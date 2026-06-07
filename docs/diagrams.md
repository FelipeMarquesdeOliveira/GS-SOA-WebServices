# Lunar Base - Diagramas de Arquitetura e Fluxo

## Arquitetura de Microservicos

```
                                    [Mobile App]
                                         |
                    +--------------------+--------------------+
                    |                    |                    |
              [ResourceService]    [CrewService]      [TelemetryService]
                    |                    |                    |
                    |                    |                    |
              [EventService]       [SimulationService]  [SpaceIntegrationService]
                    |                    |                    |
                    +--------------------+--------------------+
                                         |
                              [lunarbase-infrastructure]
                                         |
                              [lunarbase-domain (shared)]
```

## Fluxo de Dados - Gerenciamento de Recursos

```
Cliente Mobile
     |
     v
POST /resources (criar recurso)
     |
     v
ResourceService.create()
     |
     +---> ResourceRepository.save()
     |           |
     |           v
     |      [InMemory DB]
     |
     v
Response: ResourceDto (201 Created)
```

## Fluxo de Dados - Telemetria

```
Sensores da Base
     |
     v
POST /telemetry (enviar leitura)
     |
     v
TelemetryService.addReading()
     |
     +---> TelemetryRepository.save()
     |           |
     |           v
     |      [InMemory DB]
     |
     v
GET /telemetry/current (estado atual)
     |
     v
Response: CurrentStateDto (temperatura, pressao, radiacao, oxigenio, energia)
```

## Fluxo de Eventos e Alertas

```
Recurso em nivel critico
     |
     v
POST /resources/{id}/consume
     |
     v
ResourceService.consume()
     |
     +---> Verifica status
     |           |
     |     [Status == CRITICAL]
     |           |
     |           v
     +---> Lanca ResourceCriticalException
     |           |
     |           v
     +---> GlobalExceptionHandler
     |           |
     |           v
     +---> Cria evento automaticamente (opcional)
     |
     v
Response: 500 Internal Server Error com detalhes do alerta
```

## Fluxo de Simulacao

```
POST /simulation (criar simulacao)
     |
     v
SimulationService.create()
     |
     v
SimulationRepository.save()
     |
     v
Response: SimulationDto (status: Running)
     |
     v
POST /simulation/{id}/run (executar)
     |
     v
SimulationService.run() --> Simula cenario --> Resultado aleatorio
     |
     v
SimulationRepository.save()
     |
     v
Response: SimulationDto (completedAt, isSuccess, result)
```

## Modelo de Dominio

```
Entity (abstract)
  |-- Resource (id, name, type, currentAmount, maxCapacity, status, location)
  |     +-- ResourceHistory (resourceId, amountBefore, amountAfter, action, timestamp)
  |
  |-- CrewMember (id, name, role, specialization, isActive, lastShift)
  |     +-- CrewTask (id, crewMemberId, title, description, dueDate, isCompleted)
  |
  |-- SpaceEvent (id, title, description, severity, status, resourceId, acknowledgedBy)
  |
  |-- Simulation (id, name, scenario, startedAt, completedAt, isSuccess, result)
  |
  |-- TelemetryReading (id, sensorType, value, unit, location, timestamp)
```

## Diagrama de Classes - ResourceService

```
ResourcesController
     |
     +-- ResourceService
              |
              +-- ResourceRepository (interface)
                       |
                       +-- ResourceRepositoryImpl
                                |
                                +-- InMemoryRepository<Resource>
                                         |
                                         +-- Map<Integer, Resource> database
```

## Fluxo de Tratamento de Excecoes

```
Request
  |
  v
@ControllerAdvice (GlobalExceptionHandler)
  |
  +-- NotFoundException --> 404 NOT_FOUND
  |
  +-- ResourceCriticalException --> 500 INTERNAL_SERVER_ERROR
  |
  +-- BusinessRuleException --> 400 BAD_REQUEST
  |
  +-- IllegalArgumentException --> 400 BAD_REQUEST
  |
  +-- Exception (genérica) --> 500 INTERNAL_SERVER_ERROR
  |
  v
Response (JSON com message, code, entityId/resourceId)
```

## Endpoints por Servico

```
ResourceService (5001)
  GET    /resources
  POST   /resources
  GET    /resources/{id}
  PUT    /resources/{id}
  POST   /resources/{id}/consume
  POST   /resources/{id}/replenish
  DELETE /resources/{id}
  GET    /resources/stats
  GET    /resources/{id}/history

SimulationService (5002)
  POST   /simulation
  GET    /simulation/{id}
  POST   /simulation/{id}/run
  GET    /simulation/history

EventService (5003)
  GET    /events
  POST   /events
  GET    /events/active
  GET    /events/resource/{resourceId}
  POST   /events/{id}/ack
  POST   /events/{id}/link-resource

TelemetryService (5004)
  POST   /telemetry
  GET    /telemetry
  GET    /telemetry/current

SpaceIntegrationService (5005)
  GET    /space/weather
  GET    /space/asteroids
  GET    /space/mars/weather

CrewService (5006)
  GET    /crew
  POST   /crew
  GET    /crew/{id}
  GET    /crew/{id}/tasks
  POST   /crew/task
  POST   /crew/{id}/shift
```
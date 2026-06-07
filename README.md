# Lunar Base Telemetry & Command System

Sistema de telemetria e comando da base lunar baseado em arquitetura de microservicos.

## Grupo

- Felipe Marques de Oliveira RM556319
- Gabriel Barros Cisoto RM556309

## Descricao do Projeto

Este projeto foi desenvolvido como parte da disciplina de Global Solutions - SOA e WebServices, com o objetivo de criar um sistema de monitoramento e comando para uma base lunar. A arquitetura de microservicos permite escalabilidade e manutencao facilitada.

Este mesmo projeto de API foi integrado ao app mobile da GS de Mobile, integrando as materias de SOA e WebServices com Mobile.

## Arquitetura

```
LunarBase/
├── src/
│   ├── lunarbase-domain/              # Camada de dominio (entidades, interfaces, DTOs)
│   ├── lunarbase-infrastructure/      # Camada de infraestrutura (repositorios)
│   ├── resource-service/              # Gestao de recursos (oxigenio, energia, agua)
│   ├── simulation-service/            # Simulacao de cenarios criticos
│   ├── event-service/                 # Sistema de eventos e alertas
│   ├── telemetry-service/             # Telemetria em tempo real
│   ├── space-integration-service/     # Integracao com dados espaciais
│   └── crew-service/                  # Gestao da tripulacao
└── tests/
    └── lunarbase-tests/               # Testes unitarios
```

## Conceitos Aplicados

### POO (Programacao Orientada a Objetos)
- **Heranca**: Classe abstrata `Entity` como base para todas as entidades (`Resource`, `SpaceEvent`, `CrewMember`, etc.)
- **Polimorfismo**: Metodos sobrescritos em entidades derivadas
- **Encapsulamento**: Propriedades com getters/setters controlados

### Classes Abstratas e Interfaces
- **Classes Abstratas**: `Entity` (classe base abstrata)
- **Interfaces**: `Repository<T>`, `ResourceRepository`, `CrewRepository`, `EventRepository`, `SimulationRepository`, `TelemetryRepository`
- **Injecao de Dependencia**: Configurada via Spring (`AppConfig` e `@Bean`)

### DTOs e VOs
- `ResourceDto`, `CreateResourceRequest`, `ResourceHistoryDto`
- `CrewMemberDto`, `CreateCrewMemberRequest`, `CrewTaskDto`, `CreateTaskRequest`
- `EventDto`, `CreateEventRequest`
- `SimulationDto`, `CreateSimulationRequest`
- `TelemetryReadingDto`, `CreateTelemetryReadingRequest`, `CurrentStateDto`
- `SpaceWeatherDto`, `AsteroidDto`, `MarsWeatherDto`

### Tratamento de Excecoes
- `NotFoundException`: Recurso nao encontrado
- `BusinessRuleException`: Violacao de regra de negocio
- `ResourceCriticalException`: Estado critico de recurso
- `GlobalExceptionHandler`: Middleware global para tratamento centralizado

### Historico e DateTime
- Todas as entidades possuem `createdAt` e `updatedAt`
- `ResourceHistory` registra todas as alteracoes de recursos com timestamps

## Microservicos

| Servico | Porta | Descricao | Endpoints Principais |
|---------|-------|-----------|---------------------|
| ResourceService | 5001 | Gestao de recursos da base | GET/POST /resources, PUT /resources/{id}, GET /resources/stats, GET /resources/{id}/history |
| SimulationService | 5002 | Simulacao de cenarios criticos | POST /simulation, POST /simulation/{id}/run, GET /simulation/{id}, GET /simulation/history |
| EventService | 5003 | Sistema de eventos e alertas | GET /events, POST /events, GET /events/active, POST /events/{id}/ack, POST /events/{id}/link-resource |
| TelemetryService | 5004 | Telemetria em tempo real | POST /telemetry, GET /telemetry/current, GET /telemetry |
| SpaceIntegrationService | 5005 | Dados espaciais | GET /space/weather, GET /space/asteroids, GET /space/mars/weather |
| CrewService | 5006 | Gestao da tripulacao | GET /crew, POST /crew, GET /crew/{id}, GET /crew/{id}/tasks, POST /crew/task, POST /crew/{id}/shift |

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Maven
- JUnit 5 (testes unitarios)
- Postman / Swagger (testes manuais)

## Como Executar

Cada microservico pode ser executado independentemente:

```bash
# ResourceService (porta padrao 5001)
cd src/resource-service && mvn spring-boot:run

# SimulationService (porta padrao 5002)
cd src/simulation-service && mvn spring-boot:run

# EventService (porta padrao 5003)
cd src/event-service && mvn spring-boot:run

# TelemetryService (porta padrao 5004)
cd src/telemetry-service && mvn spring-boot:run

# SpaceIntegrationService (porta padrao 5005)
cd src/space-integration-service && mvn spring-boot:run

# CrewService (porta padrao 5006)
cd src/crew-service && mvn spring-boot:run
```

## Documentacao da API

A documentacao Swagger esta disponivel em cada servico:
- ResourceService: http://localhost:5001/swagger-ui.html
- SimulationService: http://localhost:5002/swagger-ui.html
- EventService: http://localhost:5003/swagger-ui.html
- TelemetryService: http://localhost:5004/swagger-ui.html
- SpaceIntegrationService: http://localhost:5005/swagger-ui.html
- CrewService: http://localhost:5006/swagger-ui.html

## Testes

### Testes Unitarios

```bash
cd tests/lunarbase-tests
mvn test
```

### Testes com Postman

Coloquei na pasta `e2e/` uma colecao do Postman com todos os endpoints para teste.

## Integracao com Mobile

Esta API foi integrada ao projeto mobile da Global Solution, permitindo que o aplicativo movel consuma os dados de telemetria, gerencie recursos e monitore a base lunar remotamente.
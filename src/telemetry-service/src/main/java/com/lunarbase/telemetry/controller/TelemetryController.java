package com.lunarbase.telemetry.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.interfaces.TelemetryRepository;
import com.lunarbase.telemetry.service.TelemetryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {
    private final TelemetryService service;

    public TelemetryController(TelemetryRepository repository) {
        this.service = new TelemetryService(repository);
    }

    @PostMapping
    public TelemetryReadingDto addReading(@RequestBody CreateTelemetryReadingRequest request) {
        return service.addReading(request);
    }

    @GetMapping("/current")
    public CurrentStateDto getCurrentState() {
        return service.getCurrentState();
    }

    @GetMapping
    public List<TelemetryReadingDto> getAll() {
        return service.getAllReadings();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String stream() {
        return "data: SSE stream not implemented in mock mode\n\n";
    }
}
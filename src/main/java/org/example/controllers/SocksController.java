package org.example.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.Socks;
import org.example.services.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Validated
public class SocksController {
    private final SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<?> addSocks(@Valid @RequestBody Socks socks) {
        log.info("addSocks");
        return ResponseEntity.ok(socksService.addSocks(socks));
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> removeSocks(@Valid @RequestBody Socks socks) {
        log.info("removeSocks");
        socksService.removeSocks(socks);
        return ResponseEntity.status(200).build();
    }

    @GetMapping()
    public int getTotalSocksByCriteria(
            @NonNull @RequestParam String color,
            @RequestParam SocksService.SockOperation operation,
            @Min(value = 0, message = "Cotton part percentage cannot be negative.")
            @Max(value = 100, message = "Cotton part percentage cannot be greater than 100.")
            @RequestParam int cottonPart
    ) {
        log.info("getSocks");
        return socksService.getTotalSocksByCriteria(color, operation, cottonPart);
    }
}


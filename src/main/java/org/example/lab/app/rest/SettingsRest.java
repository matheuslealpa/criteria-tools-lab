package org.example.lab.app.rest;

import lombok.AllArgsConstructor;
import org.example.lab.app.service.preferences.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
@AllArgsConstructor
public class SettingsRest {

    private final SettingsService settingsService;

    @GetMapping("/{settingsTypeId}/config")
    public ResponseEntity<?> getConfigValue(@PathVariable Long settingsTypeId, @RequestParam String key) {
        Optional<Object> configValue = settingsService.getConfigValueByKey(settingsTypeId, key);

        return configValue
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(settingsService.getSettingsByName(nome));
    }
}

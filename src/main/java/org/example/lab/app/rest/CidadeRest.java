package org.example.lab.app.rest;

import lombok.AllArgsConstructor;
import org.example.lab.app.domain.Cidade;
import org.example.lab.app.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cidade")
@AllArgsConstructor
public class CidadeRest {
    private CidadeService cidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id, @RequestParam String uf) {
        return ResponseEntity.ok().body(cidadeService.findByIdAndUf(id, uf));
    }
}

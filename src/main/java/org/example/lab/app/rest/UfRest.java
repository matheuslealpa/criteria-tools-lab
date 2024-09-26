package org.example.lab.app.rest;

import lombok.AllArgsConstructor;
import org.example.lab.app.domain.Uf;
import org.example.lab.app.service.UfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/uf")
@AllArgsConstructor
public class UfRest {

    private final UfService ufService;

    @GetMapping("/regiao")
    public ResponseEntity<List<Uf>> findAllByRegiao(@RequestParam(name = "nome") String regiao) {
        return ResponseEntity.ok()
                .body(ufService.findAllByRegiao(regiao));
    }
}

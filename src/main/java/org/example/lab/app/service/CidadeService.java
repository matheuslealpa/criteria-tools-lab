package org.example.lab.app.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lab.app.domain.Cidade;
import org.example.lab.app.repository.CidadeRepository;
import org.example.lab.app.repository.specification.CidadeSpecification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade findByIdAndUf(Long id, String uf) {
        return cidadeRepository.findOne(CidadeSpecification.byIdAndUf(id, uf))
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada."));
    }
}

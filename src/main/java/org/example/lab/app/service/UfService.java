package org.example.lab.app.service;

import lombok.AllArgsConstructor;
import org.example.lab.app.domain.Uf;
import org.example.lab.app.repository.UfRepository;
import org.example.lab.app.repository.specification.UfSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UfService {

    private final UfRepository ufRepository;

    public List<Uf> findAllByRegiao(String regiao) {
        Assert.isTrue(Objects.nonNull(regiao), "Região não informada.");
        Specification<Uf> spec = UfSpecification.byRegiao(regiao);
        return ufRepository.findAll(spec);
    }
}

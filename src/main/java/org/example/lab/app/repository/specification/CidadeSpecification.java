package org.example.lab.app.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.lab.app.domain.Cidade;
import org.example.lab.app.domain.Mesorregiao;
import org.example.lab.app.domain.Microrregiao;
import org.example.lab.app.domain.Uf;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpecification {

    private CidadeSpecification() {    }

    public static Specification<Cidade> byIdAndUf(Long id, String uf) {
        return (Root<Cidade> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Join<Cidade, Microrregiao> cidadeMicrorregiaoJoin = root.join("microrregiao");
            Join<Microrregiao, Mesorregiao> microrregiaoMesorregiaoJoin = cidadeMicrorregiaoJoin.join("mesorregiao");
            Join<Mesorregiao, Uf> ufMesorregiaoJoin = microrregiaoMesorregiaoJoin.join("uf");
            return cb.and(cb.equal(root.get("id"), id), cb.equal(ufMesorregiaoJoin.get("sigla"), uf));
        };
    }
}

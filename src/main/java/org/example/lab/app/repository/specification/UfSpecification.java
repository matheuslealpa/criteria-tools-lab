package org.example.lab.app.repository.specification;

import org.example.lab.app.domain.Uf;
import org.springframework.data.jpa.domain.Specification;

public class UfSpecification {

    private UfSpecification(){}

    public static Specification<Uf> byRegiao(String regiao){
        return (root, cq, cb) -> cb.equal(root.get("regiao"), regiao);
    }

}

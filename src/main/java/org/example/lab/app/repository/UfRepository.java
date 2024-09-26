package org.example.lab.app.repository;

import org.example.lab.app.domain.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long>, JpaSpecificationExecutor<Uf> {
}

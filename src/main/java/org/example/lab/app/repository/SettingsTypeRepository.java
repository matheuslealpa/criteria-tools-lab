package org.example.lab.app.repository;

import org.example.lab.app.domain.SettingsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsTypeRepository extends JpaRepository<SettingsType, Long> {

    Optional<SettingsType> findByNome(String nome);
}

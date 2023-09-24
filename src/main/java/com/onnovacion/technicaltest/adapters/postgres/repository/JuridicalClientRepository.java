package com.onnovacion.technicaltest.adapters.postgres.repository;

import com.onnovacion.technicaltest.adapters.postgres.entity.JuridicalClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuridicalClientRepository extends JpaRepository<JuridicalClientEntity, String> {
}

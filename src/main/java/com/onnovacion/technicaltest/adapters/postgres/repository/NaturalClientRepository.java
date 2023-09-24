package com.onnovacion.technicaltest.adapters.postgres.repository;

import com.onnovacion.technicaltest.adapters.postgres.entity.NaturalClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaturalClientRepository extends JpaRepository<NaturalClientEntity,String> {
}

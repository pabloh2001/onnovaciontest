package com.onnovacion.technicaltest.adapters.postgres.repository;

import com.onnovacion.technicaltest.adapters.postgres.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}

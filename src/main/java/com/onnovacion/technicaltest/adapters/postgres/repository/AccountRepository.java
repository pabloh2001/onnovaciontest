package com.onnovacion.technicaltest.adapters.postgres.repository;

import com.onnovacion.technicaltest.adapters.postgres.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}

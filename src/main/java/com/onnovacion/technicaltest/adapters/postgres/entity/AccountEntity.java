package com.onnovacion.technicaltest.adapters.postgres.entity;

import com.onnovacion.technicaltest.util.CoinType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountEntity {
    @Id
    @UuidGenerator
    private String id;
    @NotBlank(message = "'accountNumber' is required")
    @Column(name = "account_number")
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "coin_type")
    private CoinType coinType;
    private BigDecimal balance;
    @Column(name = "limit_balance")
    private BigDecimal limitBalance;
    @NotBlank(message = "'clientId' is required")
    @Column(name = "client_id")
    private String clientId;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<MovementEntity> movements;
}

package com.onnovacion.technicaltest.adapters.postgres.entity;

import com.onnovacion.technicaltest.util.OperationType;
import com.onnovacion.technicaltest.util.MovementStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovementEntity {
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "account_id")
    private String accountId;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;
    @Column(name = "movement_date")
    private LocalDateTime movementDate;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private AccountEntity account;
}

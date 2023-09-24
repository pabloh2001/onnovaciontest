package com.onnovacion.technicaltest.domain.model;

import com.onnovacion.technicaltest.util.OperationType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movement {
    private String id;
    private String accountId;
    private BigDecimal value;
    private OperationType operationType;
    private LocalDateTime movementDate;
}

package com.onnovacion.technicaltest.domain.model;

import com.onnovacion.technicaltest.util.CoinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String accountNumber;
    private CoinType coinType;
    private BigDecimal balance;
    private BigDecimal limitBalance;
    private String clientId;
}

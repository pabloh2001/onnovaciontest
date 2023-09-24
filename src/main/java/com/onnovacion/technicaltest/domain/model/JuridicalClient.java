package com.onnovacion.technicaltest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JuridicalClient {
    private String id;
    private String businessName;
    private Integer foundationYear;
    private String rut;
}

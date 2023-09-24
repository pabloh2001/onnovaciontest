package com.onnovacion.technicaltest.domain.model;

import com.onnovacion.technicaltest.util.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaturalClient {
    private String id;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String rut;
}

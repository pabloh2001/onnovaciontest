package com.onnovacion.technicaltest.application.dto;

import com.onnovacion.technicaltest.util.DocumentType;

public record RespNaturalClientDTO(String id, String fullName, DocumentType documentType,
                                   String documentNumber, String rut) {
}

package com.onnovacion.technicaltest.application.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Error {
    private String code;
    private String message;
}

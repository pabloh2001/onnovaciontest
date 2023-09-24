package com.onnovacion.technicaltest.domain.model;

import com.onnovacion.technicaltest.util.ClientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String clientId;
    private ClientType clientType;
}

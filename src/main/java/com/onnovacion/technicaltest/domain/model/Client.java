package com.onnovacion.technicaltest.domain.model;

import com.onnovacion.technicaltest.util.ClientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String clientId;
    private ClientType clientType;
    private String naturalClientDetail;
    private String juridicalClientDetail;

    public Client(ClientType clientType, String naturalClientDetail, String juridicalClientDetail) {
        this.clientType = clientType;
        this.naturalClientDetail = naturalClientDetail;
        this.juridicalClientDetail = juridicalClientDetail;
    }
}

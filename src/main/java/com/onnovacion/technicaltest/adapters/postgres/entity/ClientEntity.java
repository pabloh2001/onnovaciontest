package com.onnovacion.technicaltest.adapters.postgres.entity;

import com.onnovacion.technicaltest.util.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @UuidGenerator
    private String clientId;
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @Column(name = "natural_client_detail")
    private String naturalClientDetail;
    @Column(name = "juridical_client_detail")
    private String juridicalClientDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "natural_client_detail", insertable = false, updatable = false)
    private NaturalClientEntity naturalClient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "juridical_client_detail", insertable = false, updatable = false)
    private JuridicalClientEntity juridicalClient;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;
}

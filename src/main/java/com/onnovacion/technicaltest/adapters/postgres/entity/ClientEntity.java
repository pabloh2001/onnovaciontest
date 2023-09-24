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
    private String clientId;
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;
}

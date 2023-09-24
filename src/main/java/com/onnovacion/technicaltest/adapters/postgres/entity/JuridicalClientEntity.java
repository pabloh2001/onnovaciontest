package com.onnovacion.technicaltest.adapters.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "juridical_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JuridicalClientEntity {
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "business_name")
    private String businessName;
    @Column(name = "foundation_year")
    private Integer foundationYear;
    private String rut;
}

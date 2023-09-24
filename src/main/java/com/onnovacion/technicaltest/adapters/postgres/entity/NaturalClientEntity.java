package com.onnovacion.technicaltest.adapters.postgres.entity;

import com.onnovacion.technicaltest.util.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "natural_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaturalClientEntity {
    @Id
    @UuidGenerator
    private String id;
    @Size(max = 80, message = "'name' must be max 80 characters")
    private String name;
    @Size(max = 250, message = "'lastName' must be max 250 characters")
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;
    @Column(name = "document_number")
    private String documentNumber;
    @NotBlank(message = "'rut' no puede ser null o vacío")
    private String rut;
}

package com.hospital.dgbeni.domain.paciente;

import com.hospital.dgbeni.domain.shared.Endereco;
import com.hospital.dgbeni.domain.shared.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Past
    private LocalDate dataDeNascimento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private Boolean ativo = true;
}

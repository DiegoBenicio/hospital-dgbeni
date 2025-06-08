package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import com.hospital.dgbeni.domain.shared.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PacienteRequestDto(
        @NotBlank String nome,
        @NotNull @Past LocalDate dataDeNascimento,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotNull Sexo sexo,
        @NotNull @Valid Endereco endereco
) {
}

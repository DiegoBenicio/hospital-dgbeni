package com.hospital.dgbeni.dto;

import com.hospital.dgbeni.model.Endereco;
import com.hospital.dgbeni.model.Sexo;
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
        @NotNull Endereco endereco
) {
}

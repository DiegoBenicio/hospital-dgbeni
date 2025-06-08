package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PacienteUpdateRequestDto(
        @NotBlank String nome,
        @NotNull LocalDate dataDeNascimento,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotNull @Valid Endereco endereco
) {
}

package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record PacienteUpdateRequestDto(
        String nome,
        LocalDate dataDeNascimento,
        @Email String email,
        String telefone,
        Endereco endereco
) {
}

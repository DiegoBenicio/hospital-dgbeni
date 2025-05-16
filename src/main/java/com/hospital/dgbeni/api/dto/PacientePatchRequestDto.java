package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Sexo;

import java.time.LocalDate;

public record PacientePatchRequestDto(
        String nome,
        LocalDate dataDeNascimento,
        String email,
        String telefone,
        Sexo sexo
) {
}

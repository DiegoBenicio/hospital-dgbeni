package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.paciente.Paciente;

import java.time.LocalDate;

public record PacienteResponseDto(
        Long id,
        String nome,
        LocalDate dataDeNascimento,
        String email,
        String telefone
) {
    public PacienteResponseDto (Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getDataDeNascimento(),
                paciente.getEmail(),
                paciente.getTelefone()
        );
    }
}

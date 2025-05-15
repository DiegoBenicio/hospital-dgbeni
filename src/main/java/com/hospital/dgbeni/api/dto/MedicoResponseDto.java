package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.domain.shared.Especialidade;

public record MedicoResponseDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade
) {
    public MedicoResponseDto(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}


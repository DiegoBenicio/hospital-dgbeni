package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import jakarta.validation.constraints.Email;

public record MedicoUpdateRequestDto(
        String nome,
        @Email String email,
        String telefone,
        Endereco endereco
) {
}

package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import com.hospital.dgbeni.domain.shared.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoRequestDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank String crm,
        @NotNull Especialidade especialidade,
        @NotNull Endereco endereco
) {
}

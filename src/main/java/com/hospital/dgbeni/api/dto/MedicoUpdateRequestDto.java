package com.hospital.dgbeni.api.dto;

import com.hospital.dgbeni.domain.shared.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateRequestDto(
       @NotBlank String nome,
       @NotBlank @Email String email,
       @NotBlank String telefone,
       @NotNull @Valid Endereco endereco
) {
}

package com.hospital.dgbeni.api.dto;

public record MedicoPatchRequestDto(
        String nome,
        String email,
        String telefone,
        String crm
) {
}

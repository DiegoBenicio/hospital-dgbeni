package com.hospital.dgbeni.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Endereco(
        String rua,
        String cidade,
        String estado,
        String cep
) {
}

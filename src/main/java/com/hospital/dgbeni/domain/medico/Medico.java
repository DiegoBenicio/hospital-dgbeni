package com.hospital.dgbeni.domain.medico;

import com.hospital.dgbeni.api.dto.MedicoUpdateRequestDto;
import com.hospital.dgbeni.domain.shared.Endereco;
import com.hospital.dgbeni.domain.shared.Especialidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private Boolean ativo = true;

    public void inativar() {
        this.ativo = false;
    }

    public void atualizaInformacoes(MedicoUpdateRequestDto dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.endereco = dto.endereco();
    }

    public void atualizarParcial(String nome, String email, String telefone, String crm) {
        if (nome != null && !nome.isBlank()) this.nome = nome;
        if (email != null && !email.isBlank()) this.email = email;
        if (telefone != null && !telefone.isBlank()) this.telefone = telefone;
        if (crm != null && !crm.isBlank()) this.crm = crm;
    }
}

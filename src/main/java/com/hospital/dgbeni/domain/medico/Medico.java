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
        if (dto.nome() != null && !dto.nome().isBlank()) {
            this.nome = dto.nome();
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            this.email = dto.email();
        }

        if (dto.telefone() != null && !dto.telefone().isBlank()) {
            this.telefone = dto.telefone();
        }

        if (dto.endereco() != null) {
            Endereco enderecoDto = dto.endereco();

            this.endereco = new Endereco(
                    enderecoDto.rua() != null ? enderecoDto.rua() : this.endereco.rua(),
                    enderecoDto.numero() != null ? enderecoDto.numero() : this.endereco.numero(),
                    enderecoDto.complemento() != null ? enderecoDto.complemento() : this.endereco.complemento(),
                    enderecoDto.bairro() != null ? enderecoDto.bairro() : this.endereco.bairro(),
                    enderecoDto.cidade() != null ? enderecoDto.cidade() : this.endereco.cidade(),
                    enderecoDto.estado() != null ? enderecoDto.estado() : this.endereco.estado(),
                    enderecoDto.cep() != null ? enderecoDto.cep() : this.endereco.cep()
            );
        }
    }

    public void atualizarParcial(String nome, String email, String telefone, String crm) {
        if (nome != null && !nome.isBlank()) this.nome = nome;
        if (email != null && !email.isBlank()) this.email = email;
        if (telefone != null && !telefone.isBlank()) this.telefone = telefone;
        if (crm != null && !crm.isBlank()) this.crm = crm;
    }
}

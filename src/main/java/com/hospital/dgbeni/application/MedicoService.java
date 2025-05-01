package com.hospital.dgbeni.application;

import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoRequestDto dto) {
        Medico medico = new Medico(
                null,
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.crm(),
                dto.especialidade(),
                dto.endereco(),
                true
        );
        return medicoRepository.save(medico);
    }
}

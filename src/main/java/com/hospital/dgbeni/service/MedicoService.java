package com.hospital.dgbeni.service;

import com.hospital.dgbeni.dto.MedicoRequestDto;
import com.hospital.dgbeni.model.Medico;
import com.hospital.dgbeni.repository.MedicoRepository;
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

package com.hospital.dgbeni.application;

import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.domain.medico.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Medico> listarTodos() {
        return medicoRepository.findAll()
                .stream()
                .filter(Medico::getAtivo)
                .toList();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico com ID " + id + " não encontrado."));
    }
}

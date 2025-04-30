package com.hospital.dgbeni.service;

import com.hospital.dgbeni.model.Paciente;
import com.hospital.dgbeni.dto.PacienteRequestDto;
import com.hospital.dgbeni.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrar(PacienteRequestDto dto) {

        Paciente paciente = new Paciente(
                null,
                dto.nome(),
                dto.dataDeNascimento(),
                dto.email(),
                dto.telefone(),
                dto.sexo(),
                dto.endereco(),
                true
        );

        return pacienteRepository.save(paciente);
    }
}

package com.hospital.dgbeni.application;

import com.hospital.dgbeni.domain.paciente.Paciente;
import com.hospital.dgbeni.api.dto.PacienteRequestDto;
import com.hospital.dgbeni.domain.paciente.PacienteRepository;
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

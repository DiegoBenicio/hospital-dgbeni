package com.hospital.dgbeni.application;

import com.hospital.dgbeni.api.dto.PacientePatchRequestDto;
import com.hospital.dgbeni.api.dto.PacienteUpdateRequestDto;
import com.hospital.dgbeni.domain.paciente.Paciente;
import com.hospital.dgbeni.api.dto.PacienteRequestDto;
import com.hospital.dgbeni.domain.paciente.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .filter(Paciente::getAtivo)
                .toList();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não encontrado."));
    }

    public void excluir(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não encontrado."));

        paciente.inativar();

        pacienteRepository.save(paciente);
    }

    public void atualizar(Long id, PacienteUpdateRequestDto updateRequestDto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        paciente.atualizaInformacoes(updateRequestDto);

        pacienteRepository.save(paciente);
    }

    public void atualizaParcial(Long id, PacientePatchRequestDto dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        paciente.atualizarParcial(dto.nome(), dto.dataDeNascimento(), dto.email(), dto.telefone(), dto.sexo());
        pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorIdOptional(Long id) {
        return pacienteRepository.findById(id)
                .filter(Paciente::getAtivo);
    }
}

package com.hospital.dgbeni.application;

import com.hospital.dgbeni.api.dto.MedicoPatchRequestDto;
import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.api.dto.MedicoUpdateRequestDto;
import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.domain.medico.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void excluir(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico com ID " + id + " não encontrado."));

        medico.inativar();
        medicoRepository.save(medico);
    }


    public void atualizar(Long id, MedicoUpdateRequestDto dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));

        medico.atualizaInformacoes(dto);

        medicoRepository.save(medico);
    }

    public void atualizaParcial(Long id, MedicoPatchRequestDto dto) {
        Medico medico = medicoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));

        medico.atualizarParcial(dto.nome(), dto.email(), dto.telefone(), dto.crm());

        medicoRepository.save(medico);
    }
}

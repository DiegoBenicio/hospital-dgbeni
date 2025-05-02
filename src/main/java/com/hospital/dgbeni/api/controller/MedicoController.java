package com.hospital.dgbeni.api.controller;

import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.api.dto.MedicoResponseDto;
import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.application.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> cadastraMedico(@RequestBody @Valid MedicoRequestDto dto) {
        Medico medicoSalvo = medicoService.cadastrar(dto);
        URI uri = URI.create("/medicos/" + medicoSalvo.getId());
        return ResponseEntity.created(uri).body(medicoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDto>> listarTodos() {
        List<Medico> medicos = medicoService.listarTodos();
        List<MedicoResponseDto> response = medicos.stream()
                .map(MedicoResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDto> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(new MedicoResponseDto(medico));
    }
}

package com.hospital.dgbeni.api.controller;

import com.hospital.dgbeni.api.dto.PacienteResponseDto;
import com.hospital.dgbeni.domain.paciente.Paciente;
import com.hospital.dgbeni.api.dto.PacienteRequestDto;
import com.hospital.dgbeni.application.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastraPaciente(@RequestBody @Valid PacienteRequestDto dto) {
        Paciente pacienteSalvo = pacienteService.cadastrar(dto);
        URI uri = URI.create("/pacientes/" + pacienteSalvo.getId());
        return ResponseEntity.created(uri).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        List<PacienteResponseDto> response = pacientes.stream()
                .map(PacienteResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(new PacienteResponseDto(paciente));
    }
}

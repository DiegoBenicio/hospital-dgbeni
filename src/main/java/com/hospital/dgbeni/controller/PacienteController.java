package com.hospital.dgbeni.controller;

import com.hospital.dgbeni.model.Paciente;
import com.hospital.dgbeni.dto.PacienteRequestDto;
import com.hospital.dgbeni.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}

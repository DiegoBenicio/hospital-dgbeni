package com.hospital.dgbeni.controller;

import com.hospital.dgbeni.dto.MedicoRequestDto;
import com.hospital.dgbeni.model.Medico;
import com.hospital.dgbeni.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}

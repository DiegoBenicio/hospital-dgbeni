package com.hospital.dgbeni.api.controller;

import com.hospital.dgbeni.api.dto.PacientePatchRequestDto;
import com.hospital.dgbeni.api.dto.PacienteRequestDto;
import com.hospital.dgbeni.api.dto.PacienteResponseDto;
import com.hospital.dgbeni.api.dto.PacienteUpdateRequestDto;
import com.hospital.dgbeni.application.PacienteService;
import com.hospital.dgbeni.domain.paciente.Paciente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cadastra um paciente", description = "Método para cadastrar paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios e o formato dos dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado no servidor. Tente novamente mais tarde ou entre em contato com o suporte")

    })
    @PostMapping
    public ResponseEntity<Paciente> cadastraPaciente(@RequestBody @Valid PacienteRequestDto dto) {
        Paciente pacienteSalvo = pacienteService.cadastrar(dto);
        URI uri = URI.create("/pacientes/" + pacienteSalvo.getId());
        return ResponseEntity.created(uri).body(pacienteSalvo);
    }

    @Operation(summary = "Busca todos os paciente", description = "Retorna os dados de todos pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar paciente")
    })
    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        List<PacienteResponseDto> response = pacientes.stream()
                .map(PacienteResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca um paciente pelo ID", description = "Retorna os dados do paciente com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar paciente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(new PacienteResponseDto(paciente));
    }

    @Operation(summary = "Exclui um paciente pelo ID", description = "Método para exclusão do paciente com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado ao tentar excluir o paciente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um paciente pelo ID", description = "Método para atualizar o paciente com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para atualização"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar dados do paciente")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizaPaciente(@PathVariable Long id, @RequestBody @Valid PacienteUpdateRequestDto updateRequestDto) {
       pacienteService.atualizar(id, updateRequestDto);
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualização parcial dos dados do paciente", description = "Método para atualizar parcialmente o paciente com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do paciente atualizados parcialmente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização parcial"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar parcialmente os dados do paciente")
    })
    @PatchMapping
    public ResponseEntity<Paciente> atualizaParcial(@PathVariable Long id, @RequestBody @Valid PacientePatchRequestDto dto) {
        pacienteService.atualizaParcial(id, dto);
        return ResponseEntity.noContent().build();
    }
}

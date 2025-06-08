package com.hospital.dgbeni.api.controller;

import com.hospital.dgbeni.api.dto.MedicoPatchRequestDto;
import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.api.dto.MedicoResponseDto;
import com.hospital.dgbeni.api.dto.MedicoUpdateRequestDto;
import com.hospital.dgbeni.application.MedicoService;
import com.hospital.dgbeni.domain.medico.Medico;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Operation(summary = "Cadastra um médico", description = "Método para cadastrar Médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios e o formato dos dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado no servidor. Tente novamente mais tarde ou entre em contato com o suporte")

    })
    @PostMapping
    public ResponseEntity<Medico> cadastraMedico(@RequestBody @Valid MedicoRequestDto dto) {
        Medico medicoSalvo = medicoService.cadastrar(dto);
        URI uri = URI.create("/medicos/" + medicoSalvo.getId());
        return ResponseEntity.created(uri).body(medicoSalvo);
    }

    @Operation(summary = "Busca todos os médicos", description = "Retorna os dados de todos os médicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médicos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao listar médicos")
    })
    @GetMapping
    public ResponseEntity<List<MedicoResponseDto>> listarTodos() {
        List<Medico> medicos = medicoService.listarTodos();
        List<MedicoResponseDto> response = medicos.stream()
                .map(MedicoResponseDto::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca um médico pelo ID", description = "Retorna os dados do médico com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar médico")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDto> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(new MedicoResponseDto(medico));
    }

    @Operation(summary = "Exclui um médico pelo ID", description = "Método para exclusão do médico com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado ao tentar excluir o médico")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluiMedico(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um médico pelo ID", description = "Método para atualizar o médico com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do médico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para atualização"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar dados do médico")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizaMedico(@PathVariable Long id, @RequestBody @Valid MedicoUpdateRequestDto updateRequestDto) {
        medicoService.atualizar(id, updateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualização parcial dos dados do médico", description = "Método para atualizar parcialmente o médico com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do médico atualizados parcialmente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização parcial"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado com o ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar parcialmente os dados do médico")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Medico> atualizaParcial(@PathVariable Long id, @RequestBody @Valid MedicoPatchRequestDto dto) {
        medicoService.atualizaParcial(id, dto);
        return ResponseEntity.noContent().build();
    }
}

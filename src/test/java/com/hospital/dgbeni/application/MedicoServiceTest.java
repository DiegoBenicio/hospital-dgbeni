package com.hospital.dgbeni.application;

import com.hospital.dgbeni.api.dto.MedicoRequestDto;
import com.hospital.dgbeni.domain.medico.Medico;
import com.hospital.dgbeni.domain.medico.MedicoRepository;
import com.hospital.dgbeni.domain.shared.Endereco;
import com.hospital.dgbeni.domain.shared.Especialidade;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static com.hospital.dgbeni.domain.shared.UF.SP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarMedicoComSucesso() {
        // Arrange
        Endereco endereco = new Endereco("Rua Turton Junior", "800", "Casa dos fundos", "Santa florentina", "New York", SP, "26280-001");
        MedicoRequestDto dto = new MedicoRequestDto("Dr. Diego", "diegobenicio@hospital.com", "21986245555", "21857-RJ", Especialidade.CARDIOLOGIA, endereco);

        Medico medicoSalvo = new Medico(1L, dto.nome(), dto.email(), dto.telefone(), dto.crm(), dto.especialidade(), dto.endereco(), true);
        ReflectionTestUtils.setField(medicoSalvo, "id", 1L);

        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoSalvo);

        // Act
        Medico resultado = medicoService.cadastrar(dto);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Dr. Diego");
        verify(medicoRepository).save(any(Medico.class));
    }

    @Test
    void deveListarApenasMedicosAtivos() {
        Endereco endereco = new Endereco("Rua Turton Junior", "800", "Casa dos fundos", "Santa florentina", "New York", SP, "26280-001");
        MedicoRequestDto dto1 = new MedicoRequestDto("Dr. Diego", "diegobenicio@hospital.com", "21986245555", "21857-RJ", Especialidade.CARDIOLOGIA, endereco);
        MedicoRequestDto dto2 = new MedicoRequestDto("Dr. Samuel", "diegobenicio@hospital.com", "21986245555", "21857-RJ", Especialidade.CARDIOLOGIA, endereco);


        Medico medicoSalvo = new Medico(1L, dto1.nome(), dto1.email(), dto1.telefone(), dto1.crm(), dto1.especialidade(), dto1.endereco(), true);
        Medico medicoSalvo2 = new Medico(2L, dto2.nome(), dto2.email(), dto2.telefone(), dto2.crm(), dto2.especialidade(), dto2.endereco(), false);

        when(medicoRepository.findAll()).thenReturn(List.of(medicoSalvo, medicoSalvo2));

        //Act
        List<Medico> resultado = medicoService.listarTodos();

        //Assert
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0)).isEqualTo(medicoSalvo);
    }

    @Test
    void deveBuscarMedicoPorIdComSucesso() {
        //Arrange
        Long id = 1L;
        Endereco endereco = new Endereco("Rua A", "123", "Casa", "Bairro", "Cidade", SP, "00000-000");
        Medico medico = new Medico(id, "Dr. Diego", "diego@teste.com", "21999999999", "12345", Especialidade.CARDIOLOGIA, endereco, true);

        when(medicoRepository.findById(id)).thenReturn(Optional.of(medico));

        //Act
        Medico resultado = medicoService.buscarPorId(id);

        //Assert
        assertNotNull(resultado);
        assertEquals("Dr. Diego", resultado.getNome());
        assertEquals(id, resultado.getId());
    }

    @Test
    void deveLancarExceptionQuandoMedicoNaoForEncontrado() {

        Long id = 2L;
//        Optional.empty() simula que o repositório não encontrou nada.
        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        //Act + Assert
//        A classe da exceção esperada.
//        Um lambda (() -> {}) com o código que deve lançar a exceção
        assertThrows(EntityNotFoundException.class, () -> medicoService.buscarPorId(id));
    }

    @Test
    void deveInativarMedicoComSucesso() {
        // Arrange
        Long id = 1L;
        Endereco endereco = new Endereco("Rua A", "123", "Casa", "Bairro", "Cidade", SP, "00000-000");
        Medico medico = new Medico(id, "Dr. Diego", "diego@teste.com", "21999999999", "12345", Especialidade.CARDIOLOGIA, endereco, true);

        when(medicoRepository.findById(id)).thenReturn(Optional.of(medico));

        //Act
        medicoService.excluir(id);

        //Assert
        assertFalse(medico.getAtivo());
        verify(medicoRepository).save(medico);
    }

    @Test
    void deveLancarExceptionAoExcluirMedicoNaoEncontrado() {

        Long id = 2L;
        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> medicoService.excluir(id));
    }
}

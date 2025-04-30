package com.hospital.dgbeni.repository;

import com.hospital.dgbeni.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

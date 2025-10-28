package org.wmdb.recibo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wmdb.recibo.models.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}

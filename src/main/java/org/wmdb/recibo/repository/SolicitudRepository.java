package org.wmdb.recibo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wmdb.recibo.models.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {    
}

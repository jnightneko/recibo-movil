package org.wmdb.recibo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wmdb.recibo.models.Solicitud;
import org.wmdb.recibo.models.Usuario;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    public Optional<Solicitud> findByUsuario(Usuario id);
}

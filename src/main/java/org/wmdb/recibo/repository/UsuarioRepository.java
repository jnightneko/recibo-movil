package org.wmdb.recibo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wmdb.recibo.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsuario(String usuario);
}

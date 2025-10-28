package org.wmdb.recibo.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.wmdb.recibo.models.Usuario;

public record UsuarioData(
    Long id,    
    String nombre,
    String usuario,
    @JsonInclude(
        JsonInclude.Include.NON_NULL
    )
    String clave
) {
    public static UsuarioData valueOf(Usuario model) {
        return new UsuarioData(model.getIdUsuario(), model.getNombre(), model.getUsuario(), null);
    }
}

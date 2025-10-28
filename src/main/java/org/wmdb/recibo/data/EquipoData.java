package org.wmdb.recibo.data;

import org.wmdb.recibo.models.Equipo;

public record EquipoData(
    Long id,
    String nombre,
    boolean disponible
) {
    public static EquipoData valueOf(Equipo model) {
        return new EquipoData(model.getIdEquipo(), model.getNombre(), model.getSolicitud() == null);
    }
}

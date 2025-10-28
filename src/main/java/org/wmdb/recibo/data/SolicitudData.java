package org.wmdb.recibo.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.wmdb.recibo.models.Equipo;
import org.wmdb.recibo.models.Solicitud;

public record SolicitudData(
    Long id,
    String carrera,
    String curso,
    
    Date fechaSolicitud,
    Date fechaUso,
    
    String aula,
    String horaDe,
    String horaAsta,
    
    String celular,
    
    List<EquipoData> equipos,
    String observaciones,
    
    String recepcionistaEntrega,
    String recepcionistaRecibe,

    Boolean revisionCompleto,
    UsuarioData usuario
) {

    public static LocalDateTime parseTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);
        return localDateTime;
    }
    
    public static SolicitudData valueOf(Solicitud model) {
        List<EquipoData> equipos = new ArrayList<>();
        List<Equipo> equiposModel = model.getEquipos();
        
        if (equiposModel != null) {
            equipos.addAll(equiposModel.stream()
                    .map((value) -> EquipoData.valueOf(value))
                    .toList());
        }
        
        return new SolicitudData(
                model.getIdSolicitud(),
                model.getCarrera(),
                model.getCurso(),
                model.getFechaSolicitud(),
                model.getFechaUso(),
                model.getAula(),
                model.getHoraDe().toString(),
                model.getHoraAsta().toString(),
                model.getCelular(),
                equipos,
                model.getObservaciones(),
                model.getRecepcionistaEntrega(),
                model.getRecepcionistaRecibe(),
                model.getRevisionCompleto(),
                UsuarioData.valueOf(model.getUsuario())
        );
    }
}

package org.wmdb.recibo.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wmdb.recibo.data.EquipoData;
import org.wmdb.recibo.data.SolicitudData;
import org.wmdb.recibo.data.UsuarioData;
import org.wmdb.recibo.models.Equipo;
import org.wmdb.recibo.models.Solicitud;
import org.wmdb.recibo.models.Usuario;
import org.wmdb.recibo.repository.EquipoRepository;
import org.wmdb.recibo.repository.SolicitudRepository;
import org.wmdb.recibo.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class SolicitudService {
    
    private final SolicitudRepository repository;
    private final EquipoRepository equipoRepository;
    private final UsuarioRepository usuarioRepository;
    
    public List<SolicitudData> list() {
        return repository.findAll()
                .stream()
                .map((value) -> SolicitudData.valueOf(value))
                .toList();
    }
    
    public SolicitudData getById(Long id) {
        Solicitud model = repository.findById(id).orElseThrow();
        return SolicitudData.valueOf(model);
    }
    
    public SolicitudData getByUsuario(UsuarioData data) {
        Long id = data.id();
        Usuario usuario = id == null 
                ? usuarioRepository.findByUsuario(data.usuario()).orElseThrow() 
                : usuarioRepository.findById(id).orElseThrow();
        
        Solicitud model = repository.findByUsuario(usuario).orElseThrow();
        return SolicitudData.valueOf(model);
    }
    
    @Transactional
    public SolicitudData create(SolicitudData data) {
        List<Equipo> equipos = equipoRepository.findAllById(
                data.equipos()
                        .stream()
                        .map((value) -> value.id())
                        .toList()
        ).stream()
          .map((value) -> {
            value.setDisponible(false);
            return value;
        }).toList();
        
        Usuario usuario = usuarioRepository.findById(data.usuario().id()).orElseThrow();        
        Solicitud model = Solicitud.builder()
                .aula(data.aula())
                .carrera(data.carrera())
                .celular(data.celular())
                .curso(data.curso())
                .equipos(equipos)
                .fechaSolicitud(data.fechaSolicitud())
                .fechaUso(data.fechaUso())
                .horaAsta(SolicitudData.parseTime(data.horaAsta()))
                .horaDe(SolicitudData.parseTime(data.horaDe()))
                .observaciones(data.observaciones())
                .recepcionistaEntrega(data.recepcionistaEntrega())
                .recepcionistaRecibe(data.recepcionistaRecibe())
                .revisionCompleto(data.revisionCompleto())
                .usuario(usuario)
                .build();
        model = repository.save(model);
        
        for (final Equipo equipo : equipos) {
            equipo.setSolicitud(model);
            equipoRepository.save(equipo);
        }
        return SolicitudData.valueOf(model);
    }
    
    @Transactional
    public SolicitudData update(SolicitudData data, Long id) {
        Solicitud model = repository.findById(id).orElseThrow();
        
        model.setAula(data.aula());
        model.setCarrera(data.carrera());
        model.setCelular(data.celular());
        model.setCurso(data.curso());
        
        List<EquipoData> equiposData = data.equipos();
        List<Equipo> equipos = null;
        if (equiposData != null) {
            equipos = equipoRepository.findAllById(
                data.equipos()
                        .stream()
                        .map((value) -> value.id())
                        .toList()
            );
            
            model.setEquipos(equipos);
        } else {
            System.out.println("NULL <" + model.getEquipos().size() + ">");
            for (final Equipo equipo : model.getEquipos()) {
                equipo.setDisponible(true);
                System.out.println(">> " + equipo.getIdEquipo());
                equipoRepository.save(equipo);
            }
        }
        
        model.setFechaSolicitud(data.fechaSolicitud());
        model.setFechaUso(data.fechaUso());
        model.setHoraAsta(SolicitudData.parseTime(data.horaAsta()));
        model.setHoraDe(SolicitudData.parseTime(data.horaDe()));
        model.setObservaciones(data.observaciones());
        
        model.setRecepcionistaEntrega(data.recepcionistaEntrega());
        model.setRecepcionistaRecibe(data.recepcionistaRecibe());
        model.setRevisionCompleto(data.revisionCompleto());
        
        UsuarioData usuarioData = data.usuario();
        if (usuarioData != null) {
            model.setUsuario(usuarioRepository.findById(usuarioData.id())
                    .orElseThrow());
        }
        
        return SolicitudData.valueOf(
                repository.save(model)
        );
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

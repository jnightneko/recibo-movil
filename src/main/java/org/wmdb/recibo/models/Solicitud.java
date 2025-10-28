package org.wmdb.recibo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Solicitud")
@Table(name = "Solicitud")
public class Solicitud {
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idSolicitud;
    
    private String carrera;
    private String curso;
    
    private Date fechaSolicitud;
    private Date fechaUso;
    
    private String aula;
    private LocalDateTime horaDe;
    private LocalDateTime horaAsta;
    
    private String celular;
    
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL)
    private List<Equipo> equipos;
    private String observaciones;
    
    private String recepcionistaEntrega;
    private String recepcionistaRecibe;

    private Boolean revisionCompleto;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
}

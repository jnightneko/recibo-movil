package org.wmdb.recibo.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Equipo")
@Table(name = "Equipo")
public class Equipo {
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idEquipo;
    private String nombre;
    private boolean disponible;
    
    @ManyToOne
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    private Solicitud solicitud;
    
}

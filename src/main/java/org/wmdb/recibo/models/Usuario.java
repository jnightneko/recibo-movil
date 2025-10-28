package org.wmdb.recibo.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "Usuario")
public class Usuario {
    @Id    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long idUsuario;
    @Column(unique = true)
    private String usuario;
    
    private String nombre;
    private String clave;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudes;
}

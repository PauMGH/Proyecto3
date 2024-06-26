package es.ieslavereda.proyecto3.model;

import java.sql.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {
    private Long idCar;
    private Contenido contenido;
    private String titulo;
    private Double importe;
    private Date changedTS;
    private Cliente cliente;

    // Getters and Setters
}

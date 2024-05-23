package es.ieslavereda.proyecto3.model;

import java.sql.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pertenece {
    private Cliente cliente;
    private Contenido contenido;
    private Date changedTS;

    // Getters and Setters
}
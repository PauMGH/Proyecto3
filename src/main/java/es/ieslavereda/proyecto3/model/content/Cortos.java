package es.ieslavereda.proyecto3.model.content;

import es.ieslavereda.proyecto3.model.Contenido;

import java.sql.Date;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cortos extends Contenido {
    private Date disponibilidad;
    private boolean alquilable;
    private Date changedTS;
}

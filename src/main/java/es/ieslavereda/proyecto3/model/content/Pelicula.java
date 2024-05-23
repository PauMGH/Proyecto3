package es.ieslavereda.proyecto3.model.content;

import es.ieslavereda.proyecto3.model.Contenido;

import lombok.experimental.SuperBuilder;

import java.sql.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pelicula extends Contenido {
    private Date caducidad;
    private Date changedTS;
}

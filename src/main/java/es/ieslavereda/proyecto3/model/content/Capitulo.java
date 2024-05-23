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
public class Capitulo extends Contenido {
    private Date caducidad,
            disponibilidad;
    private int temporada;
    private String tituloCap;
    private Date changedTS;
}

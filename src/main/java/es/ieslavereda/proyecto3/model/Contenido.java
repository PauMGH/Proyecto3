package es.ieslavereda.proyecto3.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Contenido {
    private Long idCont;
    private Contenido tipo;
    private String genero;
    private Tarifa tarifa;
    private String descripcion;
    private String director;
    private Time duracion;
    private String elenco;
    private Date fechaEstreno;
    private Double valoracionMedia;
    private String titulo;
    private String idioma;
    private String imagen;
    private Date changedTS;

    // Getters and Setters
}

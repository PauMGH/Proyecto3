package es.ieslavereda.proyecto3.model;

import jdk.jfr.Timestamp;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {
    private int id;
    private double mediaValor;
    private String titulo,
            descripcion,
            genero,
            cod_alquiler,
            director;
    private Date fechaEstreno;
    private String  duracion;
    private String elenco;
    private String tipo; //Catalogo
    private Date caducidad;
    //private Timestamp changedTS;
}
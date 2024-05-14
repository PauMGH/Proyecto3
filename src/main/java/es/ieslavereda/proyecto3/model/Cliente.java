package es.ieslavereda.proyecto3.model;

import jdk.jfr.Timestamp;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String contrasenya;
    private String domicilio;
    private int codigoPostal;
    private String correo;
    private Date fechaNacimiento;// se recibe como date de tipo sql, hay que cambiar el tipo para recibirla
    private int tarjeta;
    private Timestamp changedTS;
}
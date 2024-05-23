package es.ieslavereda.proyecto3.model;

import lombok.*;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private int idCli;
    private String usuario;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private Integer codigoPostal;
    private String email;
    private Date fechaNacimiento;
    private int tarjeta;
    private boolean administrador;
    private Date changedTS;
}

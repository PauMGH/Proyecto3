package es.ieslavereda.proyecto3.model;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifa {
    private long idTarifa;
    private double precio;
    private Date changedTS;
}

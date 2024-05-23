package es.ieslavereda.proyecto3.model;

import java.sql.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    private static final int IVA = 21;
    private Long idFac;
    private Cliente cliente;
    private Date fechaFac;
    private Double baseImponible;
    private Date changedTS;

    // Getters and Setters
}

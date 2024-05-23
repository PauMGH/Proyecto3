package es.ieslavereda.proyecto3.model;

import java.sql.Date;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaFactura {
    private Long idLinea;
    private Factura factura;
    private Carrito carrito;
    private Date changedTS;

    // Getters and Setters
}

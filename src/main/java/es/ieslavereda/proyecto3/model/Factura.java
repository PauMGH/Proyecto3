package es.ieslavereda.proyecto3.model;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    private static final int IVA = 21;
    private Float importeBase;
    private int id,
            id_cliente;
    private Date fecha;
}

/*
 private static final int IVA = 21;
    private int importeBase,id,id_cliente;
    private Date fecha;
 */

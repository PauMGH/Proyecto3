package es.ieslavereda.proyecto3.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {
    private int id_cliente,
            id_catalogo,
            id;
    private double importe;
    private String titulo;
}
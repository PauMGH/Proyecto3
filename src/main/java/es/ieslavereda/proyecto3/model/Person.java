package es.ieslavereda.proyecto3.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    private int id;
    private String first_name;
    private String last_name;
    private int house_id;
}

package es.ieslavereda.proyecto3.services;


import es.ieslavereda.proyecto3.model.Carrito;
import es.ieslavereda.proyecto3.zrepositories.PeticionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PeticionesService {

    @Autowired
    private PeticionesRepository peticionesRepository;

    public float anyadirAlCarrito(Carrito carrito) throws SQLException {
        return peticionesRepository.anyadirAlCarrito(carrito);
    }
}

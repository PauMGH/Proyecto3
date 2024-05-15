package es.ieslavereda.proyecto3.controllers;


import es.ieslavereda.proyecto3.model.Carrito;
import es.ieslavereda.proyecto3.model.Pelicula;
import es.ieslavereda.proyecto3.services.PeticionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class PeticionesController extends BaseController{

    @Autowired
    private PeticionesService peticionesService;

    @PostMapping("/carrito/")
    public ResponseEntity<?>  anyadirAlCarrito(@RequestBody Carrito carrito){
        LOGGER.log(Level.INFO, "Anyadiendo una nueva line al carrito");
        try {
            return new ResponseEntity<>(peticionesService.anyadirAlCarrito(carrito), HttpStatus.OK);
        }catch (SQLException e) {
        Map<String,Object> response = new HashMap<>();
        response.put("code",e.getErrorCode());
        response.put("message",e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/votar/")
//    public ResponseEntity<?> votarPelicula(@RequestBody Pelicula pelicula){
//        LOGGER.log(Level.INFO, "Votando pelicula {0}", pelicula);
//    }


}

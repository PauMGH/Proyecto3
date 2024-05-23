package es.ieslavereda.proyecto3.controllers;

import es.ieslavereda.proyecto3.services.CapituloService;
import es.ieslavereda.proyecto3.services.ContenidoService;
import es.ieslavereda.proyecto3.services.CortosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class ContenidoController extends BaseController{

    @Autowired
    ContenidoService contenidoService;
    @CrossOrigin(origins = "*")
    @GetMapping("/contenidos")
    public ResponseEntity<?> getPeliculas() {
        LOGGER.log(Level.INFO,"Obteniendo todas las Peliculas");

        try{
            return new ResponseEntity<> (contenidoService.getAllPeliculas(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/contenido/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Obteniendo la pelicula de id: " + id);

        try {
            return new ResponseEntity<>(contenidoService.getContenidoById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contenido/{id}")
    public ResponseEntity<?> deletePeliculaById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Borrando la pelicula de id: " + id);
        try {
            return new ResponseEntity<>(contenidoService.deleteContenidoById(id), HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

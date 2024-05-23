package es.ieslavereda.proyecto3.controllers;

import es.ieslavereda.proyecto3.model.content.Pelicula;
import es.ieslavereda.proyecto3.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class PeliculasController extends BaseController{

    @Autowired
    PeliculaService peliculaService;
    @CrossOrigin(origins = "*")
    @GetMapping("/peliculas")
    public ResponseEntity<?> getPeliculas() {
        LOGGER.log(Level.INFO,"Obteniendo todas las Peliculas");

        try{
            return new ResponseEntity<> (peliculaService.getAllPeliculas(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/pelicula/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Obteniendo la pelicula de id: " + id);

        try {
            return new ResponseEntity<>(peliculaService.getPeliculaById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<?> deletePeliculaById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Borrando la pelicula de id: " + id);
        try {
            return new ResponseEntity<>(peliculaService.deletePeliculaById(id), HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pelicula/")
    public ResponseEntity<?> addPelicula (@RequestBody Pelicula pelicula){
        LOGGER.log(Level.INFO, "Creando el cliente: " + pelicula.getTitulo());
        try {
            return new ResponseEntity<>(peliculaService.addPelicula(pelicula) ,HttpStatus.OK);
        }catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

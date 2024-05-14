package es.ieslavereda.proyecto3.controller;

import es.ieslavereda.proyecto3.model.Pelicula;
import es.ieslavereda.proyecto3.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class PeliculaController extends BaseController{

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/peliculas")
    public ResponseEntity<?> getPeliculas(){
        LOGGER.log(Level.INFO,"Obteniendo todas las peliculas");

        try{
            return new ResponseEntity<> (peliculaService.getAll(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pelicula/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable("id") int id){
         LOGGER.log(Level.INFO,"Obteniendo la peliculade id: " + id);
        try{
            return new ResponseEntity<> (peliculaService.getPeliculaById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelicula{id}")
    public ResponseEntity<?> deletePeliculaById(@PathVariable("id") int id){
        LOGGER.log(Level.INFO,"Eliminando la peliculade id: " + id);
        try{
            return new ResponseEntity<> (peliculaService.deletePeliculaById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Pelicula/")
    public ResponseEntity<?> updatePelicula(@RequestBody Pelicula pelicula){
        LOGGER.log(Level.INFO,"Actualizando la peliculade id: " + pelicula.getId());
        try{
            return new ResponseEntity<> (peliculaService.updatePelicula(pelicula), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Pelicula/")
    public ResponseEntity<?> addPelicula(@RequestBody Pelicula pelicula){
        LOGGER.log(Level.INFO,"Anyadiendo la peliculade id: " + pelicula.getId());
        try{
            return new ResponseEntity<> (peliculaService.addPelicula(pelicula), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package es.ieslavereda.proyecto3.controllers;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;
import java.util.logging.Level;

@RestController
public class ClienteController extends BaseController{

    @Autowired
    ClienteService clienteService;
    @CrossOrigin(origins = "*")
    @GetMapping("/clientes")
    public ResponseEntity<?> getClientes() {
        LOGGER.log(Level.INFO,"Obteniendo todos los Clientes");

        try{
            return new ResponseEntity<> (clienteService.getAllClientes(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @CrossOrigin(origins = "*")
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Obteniendo el cliente de id: " + id);

        try {
            return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Borrando el cliente de id: " + id);
        try {
            return new ResponseEntity<>(clienteService.deleteClienteById(id), HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/cliente/")
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        LOGGER.log(Level.INFO, "Actualizando el cliente de id: " + cliente.getIdCli());

        try {
            return new ResponseEntity<>(clienteService.updateCliente(cliente) ,HttpStatus.OK);
        }catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/cliente/")
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        LOGGER.log(Level.INFO, "Creando el cliente: " + cliente.getNombre());
        try {
            return new ResponseEntity<>(clienteService.addCliente(cliente) ,HttpStatus.OK);
        }catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/identificar/")
    public ResponseEntity<?> identificar(@RequestBody String nombre, String pass, Boolean web) throws SQLException{
        LOGGER.log(Level.INFO, "Identificando");
        try {
            return new ResponseEntity<>(clienteService.indentificar(nombre, pass, web), HttpStatus.OK);
        }catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

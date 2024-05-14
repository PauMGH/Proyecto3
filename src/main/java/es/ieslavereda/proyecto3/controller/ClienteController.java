package es.ieslavereda.proyecto3.controller;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class ClienteController extends BaseController{

    @Autowired
    ClienteService clienteService;

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

    @PutMapping("/cliente")
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        LOGGER.log(Level.INFO, "Actualizando el cliente de id: " + cliente.getId());

        try {
            return new ResponseEntity<>(clienteService.updateCliente(cliente) ,HttpStatus.OK);
        }catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

}

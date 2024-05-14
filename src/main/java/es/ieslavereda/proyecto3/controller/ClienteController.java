package es.ieslavereda.proyecto3.controller;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

@RestController
public class ClienteController extends BaseController{

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> getClientes() throws SQLException { //hay que cambiar eso luego
        LOGGER.log(Level.INFO,"Obteniendo todos los usuarios");
        return clienteService.getAllClientes();
    }
}

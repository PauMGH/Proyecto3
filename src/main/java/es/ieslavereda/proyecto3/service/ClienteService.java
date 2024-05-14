package es.ieslavereda.proyecto3.service;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.zrepository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() throws SQLException{
        return clienteRepository.getAll();
    }

    public Object getClienteById(int id) throws SQLException{
        return clienteRepository.getClienteById(id);
    }

    public Object deleteClienteById(int id) throws SQLException {
        return clienteRepository.deleteClienteById(id);
    }

    public Object updateCliente(Cliente cliente) throws SQLException{
        return clienteRepository.updateCliente(cliente);
    }

    public Object addCliente(Cliente cliente) throws SQLException{
        return clienteRepository.addCliente(cliente);
    }

    public boolean indentificar(String nombre, String pass, Boolean web) throws SQLException{
        return clienteRepository.identificar(nombre, pass, web);
    }
}

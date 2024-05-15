package es.ieslavereda.proyecto3.services;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.zrepositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() throws SQLException{
        return clienteRepository.getAll();
    }

    public Cliente getClienteById(int id) throws SQLException{
        return clienteRepository.getClienteById(id);
    }

    public Cliente deleteClienteById(int id) throws SQLException {
        return clienteRepository.deleteClienteById(id);
    }

    public Cliente updateCliente(Cliente cliente) throws SQLException{
        return clienteRepository.updateCliente(cliente);
    }

    public Cliente addCliente(Cliente cliente) throws SQLException{
        return clienteRepository.addCliente(cliente);
    }

    public boolean indentificar(String nombre, String pass, Boolean web) throws SQLException{
        return clienteRepository.identificar(nombre, pass, web);
    }
}

package es.ieslavereda.proyecto3.zrepository;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {
    public List<Cliente> getAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                clientes.add(Cliente.builder().id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .build());
            }
        }
        return clientes;
    }

    public Cliente getClienteById(int id)  throws SQLException{
        String sql = "SELECT * FROM cliente WHERE id = " + id;
        Cliente c = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                c = Cliente.builder().build();
            }
        }
        return c;
    }

    public Cliente deleteClienteById(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = " + id;    
        Cliente c = null;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)){
            cs.setInt(1,id);
            cs.executeUpdate();
        }
        return c;
    }

    public Cliente updateCliente(Cliente cliente) throws SQLException{
        String sql = "UPDATE cliente SET nombre = ? WHERE id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,cliente.getNombre());
            cs.setInt(2,cliente.getId());
            cs.executeUpdate();
        }
        return cliente;
    }

    public Object addCliente(Cliente cliente) throws SQLException{
        String sql = "INSERT INTO cliente(nombre) VALUES (?)";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,cliente.getNombre());
            cs.executeUpdate();
        }
        return cliente;
    }
}

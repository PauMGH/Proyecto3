package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private String admin = "";

    public List<Cliente> getAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                clientes.add(Cliente.builder().id(rs.getInt(1))
                .nombre(rs.getString(2))
                .apellidos(rs.getString(3))
                .contrasenya(rs.getString(4))
                .domicilio(rs.getString(5))
                .codigoPostal(rs.getInt(6))
                .correo(rs.getString(7))
                .fechaNacimiento(rs.getDate(8))
                .tarjeta(rs.getInt(9))
                .changedTS((Timestamp) rs.getTimestamp(10))
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
                c = Cliente.builder().id(rs.getInt(1))
                .nombre(rs.getString(2))
                .apellidos(rs.getString(3))
                .contrasenya(rs.getString(4))
                .domicilio(rs.getString(5))
                .codigoPostal(rs.getInt(6))
                .correo(rs.getString(7))
                .fechaNacimiento(rs.getDate(8))
                .tarjeta(rs.getInt(9))
                .changedTS((Timestamp) rs.getTimestamp(10))
                .build();
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
        String sql = "UPDATE cliente SET nombre = ?, apellidos = ?, contrasenya = ?, domicilio = ?, codigoPostal = ?, correo = ?, fechaNacimiento = ?, tarjeta = ? WHERE id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,cliente.getNombre());
            cs.setString(2, cliente.getApellidos());
            cs.setString(3, cliente.getContrasenya());
            cs.setString(4, cliente.getDomicilio());
            cs.setInt(5, cliente.getCodigoPostal());
            cs.setString(6, cliente.getCorreo());
            cs.setDate(7, (Date) cliente.getFechaNacimiento());
            cs.setInt(8, cliente.getTarjeta());
            cs.setTimestamp(9, (java.sql.Timestamp) cliente.getChangedTS());
            cs.setInt(10,cliente.getId());

            cs.executeUpdate();
        }
        return cliente;
    }

    public Cliente addCliente(Cliente cliente) throws SQLException{
        String sql = "INSERT INTO cliente(nombre, apellidos, contrasenya, domicilio, codigoPostal, correo, fechaNacimiento, tarjeta, changedTS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,cliente.getNombre());
            cs.setString(2, cliente.getApellidos());
            cs.setString(3, cliente.getContrasenya());
            cs.setString(4, cliente.getDomicilio());
            cs.setInt(5, cliente.getCodigoPostal());
            cs.setString(6, cliente.getCorreo());
            cs.setDate(7, (Date) cliente.getFechaNacimiento());
            cs.setInt(8, cliente.getTarjeta());
            cs.setTimestamp(9, (java.sql.Timestamp) cliente.getChangedTS());

            cs.executeUpdate();
        }
        return cliente;
    }

    public boolean identificar(String nombre, String pass, Boolean web) throws  SQLException{
        String sql = "SELECT id FROM cliente WHERE nombre = '?' AND pass = '?'";
        boolean res;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1, nombre);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery(sql);
            if(rs.next()){
                if (web){
                    res = rs.getString(1).equals(admin);
                }else{
                    res = !rs.getString(1).equals(admin);
                }
            }else res = false;
        }
        return res;
    }
}

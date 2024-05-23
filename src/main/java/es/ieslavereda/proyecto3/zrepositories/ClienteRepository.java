package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private String admin = "";

    public List<Cliente> getAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(Cliente.builder()
                        .idCli(rs.getInt(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .domicilio(rs.getString(6))
                        .codigoPostal(rs.getInt(7))
                        .email(rs.getString(8))
                        .fechaNacimiento(rs.getDate(9))
                        .tarjeta(rs.getInt(10))
                        .administrador(rs.getString(11).equals("T"))
                        .changedTS(rs.getDate(12))
                        .build());
            }
        }
        return clientes;
    }


    public Cliente getClienteById(int id)  throws SQLException{
        String sql = "SELECT * FROM Cliente WHERE Id_cli = ?";
        Cliente c = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()){
             PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,id);
             ResultSet rs = st.executeQuery();
            if(rs.next()){
                c = Cliente.builder()
                        .idCli(rs.getInt(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .domicilio(rs.getString(6))
                        .codigoPostal(rs.getInt(7))
                        .email(rs.getString(8))
                        .fechaNacimiento(rs.getDate(9))
                        .tarjeta(rs.getInt(10))
                        .administrador(rs.getString(11).equals("T"))
                        .changedTS(rs.getDate(12))
                        .build();
            }
        }
        return c;
    }

    public Cliente deleteClienteById(int id) throws SQLException {
        String sql = "call EliminarCliente(?)";
        Cliente c = getClienteById(id);
        if (c==null) return null;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(sql)){
            cs.setInt(1,id);
            cs.executeUpdate();
        }
        return c;
    }

    public int updateCliente(Cliente cliente) throws SQLException {
        String sql = "{? = call ActualizarCliente(?,?,?,?,?,?,?,?,?,?)}"; // Preparar para recibir el valor de retorno
        cliente = checkNoNullInformation(cliente);
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            // Registrar el parámetro de retorno
            cs.registerOutParameter(1, Types.NUMERIC);

            // Establecer los parámetros de entrada
            cs.setInt(2, cliente.getIdCli());
            cs.setString(3, cliente.getContrasenya());
            cs.setString(4, cliente.getNombre());
            cs.setString(5, cliente.getApellidos());
            cs.setString(6, cliente.getDomicilio());
            cs.setInt(7, cliente.getCodigoPostal());
            cs.setString(8, cliente.getEmail());
            cs.setDate(9, cliente.getFechaNacimiento());
            cs.setInt(10, cliente.getTarjeta());
            cs.setString(11, cliente.getUsuario());

            // Ejecutar la llamada
            cs.executeUpdate();

            // Obtener el valor de retorno
            int rowsUpdated = cs.getInt(1);

            // Devolver el número de filas actualizadas
            return rowsUpdated;
        }
    }


    public Cliente addCliente(Cliente cliente) throws SQLException{
        String sql = "call GenerarCliente(?,?,?,?,?,?,?,?,?)";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1,cliente.getUsuario());
            cs.setString(2,cliente.getContrasenya());
            cs.setString(3,cliente.getNombre());
            cs.setString(4,cliente.getApellidos());
            cs.setString(5,cliente.getDomicilio());
            cs.setInt(6,cliente.getCodigoPostal());
            cs.setString(7,cliente.getEmail());
            cs.setDate(8, cliente.getFechaNacimiento());
            cs.setInt(9,cliente.getTarjeta());
            cs.executeUpdate();
        }
        return cliente;
    }

    public boolean identificar(String nombre, String pass, Boolean web) throws SQLException {
        String sql = "SELECT id FROM Cliente WHERE nombre = ? AND pass = ?";
        boolean res = false;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1, nombre);
            cs.setString(2, pass);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    if (web) {
                        res = rs.getString(3).equals(admin);
                    } else {
                        res = !rs.getString(3).equals(admin);
                    }
                }
            }
        }
        return res;
    }

    public Cliente checkNoNullInformation(Cliente cliente) throws SQLException {
        // Obtener el cliente existente de la base de datos
        Cliente aux = getClienteById(cliente.getIdCli());

        // Verificar y actualizar cada campo si es nulo
        if (cliente.getUsuario() == null) cliente.setUsuario(aux.getUsuario());
        if (cliente.getContrasenya() == null) cliente.setContrasenya(aux.getContrasenya());
        if (cliente.getNombre() == null) cliente.setNombre(aux.getNombre());
        if (cliente.getApellidos() == null) cliente.setApellidos(aux.getApellidos());
        if (cliente.getDomicilio() == null) cliente.setDomicilio(aux.getDomicilio());
        if (cliente.getCodigoPostal() == null) cliente.setCodigoPostal(aux.getCodigoPostal());
        if (cliente.getEmail() == null) cliente.setEmail(aux.getEmail());
        if (cliente.getFechaNacimiento() == null) cliente.setFechaNacimiento(aux.getFechaNacimiento());
        if (cliente.getTarjeta() == 0) cliente.setTarjeta(aux.getTarjeta());
        if (cliente.getChangedTS() == null) cliente.setChangedTS(aux.getChangedTS());

        return cliente;
    }


}

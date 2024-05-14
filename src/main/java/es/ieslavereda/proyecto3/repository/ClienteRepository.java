package es.ieslavereda.proyecto3.repository;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}

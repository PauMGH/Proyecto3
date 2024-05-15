package es.ieslavereda.proyecto3.zrepositories;

import es.ieslavereda.proyecto3.model.Carrito;
import es.ieslavereda.proyecto3.model.Factura;
import es.ieslavereda.proyecto3.model.MyDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PeticionesRepository {
    public float anyadirAlCarrito(Carrito carrito) throws SQLException {
        String sql = "INSERT INTO carrito (id_cliente, id_catalogo, importe, titulo) VALUES (?, ?, ?, ?)";
        Float importe = 0f;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, carrito.getId());
            ps.setInt(2, carrito.getId_catalogo());
            ps.setDouble(3, carrito.getImporte());
            ps.setString(4, carrito.getTitulo());
            ps.executeUpdate();

            importe = actualizarFactura((float) carrito.getImporte(), carrito.getId_cliente());
        }
        return importe;
    }

    public float actualizarFactura(Float importe, int id_cliente) throws SQLException {
        String sql = "UPDATE factura SET importe = ? WHERE id_cliente = ?";
        Float importeFinal = 0f;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setFloat(1, importe);
            ps.setInt(2, id_cliente);
            ps.executeUpdate();

            importeFinal = getFacturaById(id_cliente).getImporteBase();
        }
        return  importeFinal;
    }

    public Factura getFacturaById(int id) throws SQLException {
        String sql = "SELECT * FROM factura WHERE id_cliente = ?";
        Factura factura;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            factura = Factura.builder().importeBase(rs.getFloat(1))
                    .id(rs.getInt(2))
                    .id_cliente(rs.getInt(3))
                    .fecha(rs.getDate(4))
                    .build();
        }
        return factura;
    }
}
/*
public class Carrito {
    private int id_cliente,id_catalogo,id;
    private double importe;
    private String titulo;
}
se deberá devolver en la respuesta el cálculo total actualizado de
la factura
 */

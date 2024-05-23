package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CortosRepository {


    public static List<Cortos> getAll() throws SQLException {
        List<Cortos> peliculas = new ArrayList<>();
        String sql = "select * FROM Corto p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement st = connection.prepareCall(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                peliculas.add(Cortos.builder()
                        .idCont(rs.getInt(1))
                        .disponibilidad(rs.getDate(2))
                        .alquilable((rs.getString(3).equals("T")))
                        .changedTS(rs.getDate(4))
                        .tipo(rs.getString(6))
                        .genero(rs.getString(7))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(9))
                        .director(rs.getString(10))
                        //.duracion(rs.getTime(11))
                        .elenco(rs.getString(12))
                        .fechaEstreno(rs.getDate(13))
                        .valoracionMedia(rs.getDouble(14))
                        .titulo(rs.getString(15))
                        .idioma(rs.getString(16))
                        .imagen(rs.getString(17))
                        .build()
                );
            }
        }
        return peliculas;
    }

    public static Cortos getPeliculaById(int id) throws SQLException {
        String sql = "select * FROM Corto p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa WHERE p.Id_cont=?";
        Cortos p = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = Cortos.builder()
                        .idCont(rs.getInt(1))
                        .disponibilidad(rs.getDate(2))
                        .alquilable((rs.getString(3).equals("T")))
                        .changedTS(rs.getDate(4))
                        .tipo(rs.getString(6))
                        .genero(rs.getString(7))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(9))
                        .director(rs.getString(10))
                        .duracion(rs.getTime(11))
                        .elenco(rs.getString(12))
                        .fechaEstreno(rs.getDate(13))
                        .valoracionMedia(rs.getDouble(14))
                        .titulo(rs.getString(15))
                        .idioma(rs.getString(16))
                        .imagen(rs.getString(17))
                        .build();
            }
        }
        return p;
    }

    public Cortos deletePeliculaById(int id) throws SQLException {
        String sql = "call EliminarContenido(?)";
        Cortos p = getPeliculaById(id);
        if (p == null) return null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setLong(1, id);
            cs.executeUpdate();
        }
        return p;
    }
}

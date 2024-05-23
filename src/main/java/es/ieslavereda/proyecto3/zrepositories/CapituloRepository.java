package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Capitulo;
import es.ieslavereda.proyecto3.model.content.Cortos;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CapituloRepository {


    public static List<Capitulo> getAll() throws SQLException {
        List<Capitulo> peliculas = new ArrayList<>();
        String sql = "select * FROM Capitulo p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement st = connection.prepareCall(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                peliculas.add(Capitulo.builder()
                        .idCont(rs.getInt(1))
                        .disponibilidad(rs.getDate(2))
                        .caducidad(rs.getDate(3))
                        .tituloCap(rs.getString(4))
                        .temporada(rs.getInt(5))
                        .changedTS(rs.getDate(6))
                        .tipo(rs.getString(8))
                        .genero(rs.getString(9))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(11))
                        .director(rs.getString(12))
                        //.duracion(rs.getTime(13))
                        .elenco(rs.getString(14))
                        .fechaEstreno(rs.getDate(15))
                        .valoracionMedia(rs.getDouble(16))
                        .titulo(rs.getString(17))
                        .idioma(rs.getString(18))
                        .imagen(rs.getString(19))
                        .build()
                );
            }
        }
        return peliculas;
    }

    public static Capitulo getPeliculaById(int id) throws SQLException {
        String sql = "select * FROM Capitulo p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa WHERE p.Id_cont=?";
        Capitulo p = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = Capitulo.builder()
                        .idCont(rs.getInt(1))
                        .disponibilidad(rs.getDate(2))
                        .caducidad(rs.getDate(3))
                        .tituloCap(rs.getString(4))
                        .temporada(rs.getInt(5))
                        .changedTS(rs.getDate(6))
                        .tipo(rs.getString(8))
                        .genero(rs.getString(9))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(11))
                        .director(rs.getString(12))
                        //.duracion(rs.getTime(13))
                        .elenco(rs.getString(14))
                        .fechaEstreno(rs.getDate(15))
                        .valoracionMedia(rs.getDouble(16))
                        .titulo(rs.getString(17))
                        .idioma(rs.getString(18))
                        .imagen(rs.getString(19))
                        .build();
            }
        }
        return p;
    }

    public Capitulo deletePeliculaById(int id) throws SQLException {
        String sql = "call EliminarContenido(?)";
        Capitulo p = getPeliculaById(id);
        if (p == null) return null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setLong(1, id);
            cs.executeUpdate();
        }
        return p;
    }

}

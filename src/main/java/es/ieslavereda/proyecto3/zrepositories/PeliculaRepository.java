package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.Contenido;
import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.Tarifa;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository {

    private String admin = "";

    public static List<Pelicula> getAll() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "select * FROM Pelicula p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement st = connection.prepareCall(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                peliculas.add(Pelicula.builder()
                        .idCont(rs.getInt(1))
                        .caducidad(rs.getDate(2))
                        .changedTS(rs.getDate(3))
                        .tipo(rs.getString(5))
                        .genero(rs.getString(6))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(8))
                        .director(rs.getString(9))
                        //.duracion(rs.getTime(10))
                        .elenco(rs.getString(11))
                        //.fechaEstreno(rs.getDate(12))
                        .valoracionMedia(rs.getDouble(13))
                        .titulo(rs.getString(14))
                        .idioma(rs.getString(15))
                        .imagen(rs.getString(16))
                        .build()
                );
            }
        }
        return peliculas;
    }

    public static Pelicula getPeliculaById(int id) throws SQLException {
        String sql = "select * FROM Pelicula p JOIN Contenido c ON p.Id_cont = c.Id_cont JOIN tarifa t on c.id_tarifa = t.id_tarifa WHERE p.Id_cont=?";
        Pelicula p = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = Pelicula.builder()
                        .idCont(rs.getInt(1))
                        .caducidad(rs.getDate(2))
                        .changedTS(rs.getDate(3))
                        .tipo(rs.getString(5))
                        .genero(rs.getString(6))
                        .tarifa(rs.getDouble("Precio"))
                        .descripcion(rs.getString(8))
                        .director(rs.getString(9))
                        .duracion(rs.getTime(10))
                        .elenco(rs.getString(11))
                        .fechaEstreno(rs.getDate(12))
                        .valoracionMedia(rs.getDouble(13))
                        .titulo(rs.getString(14))
                        .idioma(rs.getString(15))
                        .imagen(rs.getString(16))
                        .build();
            }
        }
        return p;
    }

    public Pelicula deletePeliculaById(int id) throws SQLException {
        String sql = "call EliminarContenido(?)";
        Pelicula p = getPeliculaById(id);
        if (p == null) return null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setLong(1, id);
            cs.executeUpdate();
        }
        return p;
    }

    public Pelicula addPelicula (Pelicula pelicula) throws SQLException {
        String sql = "call GenerarContenido(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, pelicula.getTipo());
            cs.setString(2, pelicula.getGenero());
            cs.setDouble(3,pelicula.getTarifa());
            cs.setString(4, pelicula.getDescripcion());
            cs.setString(5, pelicula.getDirector());
            cs.setTime(6, pelicula.getDuracion());
            cs.setString(7, pelicula.getElenco());
            cs.setDate(8, pelicula.getFechaEstreno());
            cs.setDouble(9, pelicula.getValoracionMedia());
            cs.setString(10, pelicula.getTitulo());
            cs.setString(11, pelicula.getIdioma());
            cs.setString(12, pelicula.getImagen());
            cs.setDate(13,pelicula.getCaducidad());

            cs.executeQuery();

        }
        return pelicula;
    }
}

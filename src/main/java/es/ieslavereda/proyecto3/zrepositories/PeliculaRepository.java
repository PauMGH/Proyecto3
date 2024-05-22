package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.Catalogo;
import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.Pelicula;
import jdk.jfr.Timestamp;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository {
    public List<Pelicula> getAll() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        String sql = "SELECT * FROM Contenido, Pelicula";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                peliculas.add(Pelicula.builder()
                        .id(rs.getInt(1))
                        .tipo(rs.getString(2))
                        .cod_alquiler(rs.getString(3))
                        .genero(rs.getString(4))
                        .descripcion(rs.getString(5))
                        .director(rs.getString(6))
                        .duracion(rs.getString(7))
                        .elenco(rs.getString(8))
                        .fechaEstreno(rs.getDate(9))
                        .mediaValor(rs.getDouble(10))
                        .titulo(rs.getString(11))

                        .caducidad(rs.getDate(14))
                        .build());
            }
        }
        return peliculas;
    }

    public Pelicula getPeliculaById(int id) throws SQLException{
        String sql = "SELECT * FROM pelicula WHERE id = " + id;
        Pelicula p= null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();){
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                p = Pelicula.builder()
                        .id(rs.getInt(1))
                        .mediaValor(rs.getDouble(2))
                        .titulo(rs.getString(3))
                        .descripcion(rs.getString(4))
                        .genero(rs.getString(5))
                        .cod_alquiler(rs.getString(6))
                        .director(rs.getString(7))
                        .fechaEstreno(( java.util.Date)rs.getDate(8))
                        .duracion(rs.getString(9))
                        .elenco(rs.getString(10))
                        .tipo(rs.getString(11))
                        .caducidad(( java.util.Date)rs.getDate(12))
                        //.changedTS((Timestamp) rs.getTimestamp(13))
                        .build();
            }
        }
        return p;
    }




    public Pelicula deletePeliculaById(int id) throws SQLException{
        String sql = "DELETE FROM pelicula WHERE id = " + id;
        Pelicula p = null;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)){
            cs.setInt(1,id);
            cs.executeUpdate();
        }
        return p;
    }

    public Pelicula updatePelicula(Pelicula pelicula) throws SQLException{
        String sql = "UPDATE pelicula SET mediaValor = ?, " +
                "titulo = ?, " +
                "descripcion = ?, " +
                "genero = ?, " +
                "cod_alquiler = ?, " +
                "director = ?, " +
                "fechaEstreno = ?, " +
                "duracion = ?, " +
                "elenco = ?, " +
                "tipo = ?, " +
                "caducidad = ?, " +
                "changedTS = ? " +
                "WHERE id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setDouble(1,pelicula.getMediaValor());
            cs.setString(2,pelicula.getTitulo());
            cs.setString(3, pelicula.getDescripcion());
            cs.setString(4, pelicula.getGenero());
            cs.setString(5, pelicula.getDirector());
            cs.setDate(6, (Date) pelicula.getFechaEstreno());
            cs.setString(7, pelicula.getDuracion());
            cs.setObject(8, pelicula.getElenco());
            cs.setObject(9, pelicula.getTipo());
            cs.setDate(10, (Date) pelicula.getCaducidad());
            //cs.setTimestamp(11, (java.sql.Timestamp) pelicula.getChangedTS());
            cs.setInt(12, pelicula.getId());
            cs.executeUpdate();
        }
        return pelicula;
    }

    public Pelicula addPelicula(Pelicula pelicula) throws SQLException{
        String sql = "INSERT INTO " +
                "pelicula(mediaValor, titulo, descripcion, genero, cod_alquiler, director, fechaEstreno, duracion, elenco, tipo, caducidad, changedTS) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setDouble(1,pelicula.getMediaValor());
            cs.setString(2, pelicula.getTitulo());
            cs.setString(3, pelicula.getDescripcion());
            cs.setString(4, pelicula.getGenero());
            cs.setString(5, pelicula.getCod_alquiler());
            cs.setString(6, pelicula.getDirector());
            cs.setDate(7, (Date) pelicula.getFechaEstreno());
            cs.setString(8, pelicula.getDuracion());
            cs.setObject(9, pelicula.getTipo());
            cs.setDate(10, (Date) pelicula.getCaducidad());
            //cs.setTimestamp(11, (java.sql.Timestamp) pelicula.getChangedTS());
            cs.setInt(12, pelicula.getId());
            cs.executeUpdate();
        }
        return pelicula;
    }



    public List<Pelicula> callMostrarPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        Pelicula p= null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{? = call MostrarPeliculas}");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rs = (ResultSet) callableStatement.getObject(1);
            while (rs.next()) {
                p = Pelicula.builder()
                        .id(rs.getInt(1))
                        .mediaValor(rs.getDouble(2))
                        .titulo(rs.getString(3))
                        .descripcion(rs.getString(4))
                        .genero(rs.getString(5))
                        .cod_alquiler(rs.getString(6))
                        .director(rs.getString(7))
                        .fechaEstreno(( java.util.Date)rs.getDate(8))
                        .duracion(rs.getString(9))
                        .elenco(rs.getString(10))
                        .tipo(rs.getString(11))
                        .caducidad(( java.util.Date)rs.getDate(12))
                        //.changedTS((Timestamp) rs.getTimestamp(13))
                        .build();
                peliculas.add(p); // Add the Pelicula object to the list
            }
        }
        return peliculas;
    }






}

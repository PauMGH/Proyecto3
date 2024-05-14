package es.ieslavereda.proyecto3.zrepository;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.Pelicula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository {
    public List<Pelicula> getAll() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        String sql = "SELECT * FROM pelicula";
        try (Connection connection = MyDataSource.getMyDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)){
            while (rs.next()) {
                peliculas.add(Pelicula.builder().id(rs.getInt(1))
                        .nombre(rs.getString(2))
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
                p = Pelicula.builder().build();
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
        String sql = "UPDATE pelicula SET nombre = ? WHERE id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,pelicula.getNombre());
            cs.setInt(2,pelicula.getId());
            cs.executeUpdate();
        }
        return pelicula;
    }

    public Pelicula addPelicula(Pelicula pelicula) throws SQLException{
        String sql = "INSERT INTO pelicula(nombre) VALUES (?)";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(sql)) {
            cs.setString(1,pelicula.getNombre());
            cs.executeUpdate();
        }
        return pelicula;
    }
}

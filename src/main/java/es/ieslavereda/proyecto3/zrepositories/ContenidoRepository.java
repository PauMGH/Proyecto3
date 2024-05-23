package es.ieslavereda.proyecto3.zrepositories;


import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.Contenido;
import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Capitulo;
import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ContenidoRepository {

    private String admin = "";

    public Collection<Contenido> getAll() throws SQLException {
        Collection<Contenido> contenido = new ArrayList<>();
        List<Pelicula> peliculas = PeliculaRepository.getAll();
        List<Cortos> cortos = CortosRepository.getAll();
        List<Capitulo> capitulos = CapituloRepository.getAll();
        contenido.addAll(peliculas);
        contenido.addAll(cortos);
        contenido.addAll(capitulos);
        Collections.shuffle((List<?>) contenido);
        return contenido;
    }


    public Contenido getClienteById(int id)  throws SQLException{
        String sql = "SELECT * FROM Contenido WHERE Id_cont = ?";
        Contenido c = null;
        try (Connection connection = MyDataSource.getMyDataSource().getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                if (rs.getString("Tipo").equals("Pelicula")){
                    c = PeliculaRepository.getPeliculaById(id);
                } else if (rs.getString("Tipo").equals("Corto")) {
                    c = CortosRepository.getPeliculaById(id);
                } else if (rs.getString("Tipo").equals("Capitulo")) {
                    c = CapituloRepository.getPeliculaById(id);
                }
            }
        }
        return c;
    }

    public Contenido deleteClienteById(int id) throws SQLException {
        String sql = "call EliminarContenido(?)";
        Contenido c = getClienteById(id);
        if (c==null) return null;
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(sql)){
            cs.setInt(1,id);
            cs.executeUpdate();
        }
        return c;
    }





}

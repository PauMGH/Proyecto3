package es.ieslavereda.proyecto3.services;

import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import es.ieslavereda.proyecto3.zrepositories.CortosRepository;
import es.ieslavereda.proyecto3.zrepositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CortosService {

    @Autowired
    private CortosRepository cortosRepository;

    public List<Cortos> getAllPeliculas() throws SQLException{
        return cortosRepository.getAll();
    }

    public Cortos getPeliculaById(int id) throws SQLException{
        return cortosRepository.getPeliculaById(id);
    }

    public Cortos deletePeliculaById(int id) throws SQLException {
        return cortosRepository.deletePeliculaById(id);
    }

}

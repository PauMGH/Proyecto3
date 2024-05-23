package es.ieslavereda.proyecto3.services;

import es.ieslavereda.proyecto3.model.content.Pelicula;
import es.ieslavereda.proyecto3.zrepositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAllPeliculas() throws SQLException{
        return peliculaRepository.getAll();
    }

    public Pelicula getPeliculaById(int id) throws SQLException{
        return peliculaRepository.getPeliculaById(id);
    }

    public Pelicula deletePeliculaById(int id) throws SQLException {
        return peliculaRepository.deletePeliculaById(id);
    }

}

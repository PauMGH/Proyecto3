package es.ieslavereda.proyecto3.services;

import es.ieslavereda.proyecto3.model.content.Capitulo;
import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.zrepositories.CapituloRepository;
import es.ieslavereda.proyecto3.zrepositories.CortosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CapituloService {

    @Autowired
    private CapituloRepository capituloRepository;

    public List<Capitulo> getAllPeliculas() throws SQLException{
        return capituloRepository.getAll();
    }

    public Capitulo getPeliculaById(int id) throws SQLException{
        return capituloRepository.getPeliculaById(id);
    }

    public Capitulo deletePeliculaById(int id) throws SQLException {
        return capituloRepository.deletePeliculaById(id);
    }

}

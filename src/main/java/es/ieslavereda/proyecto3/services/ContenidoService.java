package es.ieslavereda.proyecto3.services;

import es.ieslavereda.proyecto3.model.Contenido;
import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import es.ieslavereda.proyecto3.zrepositories.ContenidoRepository;
import es.ieslavereda.proyecto3.zrepositories.CortosRepository;
import es.ieslavereda.proyecto3.zrepositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    public Collection<Contenido> getAllPeliculas() throws SQLException{
        return contenidoRepository.getAll();
    }

    public Contenido getContenidoById(int id) throws SQLException{
        return contenidoRepository.getClienteById(id);
    }

    public Contenido deleteContenidoById(int id) throws SQLException{
        return contenidoRepository.deleteClienteById(id);
    }


}

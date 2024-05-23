package es.ieslavereda.proyecto3.zrepositories;

import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PeliculaRepositoryTest {

    @Mock
    private MyDataSource myDataSource;

    @Mock
    private Connection connection;

    @Mock
    private CallableStatement callableStatement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PeliculaRepository peliculaRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        when(myDataSource.getMyDataSource().getConnection()).thenReturn(connection);
        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(callableStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetAll() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(5)).thenReturn("Tipo");
        when(resultSet.getString(6)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(8)).thenReturn("Descripcion");
        when(resultSet.getString(9)).thenReturn("Director");
        when(resultSet.getString(11)).thenReturn("Elenco");
        when(resultSet.getDouble(13)).thenReturn(8.5);
        when(resultSet.getString(14)).thenReturn("Titulo");
        when(resultSet.getString(15)).thenReturn("Idioma");
        when(resultSet.getString(16)).thenReturn("Imagen");

        List<Pelicula> peliculas = PeliculaRepository.getAll();

        assertNotNull(peliculas);
        assertEquals(1, peliculas.size());
        assertEquals("Titulo", peliculas.get(0).getTitulo());
    }

    @Test
    public void testGetPeliculaById() throws SQLException {
        int id = 1;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(5)).thenReturn("Tipo");
        when(resultSet.getString(6)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(8)).thenReturn("Descripcion");
        when(resultSet.getString(9)).thenReturn("Director");
        when(resultSet.getTime(10)).thenReturn(Time.valueOf("01:30:00"));
        when(resultSet.getString(11)).thenReturn("Elenco");
        when(resultSet.getDate(12)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDouble(13)).thenReturn(8.5);
        when(resultSet.getString(14)).thenReturn("Titulo");
        when(resultSet.getString(15)).thenReturn("Idioma");
        when(resultSet.getString(16)).thenReturn("Imagen");

        Pelicula pelicula = PeliculaRepository.getPeliculaById(id);

        assertNotNull(pelicula);
        assertEquals(id, pelicula.getIdCont());
        assertEquals("Titulo", pelicula.getTitulo());
    }

    @Test
    public void testDeletePeliculaById() throws SQLException {
        int id = 1;
        Pelicula pelicula = mock(Pelicula.class);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(5)).thenReturn("Tipo");
        when(resultSet.getString(6)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(8)).thenReturn("Descripcion");
        when(resultSet.getString(9)).thenReturn("Director");
        when(resultSet.getTime(10)).thenReturn(Time.valueOf("01:30:00"));
        when(resultSet.getString(11)).thenReturn("Elenco");
        when(resultSet.getDate(12)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDouble(13)).thenReturn(8.5);
        when(resultSet.getString(14)).thenReturn("Titulo");
        when(resultSet.getString(15)).thenReturn("Idioma");
        when(resultSet.getString(16)).thenReturn("Imagen");

        doReturn(pelicula).when(peliculaRepository).getPeliculaById(id);

        Pelicula deletedPelicula = peliculaRepository.deletePeliculaById(id);

        assertNotNull(deletedPelicula);
        assertEquals(id, deletedPelicula.getIdCont());
        verify(callableStatement).executeUpdate();
    }

    @Test
    public void testAddPelicula() throws SQLException {
        Pelicula pelicula = Pelicula.builder()
                .tipo("Tipo")
                .genero("Genero")
                .tarifa(10.0)
                .descripcion("Descripcion")
                .director("Director")
                .duracion(Time.valueOf("01:30:00"))
                .elenco("Elenco")
                .fechaEstreno(new Date(System.currentTimeMillis()))
                .valoracionMedia(8.5)
                .titulo("Titulo")
                .idioma("Idioma")
                .imagen("Imagen")
                .caducidad(new Date(System.currentTimeMillis()))
                .build();

        Pelicula addedPelicula = peliculaRepository.addPelicula(pelicula);

        assertNotNull(addedPelicula);
        assertEquals(pelicula.getTitulo(), addedPelicula.getTitulo());
        verify(callableStatement).executeQuery();
    }
}

/*
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getPeliculaById() {
    }

    @Test
    void deletePeliculaById() {
    }

    @Test
    void addPelicula() {
    }
 */
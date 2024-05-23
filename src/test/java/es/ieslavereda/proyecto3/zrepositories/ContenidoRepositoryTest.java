package es.ieslavereda.proyecto3.zrepositories;

import es.ieslavereda.proyecto3.model.Contenido;
import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Capitulo;
import es.ieslavereda.proyecto3.model.content.Cortos;
import es.ieslavereda.proyecto3.model.content.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContenidoRepositoryTest {

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
    private ContenidoRepository contenidoRepository;

    @Mock
    private PeliculaRepository peliculaRepository;

    @Mock
    private CortosRepository cortosRepository;

    @Mock
    private CapituloRepository capituloRepository;

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
        Pelicula pelicula = mock(Pelicula.class);
        Cortos corto = mock(Cortos.class);
        Capitulo capitulo = mock(Capitulo.class);

        when(PeliculaRepository.getAll()).thenReturn(List.of(pelicula));
        when(CortosRepository.getAll()).thenReturn(List.of(corto));
        when(CapituloRepository.getAll()).thenReturn(List.of(capitulo));

        Collection<Contenido> contenidos = contenidoRepository.getAll();

        assertNotNull(contenidos);
        assertEquals(3, contenidos.size());
        assertTrue(contenidos.containsAll(Arrays.asList(pelicula, corto, capitulo)));
    }

    @Test
    public void testGetClienteById() throws SQLException {
        int id = 1;

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("Tipo")).thenReturn("Pelicula");

        Pelicula pelicula = mock(Pelicula.class);
        when(PeliculaRepository.getPeliculaById(id)).thenReturn(pelicula);

        Contenido contenido = contenidoRepository.getClienteById(id);

        assertNotNull(contenido);
        assertEquals(pelicula, contenido);
    }

    @Test
    public void testDeleteClienteById() throws SQLException {
        int id = 1;

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("Tipo")).thenReturn("Pelicula");

        Pelicula pelicula = mock(Pelicula.class);
        when(PeliculaRepository.getPeliculaById(id)).thenReturn(pelicula);

        doReturn(pelicula).when(contenidoRepository).getClienteById(id);

        Contenido deletedContenido = contenidoRepository.deleteClienteById(id);

        assertNotNull(deletedContenido);
        assertEquals(pelicula, deletedContenido);
        verify(callableStatement).executeUpdate();
    }
}

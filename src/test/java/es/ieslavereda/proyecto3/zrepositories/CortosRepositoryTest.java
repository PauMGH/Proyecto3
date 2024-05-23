package es.ieslavereda.proyecto3.zrepositories;

import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Cortos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CortosRepositoryTest {

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
    private CortosRepository cortosRepository;

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
        when(resultSet.next()).thenReturn(true, false); // Simula un único resultado
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getDate(2)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getString(3)).thenReturn("T");
        when(resultSet.getDate(4)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getString(6)).thenReturn("Tipo");
        when(resultSet.getString(7)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(9.99);
        when(resultSet.getString(9)).thenReturn("Descripcion");
        when(resultSet.getString(10)).thenReturn("Director");
        when(resultSet.getTime(11)).thenReturn(Time.valueOf("01:30:00"));
        when(resultSet.getString(12)).thenReturn("Elenco");
        when(resultSet.getDate(13)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getDouble(14)).thenReturn(4.5);
        when(resultSet.getString(15)).thenReturn("Titulo");
        when(resultSet.getString(16)).thenReturn("Idioma");
        when(resultSet.getString(17)).thenReturn("Imagen");

        List<Cortos> cortos = CortosRepository.getAll();

        assertNotNull(cortos);
        assertEquals(1, cortos.size());

        Cortos corto = cortos.get(0);
        assertEquals(1, corto.getIdCont());
        assertEquals(Date.valueOf("2023-01-01"), corto.getDisponibilidad());
        assertTrue(corto.isAlquilable());
        assertEquals(Date.valueOf("2023-01-01"), corto.getChangedTS());
        assertEquals("Tipo", corto.getTipo());
        assertEquals("Genero", corto.getGenero());
        assertEquals(9.99, corto.getTarifa());
        assertEquals("Descripcion", corto.getDescripcion());
        assertEquals("Director", corto.getDirector());
        assertEquals(Time.valueOf("01:30:00"), corto.getDuracion());
        assertEquals("Elenco", corto.getElenco());
        assertEquals(Date.valueOf("2023-01-01"), corto.getFechaEstreno());
        assertEquals(4.5, corto.getValoracionMedia());
        assertEquals("Titulo", corto.getTitulo());
        assertEquals("Idioma", corto.getIdioma());
        assertEquals("Imagen", corto.getImagen());
    }

    @Test
    public void testGetPeliculaById() throws SQLException {
        int id = 1;

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getDate(2)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getString(3)).thenReturn("T");
        when(resultSet.getDate(4)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getString(6)).thenReturn("Tipo");
        when(resultSet.getString(7)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(9.99);
        when(resultSet.getString(9)).thenReturn("Descripcion");
        when(resultSet.getString(10)).thenReturn("Director");
        when(resultSet.getTime(11)).thenReturn(Time.valueOf("01:30:00"));
        when(resultSet.getString(12)).thenReturn("Elenco");
        when(resultSet.getDate(13)).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getDouble(14)).thenReturn(4.5);
        when(resultSet.getString(15)).thenReturn("Titulo");
        when(resultSet.getString(16)).thenReturn("Idioma");
        when(resultSet.getString(17)).thenReturn("Imagen");

        Cortos corto = CortosRepository.getPeliculaById(id);

        assertNotNull(corto);
        assertEquals(1, corto.getIdCont());
        assertEquals(Date.valueOf("2023-01-01"), corto.getDisponibilidad());
        assertTrue(corto.isAlquilable());
        assertEquals(Date.valueOf("2023-01-01"), corto.getChangedTS());
        assertEquals("Tipo", corto.getTipo());
        assertEquals("Genero", corto.getGenero());
        assertEquals(9.99, corto.getTarifa());
        assertEquals("Descripcion", corto.getDescripcion());
        assertEquals("Director", corto.getDirector());
        assertEquals(Time.valueOf("01:30:00"), corto.getDuracion());
        assertEquals("Elenco", corto.getElenco());
        assertEquals(Date.valueOf("2023-01-01"), corto.getFechaEstreno());
        assertEquals(4.5, corto.getValoracionMedia());
        assertEquals("Titulo", corto.getTitulo());
        assertEquals("Idioma", corto.getIdioma());
        assertEquals("Imagen", corto.getImagen());
    }

    @Test
    public void testDeletePeliculaById() throws SQLException {
        int id = 1;

        Cortos corto = mock(Cortos.class);
        doReturn(corto).when(cortosRepository.getPeliculaById(id));

        when(callableStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);

        Cortos deletedCorto = cortosRepository.deletePeliculaById(id);

        assertNotNull(deletedCorto);
        assertEquals(corto, deletedCorto);
        verify(callableStatement).executeUpdate();
    }
}

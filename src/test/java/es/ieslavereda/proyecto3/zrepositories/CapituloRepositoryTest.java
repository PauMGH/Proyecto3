package es.ieslavereda.proyecto3.zrepositories;

import static org.junit.jupiter.api.Assertions.*;

import es.ieslavereda.proyecto3.model.MyDataSource;
import es.ieslavereda.proyecto3.model.content.Capitulo;
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
public class CapituloRepositoryTest {

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
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(4)).thenReturn("TituloCap");
        when(resultSet.getInt(5)).thenReturn(1);
        when(resultSet.getDate(6)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(8)).thenReturn("Tipo");
        when(resultSet.getString(9)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(11)).thenReturn("Descripcion");
        when(resultSet.getString(12)).thenReturn("Director");
        when(resultSet.getString(14)).thenReturn("Elenco");
        when(resultSet.getDate(15)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDouble(16)).thenReturn(8.5);
        when(resultSet.getString(17)).thenReturn("Titulo");
        when(resultSet.getString(18)).thenReturn("Idioma");
        when(resultSet.getString(19)).thenReturn("Imagen");

        List<Capitulo> capitulos = CapituloRepository.getAll();

        assertNotNull(capitulos);
        assertEquals(1, capitulos.size());
        assertEquals("TituloCap", capitulos.get(0).getTituloCap());
    }

    @Test
    public void testGetPeliculaById() throws SQLException {
        int id = 1;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(4)).thenReturn("TituloCap");
        when(resultSet.getInt(5)).thenReturn(1);
        when(resultSet.getDate(6)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(8)).thenReturn("Tipo");
        when(resultSet.getString(9)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(11)).thenReturn("Descripcion");
        when(resultSet.getString(12)).thenReturn("Director");
        when(resultSet.getString(14)).thenReturn("Elenco");
        when(resultSet.getDate(15)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDouble(16)).thenReturn(8.5);
        when(resultSet.getString(17)).thenReturn("Titulo");
        when(resultSet.getString(18)).thenReturn("Idioma");
        when(resultSet.getString(19)).thenReturn("Imagen");

        Capitulo capitulo = CapituloRepository.getPeliculaById(id);

        assertNotNull(capitulo);
        assertEquals(id, capitulo.getIdCont());
        assertEquals("TituloCap", capitulo.getTituloCap());
    }

    @Test
    public void testDeletePeliculaById() throws SQLException {
        int id = 1;
        Capitulo capitulo = mock(Capitulo.class);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getDate(2)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate(3)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(4)).thenReturn("TituloCap");
        when(resultSet.getInt(5)).thenReturn(1);
        when(resultSet.getDate(6)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString(8)).thenReturn("Tipo");
        when(resultSet.getString(9)).thenReturn("Genero");
        when(resultSet.getDouble("Precio")).thenReturn(10.0);
        when(resultSet.getString(11)).thenReturn("Descripcion");
        when(resultSet.getString(12)).thenReturn("Director");
        when(resultSet.getString(14)).thenReturn("Elenco");
        when(resultSet.getDate(15)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDouble(16)).thenReturn(8.5);
        when(resultSet.getString(17)).thenReturn("Titulo");
        when(resultSet.getString(18)).thenReturn("Idioma");
        when(resultSet.getString(19)).thenReturn("Imagen");

        doReturn(capitulo).when(capituloRepository).getPeliculaById(id);

        Capitulo deletedCapitulo = capituloRepository.deletePeliculaById(id);

        assertNotNull(deletedCapitulo);
        assertEquals(id, deletedCapitulo.getIdCont());
        verify(callableStatement).executeUpdate();
    }
}

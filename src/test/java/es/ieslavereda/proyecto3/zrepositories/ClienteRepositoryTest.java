package es.ieslavereda.proyecto3.zrepositories;

import es.ieslavereda.proyecto3.model.Cliente;
import es.ieslavereda.proyecto3.model.MyDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteRepositoryTest {

    @Mock
    private MyDataSource myDataSource;

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private CallableStatement callableStatement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        when(myDataSource.getMyDataSource().getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(callableStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetAll() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("usuario");
        when(resultSet.getString(3)).thenReturn("contrasenya");
        when(resultSet.getString(4)).thenReturn("nombre");
        when(resultSet.getString(5)).thenReturn("apellidos");
        when(resultSet.getString(6)).thenReturn("domicilio");
        when(resultSet.getInt(7)).thenReturn(12345);
        when(resultSet.getString(8)).thenReturn("email");
        when(resultSet.getDate(9)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getInt(10)).thenReturn(123456789);
        when(resultSet.getString(11)).thenReturn("T");
        when(resultSet.getDate(12)).thenReturn(new Date(System.currentTimeMillis()));

        List<Cliente> clientes = clienteRepository.getAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("usuario", clientes.get(0).getUsuario());
    }

    @Test
    public void testGetClienteById() throws SQLException {
        int id = 1;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getString(2)).thenReturn("usuario");
        when(resultSet.getString(3)).thenReturn("contrasenya");
        when(resultSet.getString(4)).thenReturn("nombre");
        when(resultSet.getString(5)).thenReturn("apellidos");
        when(resultSet.getString(6)).thenReturn("domicilio");
        when(resultSet.getInt(7)).thenReturn(12345);
        when(resultSet.getString(8)).thenReturn("email");
        when(resultSet.getDate(9)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getInt(10)).thenReturn(123456789);
        when(resultSet.getString(11)).thenReturn("T");
        when(resultSet.getDate(12)).thenReturn(new Date(System.currentTimeMillis()));

        Cliente cliente = clienteRepository.getClienteById(id);

        assertNotNull(cliente);
        assertEquals(id, cliente.getIdCli());
        assertEquals("usuario", cliente.getUsuario());
    }

    @Test
    public void testDeleteClienteById() throws SQLException {
        int id = 1;
        Cliente cliente = mock(Cliente.class);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getString(2)).thenReturn("usuario");
        when(resultSet.getString(3)).thenReturn("contrasenya");
        when(resultSet.getString(4)).thenReturn("nombre");
        when(resultSet.getString(5)).thenReturn("apellidos");
        when(resultSet.getString(6)).thenReturn("domicilio");
        when(resultSet.getInt(7)).thenReturn(12345);
        when(resultSet.getString(8)).thenReturn("email");
        when(resultSet.getDate(9)).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getInt(10)).thenReturn(123456789);
        when(resultSet.getString(11)).thenReturn("T");
        when(resultSet.getDate(12)).thenReturn(new Date(System.currentTimeMillis()));

        doReturn(cliente).when(clienteRepository).getClienteById(id);

        Cliente deletedCliente = clienteRepository.deleteClienteById(id);

        assertNotNull(deletedCliente);
        assertEquals(id, deletedCliente.getIdCli());
        verify(callableStatement).executeUpdate();
    }

    @Test
    public void testUpdateCliente() throws SQLException {
        Cliente cliente = Cliente.builder()
                .idCli(1)
                .usuario("usuario")
                .contrasenya("contrasenya")
                .nombre("nombre")
                .apellidos("apellidos")
                .domicilio("domicilio")
                .codigoPostal(12345)
                .email("email")
                .fechaNacimiento(new Date(System.currentTimeMillis()))
                .tarjeta(123456789)
                .administrador(true)
                .changedTS(new Date(System.currentTimeMillis()))
                .build();

        when(callableStatement.getInt(1)).thenReturn(1);

        int rowsUpdated = clienteRepository.updateCliente(cliente);

        assertEquals(1, rowsUpdated);
        verify(callableStatement).executeUpdate();
    }

    @Test
    public void testAddCliente() throws SQLException {
        Cliente cliente = Cliente.builder()
                .usuario("usuario")
                .contrasenya("contrasenya")
                .nombre("nombre")
                .apellidos("apellidos")
                .domicilio("domicilio")
                .codigoPostal(12345)
                .email("email")
                .fechaNacimiento(new Date(System.currentTimeMillis()))
                .tarjeta(123456789)
                .administrador(true)
                .changedTS(new Date(System.currentTimeMillis()))
                .build();

        Cliente addedCliente = clienteRepository.addCliente(cliente);

        assertNotNull(addedCliente);
        assertEquals("usuario", addedCliente.getUsuario());
        verify(callableStatement).executeUpdate();
    }

    @Test
    public void testIdentificar() throws SQLException {
        String nombre = "nombre";
        String pass = "pass";
        boolean web = true;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(3)).thenReturn("admin");

        boolean result = clienteRepository.identificar(nombre, pass, web);

        assertFalse(result);

        web = false;
        result = clienteRepository.identificar(nombre, pass, web);

        assertTrue(result);
    }
}

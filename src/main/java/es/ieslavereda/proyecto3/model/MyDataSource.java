package es.ieslavereda.proyecto3.model;

import oracle.jdbc.datasource.impl.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class MyDataSource {
    @Bean
    public static DataSource getMyDataSource(){
        // Propiedades donde tenemos los datos de acceso a la BD
        Properties props = new Properties();
        // Objeto DataSource que devolveremos
        OracleDataSource oracleDS = null;
        try (FileInputStream fis = new FileInputStream("db.properties");) {
            // Cargamos las propiedades
            props.load(fis);
            // Generamos el DataSource con los datos URL, user y passwd necesarios
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return oracleDS;
    }
}

package es.ieslavereda.proyecto3.model;

import oracle.jdbc.datasource.impl.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataSource {
    @Bean
    public static DataSource getMyDataSource() throws SQLException {
        OracleDataSource mySQL = new OracleDataSource();
        mySQL.setURL("jdbc:oracle:thin:@172.28.201.239:1521:xe");
        mySQL.setUser("C##1DAMESPINOSA");
        mySQL.setPassword("password");
        return mySQL;

    }
}

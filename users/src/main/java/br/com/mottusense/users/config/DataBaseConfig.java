package br.com.mottusense.users.config;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    private static final String URL = "jdbc:h2:mem:cereaisdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        // Inicializa Flyway no carregamento da classe
        Flyway flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .load();
        flyway.migrate();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

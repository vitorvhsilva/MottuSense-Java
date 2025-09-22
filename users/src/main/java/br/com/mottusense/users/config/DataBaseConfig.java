package br.com.mottusense.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataBaseConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:db/migration")
                .schemas(user.toUpperCase())
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load();

        flyway.migrate();
        return flyway;
    }
}

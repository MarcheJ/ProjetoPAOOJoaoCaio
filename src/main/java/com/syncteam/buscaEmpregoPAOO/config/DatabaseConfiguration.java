package com.syncteam.buscaEmpregoPAOO.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username:}")
    private String username;
    @Value("${spring.datasource.password:}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource hikariDatasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setPoolName("buscaEmpregoPAOO-db-pool");
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(100000);
        config.setConnectionTestQuery("select 1");

        return new HikariDataSource(config);
    }
}


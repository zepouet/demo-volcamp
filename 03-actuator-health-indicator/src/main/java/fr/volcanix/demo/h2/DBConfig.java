package fr.volcanix.demo.h2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {

  @Value("${smartdbx.ds.driverClassName}")
  String driverClassName;

  @Value("${smartdbx.ds.url}")
  String url;

  @Value("${smartdbx.ds.username}")
  String username;

  @Value("${smartdbx.ds.password}")
  String password;

  @Bean(name = "smartdbx")
  public DataSource smartdb() {

    DriverManagerDataSource ds = new DriverManagerDataSource(url, username, password);
    ds.setDriverClassName(driverClassName);
    return ds;
  }
}

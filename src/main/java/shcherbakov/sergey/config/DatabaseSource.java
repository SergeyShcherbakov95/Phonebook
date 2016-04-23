package shcherbakov.sergey.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseSource {
	@Autowired
	private DatabaseProperties dbProperties;
	
	public void setDatabaseProperties(DatabaseProperties dbProperties){
		this.dbProperties = dbProperties;
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String con = "jdbc:mysql://" + dbProperties.getHost() + ":" + dbProperties.getPort() + "/" + dbProperties.getDbName();
        dataSource.setUrl(con);
        dataSource.setUsername(dbProperties.getLogin());
        dataSource.setPassword(dbProperties.getPassword());

        return dataSource;
    }
}

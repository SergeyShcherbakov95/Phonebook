package shcherbakov.sergey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("file:${lardi.conf}")
public class DatabaseProperties {
	@Value("${type}")
	private String type;
	@Value("${host}")
	private String host;
	@Value("${port}")
	private String port;
	@Value("${dbName}")
	private String dbName;
	@Value("${login}")
	private String login;
	@Value("${password}")
	private String password;
	
	public String getType() {
		return type;
	}
	public String getHost() {
		return host;
	}
	public String getPort() {
		return port;
	}
	public String getDbName() {
		return dbName;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

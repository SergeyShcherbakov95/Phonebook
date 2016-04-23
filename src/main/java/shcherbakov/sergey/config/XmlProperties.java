package shcherbakov.sergey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("file:${lardi.conf}")
public class XmlProperties {
	@Value("${type}")
	private String type;
	@Value("${pathXml}")
	private String pathXml;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPathXml() {
		return pathXml;
	}
	
	public void setPathXml(String pathXml) {
		this.pathXml = pathXml;
	}
}

package shcherbakov.sergey.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

@Configuration
public class XmlUtils {
	@Bean(name = "marshaller")
	public Marshaller getMarshaller(){
		Marshaller marshaller = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Users.class, User.class, Contact.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return marshaller;
	}
	
	@Bean(name = "unmarshaller")
	public Unmarshaller getUnmarshaller(){
		Unmarshaller unmarshaller = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Users.class, User.class, Contact.class);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmarshaller;
	}
}

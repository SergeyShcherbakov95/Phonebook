package shcherbakov.sergey.config;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shcherbakov.sergey.dao.contact.ContactDao;
import shcherbakov.sergey.dao.contact.ContactDaoDatabase;
import shcherbakov.sergey.dao.contact.ContactDaoXml;
import shcherbakov.sergey.dao.user.UserDao;
import shcherbakov.sergey.dao.user.UserDaoDatabase;
import shcherbakov.sergey.dao.user.UserDaoXml;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

@Configuration
public class DBDao {
	@Autowired
	private DatabaseProperties dbProperties;
	
	public void setDatabaseProperties(DatabaseProperties dbProperties){
		this.dbProperties = dbProperties;
	}
	
	@Autowired
	private XmlProperties xmlProperties;
	
	public void setXmlProperties(XmlProperties xmlProperties){
		this.xmlProperties = xmlProperties;
	}
	
	@Bean(name = "userDao")
	public UserDao getUserDao(){
		if ("mysql".equals(dbProperties.getType()))
			return new UserDaoDatabase();
		
		if ("xml".equals(dbProperties.getType())){
			File file = new File(xmlProperties.getPathXml());
			if(!file.exists())
				createFile(xmlProperties.getPathXml());
			return new UserDaoXml();
		}
		
		return null;
	}
	
	@Bean(name = "contactDao")
	public ContactDao getContactDao(){
		if ("mysql".equals(dbProperties.getType()))
			return new ContactDaoDatabase();
		
		if ("xml".equals(dbProperties.getType())){
			File file = new File(xmlProperties.getPathXml());
			if(!file.exists())
				createFile(xmlProperties.getPathXml());
			return new ContactDaoXml();
		}
		
		return null;
	}
	
	public void createFile(String path){
        try {
        	JAXBContext context = JAXBContext.newInstance(Users.class, User.class, Contact.class);
            Marshaller m = context.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Users users = new Users();
            
            File file = new File(path);
            try {
            	file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.marshal(users, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}

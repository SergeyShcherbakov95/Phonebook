package shcherbakov.sergey.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "contact" )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"surname", "name", "patronymic", "mobilePhone", "homePhone", "address", "email"})
public class Contact {
	@XmlAttribute( name = "id")
	private String idContact;
	private String surname;
	private String name;
	private String patronymic;
	private String mobilePhone;
	private String homePhone;
	private String address;
	private String email;
	
	public Contact(){
		
	}
	
	public Contact(String surname, String name, String patronymic, String mobilePhone, String homePhone, String address,
			String email) {
		super();
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.address = address;
		this.email = email;
	}
	
	public Contact(String idContact, String surname, String name, String patronymic, String mobilePhone, String homePhone, String address,
			String email) {
		super();
		this.idContact = idContact;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.address = address;
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == this)
			return true;
		
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		
		Contact that = (Contact) obj;
		
		if(this.getIdContact().equals(that.getIdContact()) &&
				this.getSurname().equals(that.getSurname()) &&
				this.getName().equals(that.getName()) &&
				this.getPatronymic().equals(that.getPatronymic()) &&
				this.getMobilePhone().equals(that.getMobilePhone()) &&
				this.getHomePhone().equals(that.getHomePhone()) &&
				this.getAddress().equals(that.getAddress()) &&
				this.getEmail().equals(that.getEmail()))
			return true;
		
		return false;
	}
	
	public String getIdContact() {
		return idContact;
	}
	public String getSurname() {
		return surname;
	}
	public String getName() {
		return name;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Admin() {
		super();
	}

	public Admin(String userName, String password) {
		super(userName, password);
	}
	
	

}

package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso ({Registered.class, Admin.class})
public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String userName;
	private String password;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Message> sentMessages=new Vector<Message>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Message> recivedMessages=new Vector<Message>();
	
	public User() {
		super();
	}
	
	

	public Vector<Message> getSentMessages() {
		return sentMessages;
	}



	public void setSentMessages(Vector<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}



	public Vector<Message> getRecivedMessages() {
		return recivedMessages;
	}



	public void setRecivedMessages(Vector<Message> recivedMessages) {
		this.recivedMessages = recivedMessages;
	}



	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isCorrectPassword(String pass) {
		return pass.equals(this.getPassword());
	}
	
	public Message addMessageSent(String message, String from, String to) {
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date currentDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		Message m = new Message(message, currentDate, from, to);
		sentMessages.add(m);
		return m;
	}
	
	public void addMessageRecieved(Message m) {
		recivedMessages.add(m);
	}
	
}

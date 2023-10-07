package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Movement implements Serializable {
	

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer moveNumber;
	private double value;
	private String description;
	private Date date;
	@XmlIDREF
	@OneToOne
	private Registered registered;
	
	
	public Movement() {
		super();
	}

	public Movement(double value, String description, Date date, Registered user) {
		super();
		this.value = value;
		this.description = description;
		this.date = date;
		this.registered = user;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Registered getRegistered() {
		return registered;
	}

	public void setRegistered(Registered registered) {
		this.registered = registered;
	}

	public void setMoveNumber(Integer moveNumber) {
		this.moveNumber = moveNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(moveNumber, value, description, date, registered);
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movement other = (Movement) obj;
        if (moveNumber != other.moveNumber)
            return false;
        return true;
    }
	
	public String toString() {
		return description+";  "+date.toString();
	}
	

}

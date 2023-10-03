package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable {
	/**
	 * 
	 */

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer betNumber;
	private double value;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Quote> quotes = new Vector<Quote>();
	@XmlIDREF
	@OneToOne
	private Registered registered;

	public Bet() {
		super();
	}

	public Bet(double value, Vector<Quote> quotes, Registered regis) {
		super();
		this.value = value;
		this.quotes.addAll(quotes);
		this.registered=regis;
	}

	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(Integer betNumber) {
		this.betNumber = betNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double bet) {
		this.value = bet;
	}

	public Vector<Quote> getQuotes() {
		return quotes;
	}

	public void setQuote(Vector<Quote> quotes) {
		this.quotes = quotes;
	}

	public Registered getRegistered() {
		return registered;
	}

	public void setRegistered(Registered registered) {
		this.registered = registered;
	}
	
	

}

package domain;

import java.io.Serializable;
import java.util.Objects;
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
public class Quote implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer quoteNumber;
	private String bet_description;
	private double value;
	@XmlIDREF
	@OneToOne
	private Question question;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Bet> bets=new Vector<Bet>();
	
	public Quote() {
		super();
	}

	public Quote(String bet_description, double value) {
		this.bet_description = bet_description;
		this.value = value;
	}
	
	public Vector<Bet> getBets() {
		return bets;
	}

	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}
	
	public String getBet_description() {
		return bet_description;
	}

	public void setBet_description(String bet_description) {
		this.bet_description = bet_description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(Integer quoteNumber) {
		this.quoteNumber = quoteNumber;
	}
	
	public void addBet(Bet b) {
		this.bets.add(b);
	}
	
	public void removeBet(Bet b) {
		for (int i=0; i<this.bets.size(); i++) {
			Bet bet = this.bets.get(i);
			if(bet.getBetNumber()==b.getBetNumber()) {
				this.bets.remove(i);
			}
		}
	}
	
	public String toString() {
		return value+"; "+bet_description;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(quoteNumber, bet_description, value, question);
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quote other = (Quote) obj;
        if (this.quoteNumber != other.quoteNumber)
            return false;
        return true;
    }
	
	public void removeAllBets() {
		this.bets.removeAllElements();
	}
	
}

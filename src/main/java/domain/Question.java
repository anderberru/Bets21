package domain;

import java.io.*;
import java.util.Objects;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	@XmlIDREF
	@OneToOne
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Quote> quotes=new Vector<Quote>();

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;

		this.event = event;
	}
	
	/**
	 * This method creates a quote with a description and a value
	 * 
	 * @param quote description to be added to the question
	 * @param value of that quote
	 * @return quote
	 */
	public Quote addQuote(String bet_description, double value)  {
        Quote q=new Quote(bet_description, value);
        q.setQuestion(this);
        quotes.add(q);
        return q;
	}
	
	public void removeQuote(Quote quote) {
		for (int i=0; i<this.quotes.size(); i++) {
			Quote quo = this.quotes.get(i);
			if(quo.getQuoteNumber().equals(quote.getQuoteNumber())) {
				this.quotes.remove(i);
			}
		}
	}
	
	/**
	 * This method checks if the quote already exists for that question
	 * 
	 * @param quote that needs to be checked if there exists
	 * @return true if the quote exists and false in other case
	 */
	public boolean DoesQuoteExists(String bet_description, double value)  {	
		for (Quote q:this.getQuotes()){
			if (q.getBet_description().compareTo(bet_description)==0 && q.getValue()==value)
				return true;
		}
		return false;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public Vector<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(Vector<Quote> quotes) {
		this.quotes = quotes;
	}


	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (questionNumber != other.questionNumber)
            return false;
        return true;
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(questionNumber, question, betMinimum, event);
	}
	

	public Vector<Quote> duplicateQuotes(Question newQues){
		Vector<Quote> ret = new Vector<Quote>();
		
		for (Quote q : this.quotes) {
			Quote newQ = new Quote(q.getBet_description(), q.getValue());
			newQ.setQuestion(newQues);
			ret.add(newQ);
		}
		
		return ret;
	}

	
}

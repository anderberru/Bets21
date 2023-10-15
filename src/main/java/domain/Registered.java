package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

import exceptions.BetOnSameQuote;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Registered extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fullName;
	private String DNI;
	private Date birthDate;
	private String payMethod;
	private String email;
	private double money;
	private double bonus;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Movement> movements=new Vector<Movement>();	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Bet> bets=new Vector<Bet>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Registered> followers=new Vector<Registered>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Registered> following=new Vector<Registered>();


	public Registered() {
		super();
	}

	public Registered(String userName, String pass, String fullName, String DNI, Date birthDate, String payMethod, String email, int money) {
		super(userName, pass);
		this.fullName=fullName;
		this.DNI=DNI;
		this.birthDate=birthDate;
		this.payMethod=payMethod;
		this.email=email;
		this.money= money;
		this.bonus = 0;
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public Vector<Movement> getMovements() {
		return movements;
	}

	public void setMovements(Vector<Movement> movements) {
		this.movements = movements;
	}
	
	public Vector<Bet> getBets() {
		return bets;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}
	
	public Vector<Registered> getFollowers() {
		return followers;
	}

	public void setFollowers(Vector<Registered> followers) {
		this.followers = followers;
	}

	public Vector<Registered> getFollowing() {
		return following;
	}

	public void setFollowing(Vector<Registered> following) {
		this.following = following;
	}
	
	


	
	/**
	 * This method creates a movement
	 * 
	 * @param 
	 * @param 
	 * @return Movement
	 */
	public Movement addMovement(double value, String description)  {
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date currentDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		if (description.contains("Bet: -")) {
			value *= -1;
		}
		Movement move = new Movement(value, description, currentDate, this);
        this.movements.add(move);
        return move;
	}
	
	public boolean betOnSameQuote(Vector<Quote> quotes) {
		
		for (Bet b : this.bets) {
			Vector<Quote> betQuotes=b.getQuotes();
			for (Quote quote : quotes) {
				if(betQuotes.contains(quote)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Bet addBet(double value, Vector<Quote> quotes) {
		Bet b = new Bet(value, quotes, this);
		this.bets.add(b);
		return b;
	}
	
	public void removeBet(Bet b) {
		for (int i=0; i<this.bets.size(); i++) {
			Bet bet = this.bets.get(i);
			if(bet.getBetNumber().equals(b.getBetNumber())) {
				this.bets.remove(i);
			}
		}
	}
	
	
	public void addFollower(Registered user) {
		this.followers.add(user);
	}
	
	public void addFollowing(Registered user) {
		this.following.add(user);
	}
	
	public boolean isFollower(Registered user) {
		return this.followers.contains(user);
	}
	
	public boolean isFollowing(Registered user) {
		return this.following.contains(user);
	}
	
	public int getSuccessAmount() {
		int sum = 0;
		for (Movement m: movements) {
			if (m.getDescription().contains("Won bet")) sum++;
		}
		return sum;
	}
	
	public double getSuccessRate() {
		double suc = 0;
		double los = 0;
		for (Movement m: movements) {
			if (m.getDescription().contains("Won bet")) suc++;
			else if (m.getDescription().contains("Lost bet")) los++;
		}
		if (suc + los == 0.0) return 0;
		else return Math.round((suc/(suc+los))*1000)/1000.0;
	}
	
}

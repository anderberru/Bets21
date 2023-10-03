package domain;

	import java.io.Serializable;
import java.util.Date;

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
	public class Message implements Serializable {
		

		@XmlID
		@XmlJavaTypeAdapter(IntegerAdapter.class)
		@Id @GeneratedValue
		private Integer messageNumber;
		private String message;
		private Date date;
		
		@OneToOne
		private String from;

		@OneToOne
		private String to;
		
		public Message() {
			super();
		}

		public Message(String message, Date date, String from, String to) {
			super();
			this.message = message;
			this.date = date;
			this.from = from;
			this.to =to;
		}
		
		public Integer getMessageNumber() {
			return messageNumber;
		}

		public void setMessageNumber(Integer messageNumber) {
			this.messageNumber = messageNumber;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

			
		/*public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		*/
		@Override
		public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Message other = (Message) obj;
	        if (messageNumber != other.messageNumber)
	            return false;
	        return true;
	    }
		
		public String toString() {
			return date+", From: "+from+", To: "+to+"; "+message;
		}
		//getUserName()
        //date.toString()
}

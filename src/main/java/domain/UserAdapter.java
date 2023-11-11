package domain;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends AbstractTableModel{

	private Registered user;

	public UserAdapter(Registered user) {
		this.user = user;
	}

	public Registered getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}

	@Override
	public int getRowCount() {
		return user.getBets().size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return user.getBets().get(rowIndex).getQuotes().get(0).getQuestion().getEvent().getDescription();
		case 1:
			return user.getBets().get(rowIndex).getQuotes().get(0).getQuestion().getQuestion();
		case 2:
			return user.getBets().get(rowIndex).getQuotes().get(0).getQuestion().getEvent().getEventDate();
		case 3:
			return user.getBets().get(rowIndex).getValue();
		}
		return null;
	}
	
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "Event";
		case 1:
			return "Question";
		case 2:
			return "Event Date";
		case 3:
			return "Bet (€)";
		}
		return "";
	}

}

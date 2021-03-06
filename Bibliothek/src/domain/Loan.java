package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;

public class Loan extends Observable{

	private Copy copy;
	private Customer customer;
	private GregorianCalendar pickupDate, returnDate;
	public final static int DAYS_TO_RETURN_BOOK = 30;
	
	@Override
	public int hashCode(){
		return System.identityHashCode(this);
	}

	public Loan(Customer customer, Copy copy) {
		this.copy = copy;
		this.customer = customer;
		pickupDate = new GregorianCalendar();
	}

	public boolean isLent() {
		return returnDate == null;
	}

	public boolean returnCopy() {
		try {
			returnCopy(new GregorianCalendar());
		} catch (IllegalLoanOperationException e) {
			return false;
		}
		return true;
	}

	public void returnCopy(GregorianCalendar returnDate)
			throws IllegalLoanOperationException {
		if (returnDate.before(pickupDate)) {
			throw new IllegalLoanOperationException(
					"Return Date is before pickupDate");
		}
		this.returnDate = returnDate;
		notifyAllObservers();
	}

	public void setPickupDate(GregorianCalendar pickupDate)
			throws IllegalLoanOperationException {
		if (!isLent()) {
			throw new IllegalLoanOperationException("Loan is already retuned");
		}
		this.pickupDate = pickupDate;
		notifyAllObservers();
	}

	public GregorianCalendar getPickupDate() {
		return pickupDate;
	}

	public GregorianCalendar getReturnDate() {
		return returnDate;
	}

	public Copy getCopy() {
		return copy;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "Loan of: " + copy.getTitle().getName() + "\tFrom: "
				+ customer.getLastname() + " " + customer.getFirstname()
				+ "\tPick up: " + getFormattedDate(pickupDate) + "\tReturn: "
				+ getFormattedDate(returnDate) + "\tDays: "
				+ getDaysOfLoanDuration();
	}

	public String getFormattedDate(GregorianCalendar date) {
		if (date != null) {
			DateFormat f = SimpleDateFormat.getDateInstance();
			return f.format(date.getTime());
		}
		return "00.00.00";
	}

	public int getDaysOfLoanDuration() {
		if (returnDate != null)
			return (int) (returnDate.getTimeInMillis() - pickupDate
					.getTimeInMillis())
					/ 1000 / 60 / 60 / 24;
		return -1;
	}
	
	public int getDaysOverdue() {
		if ( !isOverdue() )
			return 0;
		
		GregorianCalendar dueDate = (GregorianCalendar) pickupDate.clone();
		dueDate.add(GregorianCalendar.DAY_OF_YEAR, DAYS_TO_RETURN_BOOK);
		
		return (int) (new GregorianCalendar().getTimeInMillis() - 
				dueDate.getTimeInMillis())/ 1000 /60 /60 /24;
	}
	
	public boolean isOverdue() {
		if ( !isLent() )
			return false;
		
		GregorianCalendar dueDate = (GregorianCalendar) pickupDate.clone();
		dueDate.add(GregorianCalendar.DAY_OF_YEAR, DAYS_TO_RETURN_BOOK);
		dueDate.add(GregorianCalendar.HOUR_OF_DAY, 23);
		dueDate.add(GregorianCalendar.MINUTE, 59);
		dueDate.add(GregorianCalendar.SECOND, 59);
		
		return ( new GregorianCalendar().after(dueDate) );
	}

	private void notifyAllObservers() {
		setChanged();
		notifyObservers();
	}
	
	@Override
	public Loan clone() {
		Loan l = new Loan(customer, copy);
		try {
			l.setPickupDate((GregorianCalendar)pickupDate.clone());
		} catch (IllegalLoanOperationException e) {
			e.printStackTrace();
		}
		return l;
	}
}

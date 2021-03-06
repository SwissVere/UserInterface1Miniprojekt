package view;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Loan;

public class BookTableModel extends AbstractTableModel implements Observer{
	
	private Library lib;
	
	public BookTableModel(Library lib) {
		if(lib == null)
			lib = new Library();
		this.lib = lib;
		lib.addObserver(this);
	}
	
	private String[] columnNames = new String[] {
			"Availalbe", "Title", "Author", "Publisher"
	};
	
	private static final long serialVersionUID = 3924577490865829762L;
	Class[] columnTypes = new Class[] {
		String.class, String.class, String.class, String.class
	};
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
	boolean[] columnEditables = new boolean[] {
		false, false, false, false
	};
	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}
	@Override
	public int getColumnCount() {
		return columnTypes.length;
	}
	@Override
	public int getRowCount() {
		return lib.getBooks().size();
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		List<Book> books = lib.getBooks();
		if(books.size() < 1)
			return "";
		Book book = books.get(arg0);
		switch (arg1) {
		case 0:
			List<Copy> copies = lib.getCopiesOfBook(book);
			if(copies.size() < 1)
				return "0";
			List<Loan> loans = lib.getLentCopiesOfBook(book);
			int available = copies.size() - loans.size();
			if(available < 1) {
				Loan early = null;
				for(Loan l : loans) {
					if(early == null) {
						early = l;
					}
					else {
						if(l.getReturnDate() == null) {
							GregorianCalendar returnDate = (GregorianCalendar) l
									.getPickupDate().clone();
							returnDate.add(GregorianCalendar.DAY_OF_YEAR,
									l.DAYS_TO_RETURN_BOOK);
							
							GregorianCalendar earlyreturnDate = (GregorianCalendar) early
									.getPickupDate().clone();
							earlyreturnDate.add(GregorianCalendar.DAY_OF_YEAR,
									early.DAYS_TO_RETURN_BOOK);
							if(returnDate.compareTo(earlyreturnDate) < 0 ) {
								early = l;
							}
							
						}
						else if(l.getReturnDate().compareTo(early.getReturnDate()) < 0) {
							early = l;
						}
					}
				}
				GregorianCalendar earlyreturnDate;
				if(early.getReturnDate() != null)
					earlyreturnDate = early.getReturnDate();
				else {
					earlyreturnDate = (GregorianCalendar) early
							.getPickupDate().clone();
					earlyreturnDate.add(GregorianCalendar.DAY_OF_YEAR,
							early.DAYS_TO_RETURN_BOOK);
				}

				String date = "";
				SimpleDateFormat fromater = new SimpleDateFormat("dd.MM.yyyy");

				if (earlyreturnDate != null) {
					date = fromater.format(earlyreturnDate.getTime());
				}
				
				return date;
			}
			return "Available";
		case 1:
			return book.getName();
			
		case 2:
			return book.getAuthor();
			
		default:
			return book.getPublisher();
			
		}
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	@Override
	public void update(Observable o, Object arg) {
		fireTableDataChanged();		
	}

}

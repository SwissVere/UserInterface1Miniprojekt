package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Customer;
import domain.Library;
import domain.Loan;

public class LoanDetailTableModel extends AbstractTableModel{

	private Library lib;
	private Customer customer;
	private List<Loan> unsavedLoans = new ArrayList<Loan>();

	public LoanDetailTableModel(Library lib, Customer customer, List<Loan> unsavedLoans) {
		this.lib = lib;
		this.customer = customer;
		this.unsavedLoans = unsavedLoans;
	}

	private String[] columnNames = new String[] { "Copy-ID", "Title", "Author" };

	private static final long serialVersionUID = 3924577490865829762L;
	Class[] columnTypes = new Class[] { String.class, String.class,
			String.class };

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	boolean[] columnEditables = new boolean[] { false, false, false };

	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}

	@Override
	public int getColumnCount() {
		return columnTypes.length;
	}

	@Override
	public int getRowCount() {
		return lib.getLentCustomerLoans(customer).size() + unsavedLoans.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		List<Loan> loans = lib.getLentCustomerLoans(customer);
		loans.addAll(unsavedLoans);
		
		if (loans.size() < 1)
			return "";

		Copy copy = loans.get(arg0).getCopy();

		switch (arg1) {
			case 0:
				return "" + copy.getInventoryNumber();
			case 1:
				return "" + copy.getTitle().getName();
			default:
				return "" + copy.getTitle().getAuthor();
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}

package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Loan;

public class LoanTableModel extends AbstractTableModel {

	private Library lib;

	public LoanTableModel(Library lib) {
		this.lib = lib;
	}

	private String[] columnNames = new String[] {
			"Availalbe", "Title", "Author", "Publisher"
	};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		List<Loan> loans = lib.getLoans();
		if(loans.size() < 1)
			return "";
		Loan loan = loans.get(arg0);
		switch (arg1) {
		case 0:
			return loan.isOverdue();
		case 1:
			return loan.getCopy().getInventoryNumber();
			
		case 2:
			return loan.getCopy().getTitle();
			
		case 3:
			return loan.getReturnDate();
			
		default:
			return loan.getCustomer();
			
		}
	}

}

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Loan;

public class LoanTableModel extends AbstractTableModel implements Observer{

	private Library lib;

	public LoanTableModel(Library lib) {
		this.lib = lib;
		lib.addObserver(this);
	}

	private String[] columnNames = new String[] {
			"Status", "Copy-ID", "Title", "Lend to", "Return loan"
	};

	private static final long serialVersionUID = 3924577490865829762L;
	Class[] columnTypes = new Class[] {
		String.class, String.class, String.class, String.class, Object.class
	};
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
	boolean[] columnEditables = new boolean[] {
		false, false, false, false, true
	};
	@Override
	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}
	@Override
	public int getColumnCount() {
		return columnTypes.length;
	}
	@Override
	public int getRowCount() {
		return lib.getLentLoans().size();
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		List<Loan> loans = lib.getLentLoans();
		
		if(loans.size() < 1)
			return "";
		Loan loan = loans.get(arg0);
		switch (arg1) {
		case 0:
			if(loan.isOverdue()){
				return (String)"Overdue!";
			}
			return (String)"Ok";
		case 1:
			return "" + loan.getCopy().getInventoryNumber();
			
		case 2:
			return loan.getCopy().getTitle().getName();
			
		case 3:
			return (String)loan.getCustomer().getFirstname() + " " + loan.getCustomer().getLastname();
			
		default:
			return loan;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public void update(Observable o, Object arg) {
		fireTableChanged(new TableModelEvent(this));
	}
}

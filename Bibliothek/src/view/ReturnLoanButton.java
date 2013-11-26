package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import domain.Loan;

public class ReturnLoanButton extends JButton  implements TableCellRenderer, TableCellEditor{

	private Loan loan;
	private int selectedRow;
    private int selectedColumn;
	
	public ReturnLoanButton(Loan loan) {
		this.loan = loan;
		
		setText("Return loan");
		setToolTipText("Return the loan");

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getLoan().returnCopy();
			}
		});
		
		setOpaque(true);
	}
	
	public ReturnLoanButton() {
	}
	
	public Loan getLoan() {
		return loan;
	}

	  @Override
	  public Component getTableCellEditorComponent(JTable table,
	      Object value, boolean isSelected, int row, int col) {
	    selectedRow = row;
	    selectedColumn = col;

	    return new ReturnLoanButton((Loan)value);
	  } 

	  @Override
	  public void addCellEditorListener(CellEditorListener arg0) {      
	  } 

	  @Override
	  public void cancelCellEditing() {
	  } 

	  @Override
	  public Object getCellEditorValue() {
	    return "";
	  }

	  @Override
	  public boolean isCellEditable(EventObject arg0) {
	    return true;
	  }

	  @Override
	  public void removeCellEditorListener(CellEditorListener arg0) {
	  }

	  @Override
	  public boolean shouldSelectCell(EventObject arg0) {
	    return true;
	  }

	  @Override
	  public boolean stopCellEditing() {
	    return true;
	  }

	  @Override 
	  public Component getTableCellRendererComponent(JTable table,
	    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
	    return new ReturnLoanButton((Loan)value);
	  }
}

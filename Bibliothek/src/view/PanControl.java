package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Book;
import domain.Library;
import domain.Loan;

public class PanControl extends JPanel {

	private Library lib;
	private Book book;
	private JFrame masterFrame;
	private List<Loan> loans;
	private JButton btnSave;
	
	public PanControl(Library lib, Book book, List<Loan> loans, JFrame masterFrame) {
		this();
		this.lib = lib;
		this.book = book;
		this.loans = loans;
		this.masterFrame = masterFrame;
	}
	
	/**
	 * Create the panel.
	 */
	public PanControl() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(book != null) {
					lib.replaceOrAddBook(book);
				}
				if(loans != null) {
					for(Loan loan : loans) {
						lib.replaceOrAddLoan(loan);
					}
				}
				
				masterFrame.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 0;
		add(btnSave, gbc_btnSave);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// do nothing
				masterFrame.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 0;
		add(btnCancel, gbc_btnCancel);
	}
	
	public void isSaveButtonEnabled(boolean isenabled){
		btnSave.setEnabled(isenabled);
	}


}

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import domain.Loan;

public class ReturnLoanButton extends JButton {

	private Loan loan;
	
	public ReturnLoanButton(Loan loan) {
		this.loan = loan;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getLoan().returnCopy();
			}
		});
	}
	
	public Loan getLoan() {
		return loan;
	}
}

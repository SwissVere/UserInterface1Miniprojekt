package view;


import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import application.LibraryApp;
import domain.Library;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanLoan extends JPanel {
	private JTextField textField;
	private JTable table;
	private Library lib;
	
	/**
	 * Create the panel.
	 */
	public PanLoan(Library library) {
		this.lib = library;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panLoanStatistics = new JPanel();
		panLoanStatistics.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Loan Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panLoanStatistics, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panLoanStatistics.setLayout(gbl_panel);
		
		JLabel lblAmountLoans = new JLabel("Amount Loans");
		GridBagConstraints gbc_lblAmountLoans = new GridBagConstraints();
		gbc_lblAmountLoans.insets = new Insets(0, 0, 0, 5);
		gbc_lblAmountLoans.gridx = 0;
		gbc_lblAmountLoans.gridy = 0;
		panLoanStatistics.add(lblAmountLoans, gbc_lblAmountLoans);
		
		JLabel lblAmountLoansResult = new JLabel(lib.getLoans().size() + "");
		GridBagConstraints gbc_lblAmountLoansResult = new GridBagConstraints();
		gbc_lblAmountLoansResult.insets = new Insets(0, 0, 0, 5);
		gbc_lblAmountLoansResult.gridx = 1;
		gbc_lblAmountLoansResult.gridy = 0;
		panLoanStatistics.add(lblAmountLoansResult, gbc_lblAmountLoansResult);
		
		JLabel lblActualLoans = new JLabel("Actual Loans");
		GridBagConstraints gbc_lblActualLoans = new GridBagConstraints();
		gbc_lblActualLoans.insets = new Insets(0, 0, 0, 5);
		gbc_lblActualLoans.gridx = 3;
		gbc_lblActualLoans.gridy = 0;
		panLoanStatistics.add(lblActualLoans, gbc_lblActualLoans);
		
		JLabel lblActualLoansResult = new JLabel(lib.getLentOutBooks().size() + "");
		GridBagConstraints gbc_lblActualLoansResult = new GridBagConstraints();
		gbc_lblActualLoansResult.insets = new Insets(0, 0, 0, 5);
		gbc_lblActualLoansResult.gridx = 4;
		gbc_lblActualLoansResult.gridy = 0;
		panLoanStatistics.add(lblActualLoansResult, gbc_lblActualLoansResult);
		
		JLabel lblOverdueLoans = new JLabel("Overdue Loans");
		GridBagConstraints gbc_lblOverdueLoans = new GridBagConstraints();
		gbc_lblOverdueLoans.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverdueLoans.gridx = 6;
		gbc_lblOverdueLoans.gridy = 0;
		panLoanStatistics.add(lblOverdueLoans, gbc_lblOverdueLoans);
		
		JLabel lblOverdueLoansResult = new JLabel(lib.getOverdueLoans().size() + "");
		GridBagConstraints gbc_lblOverdueLoansResult = new GridBagConstraints();
		gbc_lblOverdueLoansResult.gridx = 7;
		gbc_lblOverdueLoansResult.gridy = 0;
		panLoanStatistics.add(lblOverdueLoansResult, gbc_lblOverdueLoansResult);
		
		JPanel panLoans = new JPanel();
		panLoans.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Loans", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panLoans, BorderLayout.CENTER);
		panLoans.setLayout(new BorderLayout(0, 0));
		
		JPanel panLoansAdministration = new JPanel();
		panLoansAdministration.setBorder(new TitledBorder(null, "All Loans are in the table below", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panLoans.add(panLoansAdministration, BorderLayout.NORTH);
		GridBagLayout gbl_panLoansAdministration = new GridBagLayout();
		gbl_panLoansAdministration.columnWidths = new int[]{0, 0, 86, 0, 0};
		gbl_panLoansAdministration.rowHeights = new int[]{20, 0};
		gbl_panLoansAdministration.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panLoansAdministration.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panLoansAdministration.setLayout(gbl_panLoansAdministration);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panLoansAdministration.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show only overdue loans");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 0;
		panLoansAdministration.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JButton btnShowSelectedLoans = new JButton("Show selected Loans");
		GridBagConstraints gbc_btnShowSelectedLoans = new GridBagConstraints();
		gbc_btnShowSelectedLoans.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowSelectedLoans.gridx = 2;
		gbc_btnShowSelectedLoans.gridy = 0;
		panLoansAdministration.add(btnShowSelectedLoans, gbc_btnShowSelectedLoans);
		
		
		JScrollPane scrollPanLoansView = new JScrollPane();
		panLoans.add(scrollPanLoansView, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new LoansTableModel(){
			private String[] columnNames = new String[] {
					"Availalbe", "Title", "Author", "Publisher"
			};
		});	
	scrollPanLoansView.setViewportView(table);

	}
}

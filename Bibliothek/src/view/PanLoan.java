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

public class PanLoan extends JPanel {
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanLoan() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panLoanStatistics = new JPanel();
		panLoanStatistics.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Loan Statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panLoanStatistics, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panLoanStatistics.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panLoanStatistics.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panLoanStatistics.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		panLoanStatistics.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 0;
		panLoanStatistics.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 0;
		panLoanStatistics.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 0;
		panLoanStatistics.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
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
		
		JButton btnNewLoan = new JButton("New Loan");
		GridBagConstraints gbc_btnNewLoan = new GridBagConstraints();
		gbc_btnNewLoan.gridx = 3;
		gbc_btnNewLoan.gridy = 0;
		panLoansAdministration.add(btnNewLoan, gbc_btnNewLoan);
		
		JScrollPane scrollPanLoansView = new JScrollPane();
		panLoans.add(scrollPanLoansView, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new AbstractTableModel(){
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
				// TODO Auto-generated method stub
				return null;
			}
		});	
	scrollPanLoansView.setViewportView(table);

	}
}

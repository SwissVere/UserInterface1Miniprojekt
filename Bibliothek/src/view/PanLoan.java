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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;

import com.sun.org.apache.regexp.internal.REUtil;

import application.LibraryApp;
import domain.Library;
import domain.Loan;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.regex.Pattern;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PanLoan extends JPanel {
	private JTextField edSearchField;
	private JTextField textField;
	private JTable tableLoanInventory;
	private JCheckBox cbShowOverdueLoans;
	private Library lib;
	private JLabel lblAmountLoansResult;
	private TableRowSorter<LoanTableModel> sorter;
	private JButton btnShowSelectedLoans;
	
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
		
		lblAmountLoansResult = new JLabel(lib.getLoans().size() + "");
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
		
		edSearchField = new JTextField();
		edSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateTableFilter();
			}
		});
		GridBagConstraints gbc_edSearchField = new GridBagConstraints();
		gbc_edSearchField.insets = new Insets(0, 0, 0, 5);
		gbc_edSearchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_edSearchField.gridx = 0;
		gbc_edSearchField.gridy = 0;
		panLoansAdministration.add(edSearchField, gbc_edSearchField);
		edSearchField.setColumns(10);
		
		cbShowOverdueLoans = new JCheckBox("Show only overdue loans");
		cbShowOverdueLoans.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateTableFilter();
			}
		});
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 0;
		panLoansAdministration.add(cbShowOverdueLoans, gbc_chckbxNewCheckBox);
		
		btnShowSelectedLoans = new JButton("Show selected Loans");
		btnShowSelectedLoans.setEnabled(false);
		btnShowSelectedLoans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = tableLoanInventory.getSelectedRows();
				
				for(int row : selectedRows) {
					String bookName = (String)tableLoanInventory.getValueAt(row, 2);
					String customerId = (String)tableLoanInventory.getValueAt(row, 3);
					customerId = customerId.replaceAll(" ", "");
					LoanDetailView.openNewLoanDetailView(lib.findLoanByBookTitleAndCustomerId(bookName, customerId), lib);
				}
			}
		});
		GridBagConstraints gbc_btnShowSelectedLoans = new GridBagConstraints();
		gbc_btnShowSelectedLoans.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowSelectedLoans.gridx = 2;
		gbc_btnShowSelectedLoans.gridy = 0;
		panLoansAdministration.add(btnShowSelectedLoans, gbc_btnShowSelectedLoans);
		
		JButton btnCreateNewLoan = new JButton("Create new Loan");
		btnCreateNewLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoanDetailView(lib, new Loan(null, null));
			}
		});
		GridBagConstraints gbc_btnCreateNewLoan = new GridBagConstraints();
		gbc_btnCreateNewLoan.gridx = 3;
		gbc_btnCreateNewLoan.gridy = 0;
		panLoansAdministration.add(btnCreateNewLoan, gbc_btnCreateNewLoan);
				
		
		final TableCellRenderer buttonRenderer = new ReturnLoanButton();
		tableLoanInventory = new JTable() {
		    public TableCellRenderer getCellRenderer(int row, int column) {
		        if ((column == 4)) {
		            return buttonRenderer;
		        }
		        // else...
		        return super.getCellRenderer(row, column);
		    }
		    @Override
		    public TableCellEditor getCellEditor(int arg0, int arg1) {
		    	 if ((arg1 == 4)) {
			            return (TableCellEditor)buttonRenderer;
			        }
			        // else...
			        return super.getCellEditor(arg0, arg1);
		    };
		};
		tableLoanInventory.setModel(new LoanTableModel(lib));
		tableLoanInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tableLoanInventory.getSelectedColumn() == -1) {
					btnShowSelectedLoans.setEnabled(false);
				}
				else {
					btnShowSelectedLoans.setEnabled(true);
				}
			}
		});
		
		JScrollPane scrollPanLoansView = new JScrollPane(tableLoanInventory);
		
		panLoans.add(scrollPanLoansView, BorderLayout.CENTER);
		
		
		sorter = new TableRowSorter<LoanTableModel>();
		sorter.setModel((LoanTableModel)tableLoanInventory.getModel());
		tableLoanInventory.setRowSorter(sorter);
		

		scrollPanLoansView.setViewportView(tableLoanInventory);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		tableLoanInventory.updateUI();
		lblAmountLoansResult.setText(lib.getLoans().size() + "");
	}
	

	private void updateTableFilter() {
		try {
		
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			
			filters.add( RowFilter.regexFilter("(?i)" + Pattern.quote(edSearchField.getText()) )); 
			
			if(cbShowOverdueLoans.isSelected()) {
				filters.add( RowFilter.regexFilter("^(?i)Overdue!$"));
			}
			
			RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
			sorter.setRowFilter(rf);

		} catch (java.util.regex.PatternSyntaxException e) {
		        return;
		}	
	}
	
}

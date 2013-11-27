package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import application.LibraryApp;
import domain.Book;
import domain.Copy;
import domain.Customer;
import domain.Library;
import domain.Loan;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import sun.security.action.GetLongAction;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JList;

import java.awt.Panel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoanDetailView extends Observable {

	private JFrame frame;
	private Library library;
	private Copy copy;
	private Loan loan;
	private static HashMap<Loan, LoanDetailView> openViews = new HashMap<Loan, LoanDetailView>();
	private JTextField edCopyID;
	private JComboBox cbCustomers;
	private JLabel lblReturnDate;
	private JLabel lblIsAvailable;
	private LoanDetailTableModel loanDetailTableModel;
	private JButton btnLendCopy;

	Calendar c = new GregorianCalendar();

	Date today = c.getTime(); // the midnight, that's the first second of the
	private JTextField edCustomerID;
	private JTable table;

	// day.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanDetailView window = new LoanDetailView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static LoanDetailView openNewLoanDetailView(Loan loan,
			Library library) {
		LoanDetailView detailView;
		if (openViews.containsKey(loan)) {
			detailView = openViews.get(loan);
		} else {
			detailView = new LoanDetailView(library, loan);
			openViews.put(loan, detailView);
		}
		detailView.frame.toFront();
		detailView.frame.repaint();

		return detailView;
	}

	public LoanDetailView() {
		this(new Library(), new Loan(null, null));
	}

	/**
	 * For existing loans
	 */
	public LoanDetailView(Library lib, Loan loan) {
		this.library = lib;
		this.loan = loan;
		initialize();

		if (loan.getCustomer() != null || loan.getCopy() != null) {
			edCustomerID.setText(loan.getCustomer().getId());
			cbCustomers.setSelectedItem(loan.getCustomer());
			edCopyID.setText("" + loan.getCopy().getInventoryNumber());
		}

		if (loan.getReturnDate() == null) {
			GregorianCalendar returnDate = (GregorianCalendar) loan
					.getPickupDate().clone();
			returnDate.add(GregorianCalendar.DAY_OF_YEAR,
					loan.DAYS_TO_RETURN_BOOK);
			lblReturnDate.setText(loan.getFormattedDate(returnDate));
		} else
			lblReturnDate.setText(loan.getFormattedDate(loan.getReturnDate()));

		frame.setVisible(true);

		if (edCustomerID != null) {
			loanCheck(library, library.getCustomerPerID(edCustomerID.getText()));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				if (openViews.containsKey(loan))
					openViews.remove(loan);
			}
		});
		frame.setTitle("Loan Detail View");
		frame.setMinimumSize(new Dimension(600, 480));
		frame.setBounds(100, 100, 600, 480);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 91, 0, 274, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel panCustomerSelection = new JPanel();
		GridBagConstraints gbc_panCustomerSelection = new GridBagConstraints();
		gbc_panCustomerSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_panCustomerSelection.insets = new Insets(0, 0, 5, 0);
		gbc_panCustomerSelection.gridx = 0;
		gbc_panCustomerSelection.gridy = 0;
		frame.getContentPane().add(panCustomerSelection,
				gbc_panCustomerSelection);
		panCustomerSelection.setBorder(new TitledBorder(new LineBorder(
				new Color(0, 0, 0)), "Customer Selection",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panCustomerSelection = new GridBagLayout();
		gbl_panCustomerSelection.columnWidths = new int[] { 0, 0, 0 };
		gbl_panCustomerSelection.rowHeights = new int[] { 0, 0, 0 };
		gbl_panCustomerSelection.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panCustomerSelection.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		panCustomerSelection.setLayout(gbl_panCustomerSelection);

		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCustomerId = new GridBagConstraints();
		gbc_lblCustomerId.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerId.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerId.gridx = 0;
		gbc_lblCustomerId.gridy = 0;
		panCustomerSelection.add(lblCustomerId, gbc_lblCustomerId);

		edCustomerID = new JTextField();
		edCustomerID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Customer cs = library.getCustomerPerID(edCustomerID.getText());
				if (cs == null)
					return;
				cbCustomers.setSelectedItem(cs);

				loanDetailTableModel = new LoanDetailTableModel(library, cs);
				table.setModel(loanDetailTableModel);
				loanDetailTableModel.fireTableDataChanged();

				if (edCustomerID != null) {
					loanCheck(library, library.getCustomerPerID(edCustomerID.getText()));
				}
			}
		});
		GridBagConstraints gbc_edCustomerID = new GridBagConstraints();
		gbc_edCustomerID.insets = new Insets(0, 0, 5, 0);
		gbc_edCustomerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_edCustomerID.gridx = 1;
		gbc_edCustomerID.gridy = 0;
		panCustomerSelection.add(edCustomerID, gbc_edCustomerID);
		edCustomerID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panCustomerSelection.add(lblNewLabel, gbc_lblNewLabel);

		cbCustomers = new JComboBox();
		cbCustomers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Customer cs = (Customer) cbCustomers.getSelectedItem();
				if (cs == null)
					return;
				edCustomerID.setText(cs.getId());

				loanDetailTableModel = new LoanDetailTableModel(library, cs);
				table.setModel(loanDetailTableModel);
				loanDetailTableModel.fireTableDataChanged();

				if (edCustomerID != null) {
					loanCheck(library, library.getCustomerPerID(edCustomerID.getText()));
				}
			}
		});
		GridBagConstraints gbc_cbCustomers = new GridBagConstraints();
		gbc_cbCustomers.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCustomers.gridx = 1;
		gbc_cbCustomers.gridy = 1;
		panCustomerSelection.add(cbCustomers, gbc_cbCustomers);
		List<Customer> customers = library.getCustomers();
		cbCustomers.setModel(new DefaultComboBoxModel<Customer>(customers
				.toArray(new Customer[customers.size()])));

		JPanel panCopySelection = new JPanel();
		GridBagConstraints gbc_panCopySelection = new GridBagConstraints();
		gbc_panCopySelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_panCopySelection.insets = new Insets(0, 0, 5, 0);
		gbc_panCopySelection.gridx = 0;
		gbc_panCopySelection.gridy = 1;
		frame.getContentPane().add(panCopySelection, gbc_panCopySelection);
		panCopySelection.setBorder(new TitledBorder(new LineBorder(new Color(0,
				0, 0)), "Lend out new copy", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagLayout gbl_panCopySelection = new GridBagLayout();
		gbl_panCopySelection.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panCopySelection.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panCopySelection.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panCopySelection.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panCopySelection.setLayout(gbl_panCopySelection);

		JLabel lblAvailable = new JLabel("Available");
		GridBagConstraints gbc_lblAvailable = new GridBagConstraints();
		gbc_lblAvailable.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailable.gridx = 0;
		gbc_lblAvailable.gridy = 0;
		panCopySelection.add(lblAvailable, gbc_lblAvailable);

		lblIsAvailable = new JLabel("");
		lblIsAvailable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblIsAvailable = new GridBagConstraints();
		gbc_lblIsAvailable.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsAvailable.gridx = 1;
		gbc_lblIsAvailable.gridy = 0;
		panCopySelection.add(lblIsAvailable, gbc_lblIsAvailable);

		JLabel lblCopyId = new JLabel("Copy ID");
		GridBagConstraints gbc_lblCopyId = new GridBagConstraints();
		gbc_lblCopyId.anchor = GridBagConstraints.EAST;
		gbc_lblCopyId.insets = new Insets(0, 0, 5, 5);
		gbc_lblCopyId.gridx = 0;
		gbc_lblCopyId.gridy = 1;
		panCopySelection.add(lblCopyId, gbc_lblCopyId);

		edCopyID = new JTextField();
		GridBagConstraints gbc_edCopyID = new GridBagConstraints();
		gbc_edCopyID.insets = new Insets(0, 0, 5, 5);
		gbc_edCopyID.fill = GridBagConstraints.HORIZONTAL;
		gbc_edCopyID.gridx = 1;
		gbc_edCopyID.gridy = 1;
		panCopySelection.add(edCopyID, gbc_edCopyID);
		edCopyID.setColumns(10);

		btnLendCopy = new JButton("Lend Copy");
		btnLendCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				library.createAndAddLoan(library.getCustomerPerID(edCustomerID
						.getText()), library.getCopyPerId(Long
						.parseLong(edCopyID.getText())));

				loanDetailTableModel.fireTableDataChanged();

				if (edCustomerID != null) {
					loanCheck(library, library.getCustomerPerID(edCustomerID.getText()));
				}
			}
		});
		GridBagConstraints gbc_btnLendCopy = new GridBagConstraints();
		gbc_btnLendCopy.insets = new Insets(0, 0, 5, 0);
		gbc_btnLendCopy.gridx = 2;
		gbc_btnLendCopy.gridy = 1;
		panCopySelection.add(btnLendCopy, gbc_btnLendCopy);

		JLabel lblNewLabel_3 = new JLabel("Back at");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		panCopySelection.add(lblNewLabel_3, gbc_lblNewLabel_3);

		lblReturnDate = new JLabel();
		lblReturnDate.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblReturnDate = new GridBagConstraints();
		gbc_lblReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnDate.gridx = 1;
		gbc_lblReturnDate.gridy = 2;
		panCopySelection.add(lblReturnDate, gbc_lblReturnDate);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 3;
		panCopySelection.add(label, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		if (loan == null)
			loanDetailTableModel = new LoanDetailTableModel(library,
					(Customer) cbCustomers.getSelectedItem());
		else
			loanDetailTableModel = new LoanDetailTableModel(library,
					loan.getCustomer());
		table.setModel(loanDetailTableModel);
		scrollPane.setViewportView(table);

		JPanel panControl = new PanControl(library, null, loan, frame);
		GridBagConstraints gbc_panControl = new GridBagConstraints();
		gbc_panControl.fill = GridBagConstraints.BOTH;
		gbc_panControl.gridx = 0;
		gbc_panControl.gridy = 3;
		frame.getContentPane().add(panControl, gbc_panControl);
	}

	private void loanCheck(Library lib, Customer customer) {
		boolean oneLoanOverdue = false;
		List<Loan> loans = lib.getLentCustomerLoans(customer);
		for(Loan l: loans){
			if(l.isOverdue()){
				oneLoanOverdue = true;
			}
		}
		
		if (lib.getLentCustomerLoans(customer).size() < 3 && !oneLoanOverdue) {
			lblIsAvailable.setText("More Loans possible");
			btnLendCopy.setEnabled(true);
		} else {
			lblIsAvailable.setText("More Loans aren't possible!");
			btnLendCopy.setEnabled(false);
		}
	}

}

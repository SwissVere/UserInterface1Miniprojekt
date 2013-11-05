package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class LoanDetailView {

	private JFrame frame;
	private JTable table;
	private Library library;
	private Copy copy;
	private static HashMap<Copy, LoanDetailView> openViews = new HashMap<Copy, LoanDetailView>();
	private JTextField edCopyID;

	Calendar c = new GregorianCalendar();

	Date today = c.getTime(); // the midnight, that's the first second of the
	private JTextField edCustomerID;
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

	public static LoanDetailView openNewLoanDetailView(Copy copy,
			Library library) {
		LoanDetailView detailView;
		if (openViews.containsKey(copy)) {
			detailView = openViews.get(copy);
		} else {
			detailView = new LoanDetailView(library, copy);
			openViews.put(copy, detailView);
		}
		detailView.frame.toFront();
		detailView.frame.repaint();

		return detailView;
	}

	public LoanDetailView() {
		this(new Library(), new Copy(new Book("")));
	}

	/**
	 * Create the application. For new Loan
	 */
	public LoanDetailView(Library lib, Copy copy) {
		this.library = lib;
		this.copy = copy;
		initialize();
		
		frame.setVisible(true);
	}
	
	/**
	 * For existing loan
	 */
	public LoanDetailView(Library lib, Loan loan) {
		edCustomerID.setText(loan.);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				openViews.remove(copy);
			}
		});
		frame.setTitle("Loan Detail View");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_1, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 434, 0 };
		gbl_panel.rowHeights = new int[] { 54, 61, 10, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Customer Selection", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCustomerId = new GridBagConstraints();
		gbc_lblCustomerId.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerId.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerId.gridx = 0;
		gbc_lblCustomerId.gridy = 0;
		panel_1.add(lblCustomerId, gbc_lblCustomerId);
		
		edCustomerID = new JTextField();
		GridBagConstraints gbc_edCustomerID = new GridBagConstraints();
		gbc_edCustomerID.insets = new Insets(0, 0, 5, 0);
		gbc_edCustomerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_edCustomerID.gridx = 1;
		gbc_edCustomerID.gridy = 0;
		panel_1.add(edCustomerID, gbc_edCustomerID);
		edCustomerID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		JComboBox cbCustomers = new JComboBox();
		GridBagConstraints gbc_cbCustomers = new GridBagConstraints();
		gbc_cbCustomers.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCustomers.gridx = 1;
		gbc_cbCustomers.gridy = 1;
		panel_1.add(cbCustomers, gbc_cbCustomers);
		List<Customer> customers = library.getCustomers();
		cbCustomers.setModel(new DefaultComboBoxModel<Customer>(customers
				.toArray(new Customer[customers.size()])));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Lend out new copy", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_2 = new JLabel("Copy ID");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		edCopyID = new JTextField();
		GridBagConstraints gbc_edCopyID = new GridBagConstraints();
		gbc_edCopyID.insets = new Insets(0, 0, 5, 5);
		gbc_edCopyID.fill = GridBagConstraints.HORIZONTAL;
		gbc_edCopyID.gridx = 1;
		gbc_edCopyID.gridy = 0;
		panel_2.add(edCopyID, gbc_edCopyID);
		edCopyID.setColumns(10);

		JButton btnLendCopy = new JButton("Lend Copy");
		GridBagConstraints gbc_btnLendCopy = new GridBagConstraints();
		gbc_btnLendCopy.insets = new Insets(0, 0, 5, 0);
		gbc_btnLendCopy.gridx = 2;
		gbc_btnLendCopy.gridy = 0;
		panel_2.add(btnLendCopy, gbc_btnLendCopy);

		JLabel lblNewLabel_3 = new JLabel("Back at");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblReturnDate = new JLabel("" + today);
		GridBagConstraints gbc_lblReturnDate = new GridBagConstraints();
		gbc_lblReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnDate.gridx = 1;
		gbc_lblReturnDate.gridy = 1;
		panel_2.add(lblReturnDate, gbc_lblReturnDate);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		panel_2.add(label, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}

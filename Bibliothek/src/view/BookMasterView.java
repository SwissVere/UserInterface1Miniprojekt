package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BookMasterView {

	private JFrame frmSwingingLibar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookMasterView window = new BookMasterView();
					window.frmSwingingLibar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookMasterView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSwingingLibar = new JFrame();
		frmSwingingLibar.setTitle("Swinging Library");
		frmSwingingLibar.setBounds(100, 100, 450, 300);
		frmSwingingLibar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("New label");
		frmSwingingLibar.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		frmSwingingLibar.getContentPane().add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSwingingLibar.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panBooks = new JPanel();
		tabbedPane.addTab("Books", null, panBooks, null);
		panBooks.setLayout(new BorderLayout(0, 0));
		
		JPanel panInventoryStatistics = new JPanel();
		panInventoryStatistics.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Inventory statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panBooks.add(panInventoryStatistics, BorderLayout.NORTH);
		GridBagLayout gbl_panInventoryStatistics = new GridBagLayout();
		gbl_panInventoryStatistics.columnWidths = new int[]{116, 0, 109, 0, 94, 0};
		gbl_panInventoryStatistics.rowHeights = new int[]{23, 0};
		gbl_panInventoryStatistics.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panInventoryStatistics.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panInventoryStatistics.setLayout(gbl_panInventoryStatistics);
		
		JLabel lblCountBooks = new JLabel("New label");
		GridBagConstraints gbc_lblCountBooks = new GridBagConstraints();
		gbc_lblCountBooks.anchor = GridBagConstraints.WEST;
		gbc_lblCountBooks.insets = new Insets(0, 0, 0, 5);
		gbc_lblCountBooks.gridx = 0;
		gbc_lblCountBooks.gridy = 0;
		panInventoryStatistics.add(lblCountBooks, gbc_lblCountBooks);
		
		JLabel lblCountCopies = new JLabel("New label");
		GridBagConstraints gbc_lblCountCopies = new GridBagConstraints();
		gbc_lblCountCopies.anchor = GridBagConstraints.WEST;
		gbc_lblCountCopies.insets = new Insets(0, 0, 0, 5);
		gbc_lblCountCopies.gridx = 2;
		gbc_lblCountCopies.gridy = 0;
		panInventoryStatistics.add(lblCountCopies, gbc_lblCountCopies);
		
		JPanel panBookInventory = new JPanel();
		panBookInventory.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Book-Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panBooks.add(panBookInventory, BorderLayout.CENTER);
		panBookInventory.setLayout(new BorderLayout(0, 0));
		
		JPanel panBookInventoryMenu = new JPanel();
		panBookInventory.add(panBookInventoryMenu, BorderLayout.NORTH);
		GridBagLayout gbl_panBookInventoryMenu = new GridBagLayout();
		gbl_panBookInventoryMenu.columnWidths = new int[]{116, 0, 109, 0, 94, 0};
		gbl_panBookInventoryMenu.rowHeights = new int[]{23, 0};
		gbl_panBookInventoryMenu.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panBookInventoryMenu.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panBookInventoryMenu.setLayout(gbl_panBookInventoryMenu);
		
		JLabel lblCountSelected = new JLabel("New label");
		lblCountSelected.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCountSelected = new GridBagConstraints();
		gbc_lblCountSelected.anchor = GridBagConstraints.WEST;
		gbc_lblCountSelected.insets = new Insets(0, 0, 0, 5);
		gbc_lblCountSelected.gridx = 0;
		gbc_lblCountSelected.gridy = 0;
		panBookInventoryMenu.add(lblCountSelected, gbc_lblCountSelected);
		
		JButton btnShowSelection = new JButton("Show selection");
		GridBagConstraints gbc_btnShowSelection = new GridBagConstraints();
		gbc_btnShowSelection.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnShowSelection.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowSelection.gridx = 2;
		gbc_btnShowSelection.gridy = 0;
		panBookInventoryMenu.add(btnShowSelection, gbc_btnShowSelection);
		
		JButton btnAddNewBook = new JButton("Add new book");
		btnAddNewBook.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAddNewBook.gridx = 4;
		gbc_btnAddNewBook.gridy = 0;
		panBookInventoryMenu.add(btnAddNewBook, gbc_btnAddNewBook);
		
		JList listBookInventory = new JList();
		panBookInventory.add(listBookInventory, BorderLayout.CENTER);
	}

}

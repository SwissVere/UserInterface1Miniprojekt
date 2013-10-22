package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Loan;

public class PanBook extends JPanel {

	private JTable tableBookInventory;
	private JButton btnAddNewBook;
	private JButton btnShowSelection;
	private Library lib;

	/**
	 * Create the panel.
	 */
	public PanBook(Library library) {
		this.lib = library;
		setLayout(new BorderLayout(0, 0));
		JPanel panInventoryStatistics = new JPanel();
		panInventoryStatistics.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Inventory statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panInventoryStatistics, BorderLayout.NORTH);
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
		add(panBookInventory, BorderLayout.CENTER);
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
		
		btnShowSelection = new JButton("Show selection");
		btnShowSelection.setEnabled(false);
		btnShowSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tableBookInventory.get
				lib.findByBookTitle("");
				BookDetailView detailView = new BookDetailView();
			}
		});
		GridBagConstraints gbc_btnShowSelection = new GridBagConstraints();
		gbc_btnShowSelection.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnShowSelection.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowSelection.gridx = 2;
		gbc_btnShowSelection.gridy = 0;
		panBookInventoryMenu.add(btnShowSelection, gbc_btnShowSelection);
		
		tableBookInventory = new JTable();
		tableBookInventory.setModel(new AbstractTableModel(){
			private String[] columnNames = new String[] {
					"Availalbe", "Title", "Author", "Publisher"
			};
			
			private static final long serialVersionUID = 3924577490865829762L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			@Override
			public int getColumnCount() {
				return columnTypes.length;
			}
			@Override
			public int getRowCount() {
				System.out.println(lib.getBooks().size());
				// TODO Auto-generated method stub
				return lib.getBooks().size();
			}
			@Override
			public Object getValueAt(int arg0, int arg1) {
				System.out.println(arg0);
				List<Book> books = lib.getBooks();
				if(books.size() < 1)
					return "";
				Book book = books.get(arg0);
				switch (arg1) {
				case 0:
					List<Copy> copies = lib.getCopiesOfBook(book);
					List<Loan> loans = lib.getLentCopiesOfBook(book);
					int available = copies.size() - loans.size();
					if(available < 1) {
						Loan early = null;
						for(Loan l : loans) {
							if(early == null) {
								early = l;
							}
							else {
								if(l.getReturnDate().compareTo(early.getReturnDate()) < 0) {
									early = l;
								}
							}
						}
						return early.getReturnDate().toString();
					}
					return Integer.toString(available);
				case 1:
					return book.getName();
					
				case 2:
					return book.getAuthor();
					
				default:
					return book.getPublisher();
					
				}
			}
			@Override
			public String getColumnName(int column) {
				return columnNames[column];
			}
		});
		tableBookInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBookInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tableBookInventory.getSelectedColumn() == -1) {
					btnShowSelection.setEnabled(false);
				}
				else {
					btnShowSelection.setEnabled(true);
				}
			}
		});
		
		JScrollPane panBookInventoryTable = new JScrollPane();
		panBookInventoryTable.add(tableBookInventory);
		
		panBookInventory.add(panBookInventoryTable, BorderLayout.CENTER);
		
		btnAddNewBook = new JButton("Add new book");
		btnAddNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookDetailView detailView = new BookDetailView();
			}
		});
		btnAddNewBook.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnAddNewBook = new GridBagConstraints();
		gbc_btnAddNewBook.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAddNewBook.gridx = 4;
		gbc_btnAddNewBook.gridy = 0;
		panBookInventoryMenu.add(btnAddNewBook, gbc_btnAddNewBook);
	}

}
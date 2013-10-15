package view;

import java.awt.EventQueue;
import java.util.Observable;

import javax.swing.JFrame;

import domain.Book;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BookDetailView extends Observable{

	private JFrame frame;

	private Book book;
	// needed for UI-Designer
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetailView window = new BookDetailView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public BookDetailView() {
		this(new Book(""));
	}
	
	public BookDetailView(Book book) {
		this.book = book;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Book detail view");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panBookInformation = new JPanel();
		panBookInformation.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Book Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panBookInformation = new GridBagConstraints();
		gbc_panBookInformation.insets = new Insets(0, 0, 5, 0);
		gbc_panBookInformation.fill = GridBagConstraints.BOTH;
		gbc_panBookInformation.gridx = 0;
		gbc_panBookInformation.gridy = 0;
		frame.getContentPane().add(panBookInformation, gbc_panBookInformation);
		
		JPanel panCopies = new JPanel();
		panCopies.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Copies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panCopies = new GridBagConstraints();
		gbc_panCopies.fill = GridBagConstraints.BOTH;
		gbc_panCopies.gridx = 0;
		gbc_panCopies.gridy = 1;
		frame.getContentPane().add(panCopies, gbc_panCopies);
		panCopies.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panCopies.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{116, 0, 109, 0, 109, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCopyCount = new JLabel("Count: 0");
		GridBagConstraints gbc_lblCopyCount = new GridBagConstraints();
		gbc_lblCopyCount.insets = new Insets(0, 0, 0, 5);
		gbc_lblCopyCount.gridx = 0;
		gbc_lblCopyCount.gridy = 0;
		panel.add(lblCopyCount, gbc_lblCopyCount);
		
		JButton btnDeleteSelected = new JButton("Delete Selected");
		GridBagConstraints gbc_btnDeleteSelected = new GridBagConstraints();
		gbc_btnDeleteSelected.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteSelected.gridx = 2;
		gbc_btnDeleteSelected.gridy = 0;
		panel.add(btnDeleteSelected, gbc_btnDeleteSelected);
		
		JButton btnAddCopy = new JButton("Add copy");
		GridBagConstraints gbc_btnAddCopy = new GridBagConstraints();
		gbc_btnAddCopy.gridx = 4;
		gbc_btnAddCopy.gridy = 0;
		panel.add(btnAddCopy, gbc_btnAddCopy);
		
		JList listCopies = new JList();
		panCopies.add(listCopies, BorderLayout.CENTER);
	}
}

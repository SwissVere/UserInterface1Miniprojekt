package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JList;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import domain.Book;
import domain.Copy;
import domain.Library;
import domain.Loan;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Console;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MasterView{

	private JFrame frmSwingingLibar;
	private Library lib;
	private boolean isBuilder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterView window = new MasterView(new Library(), true);
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
	public MasterView(Library lib, boolean isBuilder) {
		this.lib = lib;
		this.isBuilder = isBuilder;
		initialize();
		frmSwingingLibar.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSwingingLibar = new JFrame();
		frmSwingingLibar.setMinimumSize(new Dimension( 800, 500));
		frmSwingingLibar.setTitle("Swinging Library");
		frmSwingingLibar.setBounds(100, 100, 450, 300);
		frmSwingingLibar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		frmSwingingLibar.getContentPane().add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSwingingLibar.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		if(isBuilder) {
			tabbedPane.addTab("Books", new PanBook(new Library()));
			tabbedPane.addTab("Loans", new PanLoan(new Library()));
		}
		else {
			tabbedPane.addTab("Books", new PanBook(lib));
			tabbedPane.addTab("Loans", new PanLoan(lib));
		}
	}
}

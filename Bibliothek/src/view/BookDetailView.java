package view;

import java.awt.EventQueue;
import java.util.Observable;

import javax.swing.JFrame;

import domain.Book;

public class BookDetailView extends Observable{

	private JFrame frame;

	private Book book;
	
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}

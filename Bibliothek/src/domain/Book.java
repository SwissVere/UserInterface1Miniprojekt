package domain;

import java.util.Observable;

public class Book extends Observable{
	
	private String title, author, publisher;
	private Shelf shelf;
	private boolean isNew = true;
	
	public Book(String name) {
		this.title = name;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String autor) {
		this.author = autor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Shelf getShelf() {
		return shelf;
	}
	
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	@Override
	public String toString() {
		return title + ", " + author + ", " + publisher;
	}
}

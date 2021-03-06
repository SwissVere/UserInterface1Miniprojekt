package domain;

import java.util.Observable;

public class Book extends Observable{
	
	private String title, author, publisher;
	private Shelf shelf;
	private boolean isNew = true;
	
	@Override
	public int hashCode()
	{
		return System.identityHashCode(this);
	}
	
	public Book(String name) {
		this.title = name;
		this.author = "";
		this.publisher = "";
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
		setChanged();
		notifyObservers(this);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String autor) {
		this.author = autor;
		setChanged();
		notifyObservers(this);
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		setChanged();
		notifyObservers(this);
	}
	
	public Shelf getShelf() {
		return shelf;
	}
	
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
		setChanged();
		notifyObservers(this);
	}
	
	public boolean isNew() {
		return isNew;
	}

	@Deprecated
	public void setNew(boolean isNew) {
		this.isNew = isNew;
		setChanged();
		notifyObservers(this);
	}

	@Override
	public String toString() {
		return title + ", " + author + ", " + publisher;
	}
	
	@Override
	public Book clone() {
		Book b = new Book(title);
		b.setAuthor(author);
		b.setPublisher(publisher);
		b.setShelf(shelf);
		
		return b;
	}
}

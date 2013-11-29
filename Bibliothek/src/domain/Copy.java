package domain;

public class Copy {
	
	public enum Condition {NEW, GOOD, DAMAGED, WASTE, LOST, DELETED }
	
	public static long nextInventoryNumber = 1;
	
	private final long inventoryNumber;
	private Book book;
	private Condition condition;
	
	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}
	
	public Copy(Book title) {
		this.book = title;
		inventoryNumber = nextInventoryNumber++;
		condition = Condition.NEW;
	}

	public Book getTitle() {
		return book;
	}

	public void setTitle(Book b) {
		book = b;
	}
	
	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public long getInventoryNumber() {
		return inventoryNumber;
	}
	
	@Override
	public String toString() {
		return book.getName() + " " + condition.toString() + " " + inventoryNumber;
	}
}

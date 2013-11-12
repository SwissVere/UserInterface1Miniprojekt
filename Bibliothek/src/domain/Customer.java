package domain;

public class Customer {
	
	private String lastname, firstname, street, city, id;
	private int zip;

	public Customer(String lastname, String firstname) {
		this.id = firstname + lastname;
		this.lastname = lastname;
		this.firstname = firstname;
	}
	
	public void setAdress(String street, int zip, String city) {
		this.street = street;
		this.zip = zip;
		this.city = city;
	}

	public String getName() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return lastname + " " + firstname + " , " + street + " , " + zip + " " + city;
	}

}

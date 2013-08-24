package ufpb.project.rescsystem.modules;

public class Facility {
	
	private String name;
	private String phone;
	private String address;
	private boolean available;

	public Facility (String name) {
		this.name = name;
	}
	
	public Facility(String name, String phone, String address, boolean available) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public String toString() {
		return getPhone() +"\n" +getAddress() +"\n" +getName(); 
		// Resources.getSystem().getString(R.string.app_name);
	}
	
}

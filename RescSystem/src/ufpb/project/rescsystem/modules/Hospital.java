package ufpb.project.rescsystem.modules;

import android.content.res.Resources;
import ufpb.project.rescsystem.R;

public class Hospital extends Entity {

	private String phone;
	private String address;
	private boolean available;
	
	public Hospital(String name, String phone, String address, boolean available) {
		super(name);
		this.phone = phone;
		this.address = address;
		this.available = available;
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
		return getPhone() +"\n" +getAddress(); 
		// Resources.getSystem().getString(R.string.app_name);
	}
	
}

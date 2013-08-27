package ufpb.project.rescsystem.modules;

import com.google.android.gms.maps.model.LatLng;

public class Hospital extends Facility {

	public Hospital(String name, String phone, String address, boolean available, LatLng placeLL) {
		super(name, phone, address, available, placeLL);
	}

}

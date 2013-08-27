package ufpb.project.rescsystem.modules;

import com.google.android.gms.maps.model.LatLng;

public class Shelter extends Facility {

	public Shelter(String name, String phone, String address, boolean available, LatLng latLng) {
		super(name, phone, address, available, latLng);
	}

}

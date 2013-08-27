package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Shelter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SheltersFragment extends MyListFragment {
	
	public void onCreate(Bundle savedInstanceState) {
		
		setData(new ArrayList<Facility>());
		getData().add(new Shelter("Abrigo1", "Rua 1", "9999", true,
				new LatLng(-7.132298, -34.886339)));
		getData().add(new Shelter("Abrigo2", "Rua 2", "9998", true,
				new LatLng(-7.159040, -34.881961)));
		getData().add(new Shelter("Abrigo3", "Rua 3", "9998", true,
				new LatLng(-7.216691, -34.876125)));
		
		setLayoutId(R.layout.fragment_shelters, R.id.container2);
		
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		return super.onCreateView(infltr, container, savedState);
	}
	
	public void onStart() {
		super.onStart();
		onMapReady();
	}
	
}

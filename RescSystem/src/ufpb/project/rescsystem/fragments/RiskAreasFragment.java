package ufpb.project.rescsystem.fragments;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RiskAreasFragment extends Fragment {
	
	MapFragment map;
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		View v = infltr.inflate(R.layout.fragment_riskareas, container, true);
		
		//map = MapFragment.newInstance();
		
		return v;
	}
	
}

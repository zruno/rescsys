package ufpb.project.rescsystem.fragments;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HospitalsFragment extends MyListFragment {

	private GoogleMap mMap;
	private SupportMapFragment mMapFragment;
	
	MapFragment mapFrag;
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		//ft.add(R.layout.fragment_hospitais, mapFrag);
		//map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		super.setLayoutId(R.layout.fragment_hospitais, R.id.mapHospitals);
		
		return super.onCreateView(infltr, container, savedState);	
		//return infltr.inflate(R.layout.fragment_hospitais, container, true);
	}
	}
	
//	ublic void setTextView(int selected) {
//		TextView textInfo = (TextView) getActivity().findViewById(R.id.textInfo);
//		textInfo.setText(getData().get(selected).toString());
//	}
	
}
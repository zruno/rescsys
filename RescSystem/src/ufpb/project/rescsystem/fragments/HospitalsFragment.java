package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.maputils.GetPlacesTask;
import ufpb.project.rescsystem.modules.Facility;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HospitalsFragment extends MyListFragment {
	
	public void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.fragment_hospitais, R.id.container);
		
		LocationManager locMan = (LocationManager) 
				getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		double lat = lastLoc.getLatitude();
		double lng = lastLoc.getLongitude();
		
		String placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
			    "json?location="+lat+","+lng+
			    //"&radius=10000"+
			    "&sensor=true" +
			    "&types=hospital"+
			    "&rankby=distance"+ "&name=hospital"+
			    "&key=AIzaSyCxntrIHVBoFo_Ndv56cVxyqaxqPDb3CXU";
		
		setData(new ArrayList<Facility>());
		new GetPlacesTask(getData(), this, "hospital").execute(placesSearchStr);
		
		setColor(Color.RED);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {		
		return super.onCreateView(infltr, container, savedState);
	}
	
}
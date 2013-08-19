package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import android.content.Context;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment implements OnItemClickListener {

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	
	private ArrayList<Facility> data;
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		View v = infltr.inflate(R.layout.fragment_hospitais, container, true);
		return v;
	}
	
	public void onStart() {
		super.onStart();
		
		
		SupportMapFragment map = SupportMapFragment.newInstance();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, map);
		ft.commit();
		
	}
	
	public void setListView(Context context) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
			android.R.layout.simple_list_item_1, getNames());
		setListAdapter(adapter);
	}
		
	public void setData(ArrayList<Facility> data) {
		this.data = data;
	}
	
	public ArrayList<Facility> getData() {
		return data;
	}
	
	private String[] getNames() {
		String[] names = new String[data.size()];
		int i = 0;
		ArrayList<Facility> newData = (ArrayList<Facility>) data;
		for (Facility a: newData) {
			names[i] = a.getName();
			i++;
		}
		return names;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}
	
}

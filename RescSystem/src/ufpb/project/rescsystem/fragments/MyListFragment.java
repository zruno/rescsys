package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	private SupportMapFragment map;
	private ArrayList<Facility> data;
	
	public interface mapReadyListener {
		void onMapReady();
	}
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		View v = infltr.inflate(R.layout.fragment_hospitais, container, true);
		
		return v;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		map = GMapFragment.gMapInstance();
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, map);
		ft.commit();
		
		setListView();
	}
	
	public void onResume() {
		super.onResume();
		// GoogleMap gmap = map.getMap();
		//gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
		
		
	}
	
	public void setListView() {
		
		ArrayList<Facility> data = new ArrayList<Facility>();
		setData(data);
		data.add(new Facility("Hospital Universitário Lauro Wanderley, João Pessoa - Paraiba",
				"(83) 3216-7042", "Campus Universitário 1 Cidade Universitária, " 
						+"João Pessoa - PB 58050-000", true));
		data.add(new Facility("Hospital 2", "55 83 0800", "Rua Avenida", true));
		data.add(new Facility("Hospital 3", "55 83 0800", "Rua Avenida", true));
		data.add(new Facility("Hospital 4", "55 83 0800", "Rua Avenida", true));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity().getBaseContext(), android.R.layout.simple_list_item_1,
				getNames());
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
		ArrayList<Facility> newData = data;
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

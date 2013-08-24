package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.fragments.GMapFragment.MapListener;
import ufpb.project.rescsystem.modules.Facility;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment 
			implements OnItemClickListener, MapListener {

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	private GMapFragment map;
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
		
		map = GMapFragment.gMapInstance(this);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, map);
		ft.commit();
	}
	
	public void onStart() {
		super.onStart();
	}
	
	public void onResume() {
		super.onResume();
	}
	
	public void setListView() {
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
		for (Facility a: data) {
			names[i] = a.getName();
			System.out.println(a.getName() +"zeca");
			i++;
		}
		return names;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}

	@Override
	public void onMapReady() {
		data = map.getPlaces();
		setListView();
		
	}
	
}

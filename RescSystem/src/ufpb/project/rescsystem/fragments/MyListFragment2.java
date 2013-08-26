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

public class MyListFragment2 extends ListFragment 
			implements ufpb.project.rescsystem.fragments.GMapFragment2.MapListener {

	private GMapFragment2 map;
	private ArrayList<Facility> data;
	private int fragmentId;
	private int mapContainerId;
	
	public void setLayoutId(int fragmentId, int containerId) {
		this.fragmentId = fragmentId;
		this.mapContainerId = containerId;
	}

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		View v = infltr.inflate(fragmentId, container, true);
		return v;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		map = GMapFragment2.gMapInstance(this);
		
		data = new ArrayList<Facility>();
		data.add(new Facility("Abrigo1", "Rua 1", "9999", true,
				new LatLng(-7.132298, -34.886339)));
		data.add(new Facility("Abrigo2", "Rua 2", "9998", true,
				new LatLng(-7.159040, -34.881961)));
		data.add(new Facility("Abrigo3", "Rua 3", "9998", true,
				new LatLng(-7.216691, -34.876125)));
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(mapContainerId, map);
		ft.commit();
		
	}
	
	public void onStart() {
		super.onStart();
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				map.setHighlightedMarker(arg2);
				map.setRouteToMarker(arg2);
			}
		});
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
			i++;
		}
		return names;
	}

	@Override
	public void onMapReady() {
		map.addMarkers(data);
		setListView();
	}
	
}

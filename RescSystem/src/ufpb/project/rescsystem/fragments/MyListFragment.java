package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

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
			implements MapListener {

	private GMapFragment map;
	private ArrayList<Facility> data;
	
	private int fragmentId;
	private int mapContainerId;
	
	private int mapReadyFlag = 0;
	
	public void setLayoutId(int fragmentId, int containerId) {
		this.fragmentId = fragmentId;
		this.mapContainerId = containerId;
	}

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		return infltr.inflate(fragmentId, container, true);
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		map = GMapFragment.gMapInstance(this, data);
		
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
		mapReadyFlag++;
		if (mapReadyFlag == 2) {
			setListView();
			map.addMarkers();
			mapReadyFlag = 0;
		}
	}
	
}

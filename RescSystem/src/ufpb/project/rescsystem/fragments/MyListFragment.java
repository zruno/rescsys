package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.R;
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

public class MyListFragment extends ListFragment implements OnItemClickListener {

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
		
		map = GMapFragment.gMapInstance();
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, map);
		ft.commit();
	}
	
	public void onStart() {
		super.onStart();
	}
	
	public void onResume() {

		data = map.getPlaces();
		setListView();
		super.onResume();
	}
	
	public void setListView() {
		System.out.println(data.size() +"zeca-1");
		for (Facility f: data) System.out.println(f +"defron");
		//ArrayList<Facility> data = new ArrayList<Facility>();
//		data.add(new Facility("Hospital Universitário Lauro Wanderley, João Pessoa - Paraiba",
//				"(83) 3216-7042", "Campus Universitário 1 Cidade Universitária, " 
//						+"João Pessoa - PB 58050-000", true));
		String[] array = {"sad", "news"};
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
	
}

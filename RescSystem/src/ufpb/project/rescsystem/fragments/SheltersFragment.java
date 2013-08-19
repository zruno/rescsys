package ufpb.project.rescsystem.fragments;

import com.google.android.gms.maps.MapView;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SheltersFragment extends Fragment implements OnItemClickListener {

	private MapView map;
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		//super.setLayoutId(R.layout.fragment_shelters, R.id.mapShelters);
		// return super.onCreateView(infltr, container, savedState);
		return infltr.inflate(R.layout.fragment_riskareas, container, true);

	}
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}
	
}

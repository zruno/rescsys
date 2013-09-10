package ufpb.project.rescsystem.fragments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.ContentParser;
import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Shelter;
import utils.Utilities;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SheltersFragment extends MyListFragment {
	
	public void onCreate(Bundle savedInstanceState) {
		
		InputStream input = getActivity().getResources().openRawResource(R.raw.shelters);
		setData(new ContentParser().parseResources(input));
		
		setColor(Color.argb(255, 12, 174, 0));
		setLayoutId(R.layout.fragment_shelters, R.id.container2);
		
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		return super.onCreateView(infltr, container, savedState);
	}
	
	public void onStart() {
		super.onStart();
		onMapReady();
	}
	
}

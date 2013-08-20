package ufpb.project.rescsystem.fragments;


import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HospitalsFragment extends MyListFragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
			
		return infltr.inflate(R.layout.fragment_hospitais, container, true);
	
	}
	
}
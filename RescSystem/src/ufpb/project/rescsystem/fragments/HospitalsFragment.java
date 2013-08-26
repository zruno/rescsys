package ufpb.project.rescsystem.fragments;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HospitalsFragment extends MyListFragment {
	
	public void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.fragment_hospitais, R.id.container);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {		
		return super.onCreateView(infltr, container, savedState);
	}
	
}
package ufpb.project.rescsystem.fragments;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SheltersFragment extends MyListFragment2 {
	
	public void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.fragment_shelters, R.id.container2);
		super.onCreate(savedInstanceState);

	}
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		return super.onCreateView(infltr, container, savedState);
	}
	
}

package ufpb.project.rescsystem.fragments;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RoutesFragment extends MyListFragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		super.setLayoutId(R.layout.fragment_routes);
		return super.onCreateView(infltr, container, savedState);
	}
	
}

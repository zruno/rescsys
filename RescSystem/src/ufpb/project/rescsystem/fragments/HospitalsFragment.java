package ufpb.project.rescsystem.fragments;


import java.util.ArrayList;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Hospital;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HospitalsFragment extends MyListFragment {
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		super.setLayoutId(R.layout.fragment_hospitais);
		return super.onCreateView(infltr, container, savedState);	
	}
	
}
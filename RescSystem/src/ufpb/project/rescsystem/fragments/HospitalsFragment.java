package ufpb.project.rescsystem.fragments;


import java.util.ArrayList;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Entity;
import ufpb.project.rescsystem.modules.Hospital;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class HospitalsFragment extends MyListFragment {
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		return super.onCreateView(infltr, container, savedState);	
	}
	
	public void setTextView() {
		TextView textInfo = (TextView) getActivity().findViewById(R.id.textInfo);
		textInfo.setText(getData().get(0).toString());
		textInfo.setGravity(Gravity.TOP);
	}

}
package ufpb.project.rescsystem.fragments;


import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HospitalsFragment extends MyListFragment implements OnItemClickListener {
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		super.setLayoutId(R.layout.fragment_hospitais);
		return super.onCreateView(infltr, container, savedState);	
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		setTextView(arg2);
	}
	
	public void setTextView(int selected) {
		TextView textInfo = (TextView) getActivity().findViewById(R.id.textInfo);
		textInfo.setText(getData().get(selected).toString());
	}
	
}
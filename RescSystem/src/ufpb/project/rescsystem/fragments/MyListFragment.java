package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import ufpb.project.rescsystem.modules.Facility;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment implements OnItemClickListener {

	private ArrayList<Facility> data;
	private int layoutId;
	
	 public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		View v = infltr.inflate(layoutId, container, true);
		return v;
	}
	
	public void setListView(Context context) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
			android.R.layout.simple_list_item_1, getNames());
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
		ArrayList<Facility> newData = (ArrayList<Facility>) data;
		for (Facility a: newData) {
			names[i] = a.getName();
			i++;
		}
		return names;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}
	
}

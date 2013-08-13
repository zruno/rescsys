package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Entity;
import ufpb.project.rescsystem.modules.Hospital;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment {

	private ArrayList<Entity> data;
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		return infltr.inflate(R.layout.fragment_hospitais, container, true);
	}
		
	public void setListView(Context context) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
			android.R.layout.simple_list_item_1, getNames());
		setListAdapter(adapter);
	}
	
	public void setData(ArrayList<Entity> data) {
		this.data = data;
	}
	
	public ArrayList<Entity> getData() {
		return data;
	}
	
	private String[] getNames() {
		String[] names = new String[data.size()];
		int i = 0;
		ArrayList<Entity> newData = (ArrayList<Entity>) data;
		for (Entity a: newData) {
			names[i] = a.getName();
			i++;
		}
		return names;
	}	
	
}

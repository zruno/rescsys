package ufpb.project.rescsystem.fragments;

import java.io.InputStream;
import java.util.ArrayList;

import ufpb.project.rescsystem.ContentParser;
import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.DisasterEvent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class InformationFragment extends Fragment implements OnItemSelectedListener {

	private ExpandableListView mExpandableList;
	private Spinner spinner;
	private String[] labels;
	private ArrayList<Parent> arrayParents;
	private TextView eventInfo;
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		View view = infltr.inflate(R.layout.fragment_information, container, true);

		eventInfo = (TextView) view.findViewById(R.id.home_event2);
		
		spinner = (Spinner) view.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.events_array, R.layout.spinnerview);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);
		
		mExpandableList = (ExpandableListView) view.findViewById(R.id.expandable_list2);
		
        arrayParents = new ArrayList<Parent>();
       
        labels = createLabelsArray();
        String[] contents = createContentsArray("", "", "");
        
        for (int i = 0; i < labels.length; i++){
            
        	Parent parent = new Parent();
            parent.setTitle(labels[i]);

            ArrayList<String> temp = new ArrayList<String>();
            temp.add(contents[i]);
            parent.setArrayChildren(temp);
 
            arrayParents.add(parent);
        }
 
        MyCustomAdapter mca = new MyCustomAdapter(getActivity().getBaseContext(), arrayParents);
        mca.notifyDataSetChanged();
        mExpandableList.setAdapter(mca);
        
		return view;
	}
	
	private String[] createLabelsArray() {
		String[] array = new String[3];
		
		array[0] = getResources().getString(R.string.label_before);
		array[1] = getResources().getString(R.string.label_during);		
		array[2] = getResources().getString(R.string.label_after);
		
		return array;
	}

	private String[] createContentsArray(String before, String during, String after) {
		String[] array = new String[3];
		
		array[0] = before;
		array[1] = during;
		array[2] = after;
		
		return array;
	}
	
	private String[] createContentsArray(int id1, int id2, int id3) {
		String[] array = new String[3];
		
		array[0] = getResources().getString(id1);
		array[1] = getResources().getString(id2);
		array[2] = getResources().getString(id3);
		
		return array;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		String[] array = new String[3];
		
		String selectedText = spinner.getItemAtPosition(arg2).toString();
		
		if (selectedText.equals("Inundação")) {
			eventInfo.setText(getResources().getString(R.string.flood_about));
			array = createContentsArray(R.string.flood_before, 
					R.string.flood_during, R.string.flood_after);
		}
		else if (selectedText.equals("Terremoto")) {
			eventInfo.setText(getResources().getString(R.string.earthquake_about));
			array = createContentsArray(R.string.earthquake_before, 
					R.string.earthquake_during, R.string.earthquake_after);
		}
		else if (selectedText.equals("Tsunami")) {
			eventInfo.setText(getResources().getString(R.string.tsunami_about));
			array = createContentsArray(R.string.tsunami_before, 
					R.string.tsunami_during, R.string.tsunami_after);
		}
		else if (selectedText.equals("Furacão")) {
			eventInfo.setText(getResources().getString(R.string.hurricane_about));
			array = createContentsArray(R.string.hurricane_before, 
					R.string.hurricane_during, R.string.hurricane_after);
		}
		else if (selectedText.equals("Incêndio")) {
			eventInfo.setText(getResources().getString(R.string.fire_about));
			array = createContentsArray(R.string.fire_before, 
					R.string.fire_during, R.string.fire_after);
		}
		
        for (int i = 0; i < labels.length; i++){
            System.err.println("pasda"); // DEBUG
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(array[i]);
            arrayParents.get(i).setArrayChildren(temp);
            mExpandableList.collapseGroup(i);
        }
        
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

}
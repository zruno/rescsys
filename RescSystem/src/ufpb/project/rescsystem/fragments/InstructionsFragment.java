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
import android.widget.Spinner;
import android.widget.TextView;

public class InstructionsFragment extends Fragment implements OnItemSelectedListener {

	private ExpandableListView mExpandableList;
	private DisasterEvent event;
	
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		InputStream input = getActivity().getResources().openRawResource(R.raw.event);
		event = (new ContentParser().parseEvent(input));
	}
	
	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		View view = infltr.inflate(R.layout.fragment_instructions, container, true);

		TextView eventInfo = (TextView) view.findViewById(R.id.home_event);
		eventInfo.setText(event.toString());
		
		TextView tv = (TextView) view.findViewById(R.id.home_label_event);
		tv.setText("Alerta de " +event.getType());
		
//		Spinner spinner = (Spinner) view.findViewById(R.id.events_spinner);
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				getActivity(), R.array.events_array, android.R.layout.simple_spinner_item);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setOnItemSelectedListener(this);
//		spinner.setAdapter(adapter);
		mExpandableList = (ExpandableListView) view.findViewById(R.id.expandable_list);
		 
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        //ArrayList<String> arrayChildren = new ArrayList<String>();
 
        String[] labels = createLabelsArray();
        String[] contents = createContentsArray();
        
        //here we set the parents and the children
        for (int i = 0; i < 3; i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent = new Parent();
            parent.setTitle(labels[i]);

            ArrayList<String> temp = new ArrayList<String>();
            temp.add(contents[i]);
            parent.setArrayChildren(temp);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(getActivity().getBaseContext(), arrayParents));
 
		return view;
	}
	
	private String[] createLabelsArray() {
		String[] array = new String[3];
		
		array[0] = getResources().getString(R.string.label_before);
		array[1] = getResources().getString(R.string.label_during);		
		array[2] = getResources().getString(R.string.label_after);
		
		return array;
	}

	private String[] createContentsArray() {
		String[] array = new String[3];
		
		array[0] = getResources().getString(R.string.flood_before);
		array[1] = "- Evite contato com a água da enchente, pode estar contaminada."
				  +"\n- Não manuseie equipamentos eletrônicos até eles serem checados."
				+"\n- Continue se informando através das notícias por meios de comunicação como rádio."
				+"\n- Permaneça longe de áreas sujeitas a inundação ou deslizamento."
				+"\n- Não tente andar, nada ou dirigir através da enchente.";
		array[2] = getResources().getString(R.string.flood_after);
		
		return array;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}


	
}
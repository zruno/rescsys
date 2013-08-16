package ufpb.project.rescsystem.fragments;

import ufpb.project.rescsystem.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InstructionsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		return infltr.inflate(R.layout.fragment_instructions, container, true);
	}
	
}
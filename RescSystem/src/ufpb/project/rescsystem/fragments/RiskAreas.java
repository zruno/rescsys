package ufpb.project.rescsystem.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RiskAreas extends Fragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		TextView txt = new TextView(getActivity());
		String saida = "�reas de Risco - Jo�o Pessoa\n\n" +
    			"\t� Bessa\n" +
    			"\t� Mana�ra\n" +
    			"\t� Tamba�\n" +
    			"\t� Cabo Branco\n" +
    			"\t� UFPB\n";
		
		txt.setText(saida);
		return txt;
	}
	
}

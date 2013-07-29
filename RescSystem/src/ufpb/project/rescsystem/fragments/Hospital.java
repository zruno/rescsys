package ufpb.project.rescsystem.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Hospital extends Fragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		TextView txt = new TextView(getActivity());
		String saida = "Lista de Hospitais disponíveis - João Pessoa\n\n" +
    			"\t• Hospital Memorial São Francisco - Torre\n" +
    			"\t• Hospital Edson Ramalho - Centro\n" +
    			"\t• Hospital da Unimed - Torre\n";
		
		txt.setText(saida);
		return txt;
	}
	
}

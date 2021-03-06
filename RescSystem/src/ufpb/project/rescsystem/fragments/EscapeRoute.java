package ufpb.project.rescsystem.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EscapeRoute extends Fragment {

	@Override
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {

		TextView txt = new TextView(getActivity());
		String saida = "Rota de Evacuação\n\n" +
        		"\t•  ↑ Siga pela via expressa\n" +
        		"\t• <- Entre à esquerda na Av. Dom Pedro II\n" +
        		"\t• <- Entre à esqueda na Av. Epitário Pessoa";
		
		txt.setText(saida);
		return txt;
	}
	
}

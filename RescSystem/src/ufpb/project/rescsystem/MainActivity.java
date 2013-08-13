package ufpb.project.rescsystem;

import java.util.ArrayList;

import ufpb.project.rescsystem.fragments.*;
import ufpb.project.rescsystem.modules.Entity;
import ufpb.project.rescsystem.modules.Hospital;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.app.ActionBar.*;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockFragmentActivity implements
		TabListener {

	/* Fragments */
	private Instruction f1;
	private Shelter f2;
	private HospitalsFragment f3;
	private RiskAreas f4;
	private EscapeRoute f5;

	String TAG = "rescsys";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Exception e = new Exception();
		Log.d(TAG, "called", e);
		
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_exemplo_sherlock);

		f1 = (Instruction) getSupportFragmentManager().findFragmentById(
				R.id.fragmento1);
		f2 = (Shelter) getSupportFragmentManager().findFragmentById(
				R.id.fragmento2);
		f3 = (HospitalsFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragmento3);
		f4 = (RiskAreas) getSupportFragmentManager().findFragmentById(
				R.id.fragmento4);
		f5 = (EscapeRoute) getSupportFragmentManager().findFragmentById(
				R.id.fragmento5);


		ArrayList<Entity> data = new ArrayList<Entity>();
		data.add(new Hospital("Hospital Exemplo", "+5508332004000", 
				"Rua Avenida, Bairro Vizinhança, N 1000 - João Pessoa, Paraíba", true));
		f3.setData(data);
		f3.setTextView();

		data.add(new Hospital("Outro Hospital", "08340040040", 
				"Em linguística, a noção de texto é ampla e ainda aberta" +
				" a uma definição mais precisa. Grosso modo, pode ser entendido como manifestação" +
				" linguística das ideias de um autor, que serão interpretadas pelo leitor de acordo" +
				" com seus conhecimentos linguísticos e culturais. Seu tamanho é variável." +
				" Conjunto de palavras e frases articuladas, escritas sobre qualquer suporte." +
				"Obra escrita considerada na sua redação original e autêntica (por oposição a sumário," +
				" tradução, notas, comentários, etc.)”2 .Um texto é uma ocorrência linguística, " +
				"escrita ou falada de qualquer extensão, " +
				"dotada de unidade sociocomunicativa, semântica e formal. É uma unidade de linguagem em uso", true));
		f3.setData(data);
		
		data.add(new Hospital("Yet Another Hospital", "010001110", 
				"St Avenue, Maynever, N 100 - Never, Neverland", true));
		
		data.add(new Hospital("Yet Another Hospital", "010001110", 
				"St Avenue, Maynever, N 100 - Never, Neverland", true));
		f3.setData(data);
		f3.setListView(getBaseContext());
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab aba1 = getSupportActionBar().newTab().setText("Instrucoes")
				.setTabListener(this).setIcon(R.drawable.instructions);
		
		Tab aba2 = getSupportActionBar().newTab().setText("Abrigos")
				.setTabListener(this).setIcon(R.drawable.shelters);

		Tab aba3 = getSupportActionBar().newTab().setText("Hospitais")
				.setTabListener(this).setIcon(R.drawable.hospitals);
		
		Tab aba4 = getSupportActionBar().newTab().setText("A. Risco")
				.setTabListener(this).setIcon(R.drawable.escape_route);
		
		Tab aba5 = getSupportActionBar().newTab().setText("Fuga")
				.setTabListener(this).setIcon(R.drawable.risc_areas);
		
		getSupportActionBar().addTab(aba1);
		getSupportActionBar().addTab(aba2);
		getSupportActionBar().addTab(aba3);
		getSupportActionBar().addTab(aba4);
		getSupportActionBar().addTab(aba5);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater()
				.inflate(R.menu.activity_exemplo_sherlock, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		
		if (tab.getPosition() == 0) {
			ft.show(f1).hide(f2).hide(f3).hide(f4).hide(f5);
		}else if(tab.getPosition() == 1){
			ft.hide(f1).show(f2).hide(f3).hide(f4).hide(f5);
		} else if(tab.getPosition() == 2){
			ft.hide(f1).hide(f2).show(f3).hide(f4).hide(f5);
		} else if(tab.getPosition() == 3){
			ft.hide(f1).hide(f2).hide(f3).show(f4).hide(f5);
		} else {
			ft.hide(f1).hide(f2).hide(f3).hide(f4).show(f5);
		} 
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}
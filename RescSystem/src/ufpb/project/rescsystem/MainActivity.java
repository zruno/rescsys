package ufpb.project.rescsystem;

import java.util.ArrayList;

import ufpb.project.rescsystem.fragments.*;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Hospital;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.app.ActionBar.*;
import com.actionbarsherlock.view.Menu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends SherlockFragmentActivity implements
		TabListener {
	
	/* Fragments */
	private InstructionsFragment f1;
	private SheltersFragment f2;
	private MyListFragment f3;
	private RoutesFragment f5;

	String TAG = "rescsys";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_exemplo_sherlock);
		
		getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);

		f1 = (InstructionsFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragmento1);
		f2 = (SheltersFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragmento2);
		f3 = (MyListFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragmento3);
		f5 = (RoutesFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragmento5);

		//f3.setData(data);

//		f3.setListView(getBaseContext());
//		f3.getListView().setOnItemClickListener(f3);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab aba1 = getSupportActionBar().newTab().setText("Instrucoes")
				.setTabListener(this).setIcon(R.drawable.instructions);
		
		Tab aba2 = getSupportActionBar().newTab().setText("Abrigos")
				.setTabListener(this).setIcon(R.drawable.shelters);

		Tab aba3 = getSupportActionBar().newTab().setText("Hospitais")
				.setTabListener(this).setIcon(R.drawable.hospitals);
		
		Tab aba5 = getSupportActionBar().newTab().setText("Fuga")
				.setTabListener(this).setIcon(R.drawable.risc_areas);
		
		getSupportActionBar().addTab(aba1);
		getSupportActionBar().addTab(aba2);
		getSupportActionBar().addTab(aba3);
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
			ft.show(f1).hide(f2).hide(f3).hide(f5);
		}else if(tab.getPosition() == 1){
			ft.hide(f1).show(f2).hide(f3).hide(f5);
		} else if(tab.getPosition() == 2){
			ft.hide(f1).hide(f2).show(f3).hide(f5);
		} else {
			ft.hide(f1).hide(f2).hide(f3).show(f5);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}
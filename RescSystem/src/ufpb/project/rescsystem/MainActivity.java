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
	private HospitalsFragment f3;
	private RoutesFragment f4;

	String TAG = "rescsys";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_main);
		
		getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);

		f1 = (InstructionsFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragment1);
		f2 = (SheltersFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragment2);
		f3 = (HospitalsFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragment3);
		f4 = (RoutesFragment) getSupportFragmentManager().findFragmentById(
				R.id.fragment4);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab aba1 = getSupportActionBar().newTab()
				.setText("Instruções")
				.setTabListener(this)
				//.setIcon(R.drawable.information)
				;
		
		Tab aba2 = getSupportActionBar().newTab().setText("Abrigos")
				.setTabListener(this);//.setIcon(R.drawable.shelters);

		Tab aba3 = getSupportActionBar().newTab().setText("Hospitais")
				.setTabListener(this);//.setIcon(R.drawable.hospitals);
		
		Tab aba5 = getSupportActionBar().newTab().setText("Evacuação")
				.setTabListener(this);//.setIcon(R.drawable.escape_route);
		
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
			ft.show(f1).hide(f2).hide(f3).hide(f4);
		}else if(tab.getPosition() == 1){
			ft.hide(f1).show(f2).hide(f3).hide(f4);
		} else if(tab.getPosition() == 2){
			ft.hide(f1).hide(f2).show(f3).hide(f4);
		} else {
			ft.hide(f1).hide(f2).hide(f3).show(f4);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}
package ufpb.project.rescsystem.fragments;

import java.io.InputStream;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.ContentParser;
import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.fragments.GMapFragment.MapListener;
import ufpb.project.rescsystem.modules.DisasterEvent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RoutesFragment extends Fragment
			implements MapListener {

	private DisasterEvent event;
	private GMapFragment map;
	private TextView routeText;
	private int mapReadyFlag = 0;
	
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		InputStream input = getActivity().getResources().openRawResource(R.raw.event);
		event = (new ContentParser().parseEvent(input));
	
		map = GMapFragment.gMapInstance(this);
		map.setRouteColor(Color.argb(255, 0, 0, 155));
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container3, map);
		ft.commit();
	}
	
	public View onCreateView(LayoutInflater infltr, ViewGroup container,
			Bundle savedState) {
		
		View v = infltr.inflate(R.layout.fragment_routes, container, true);
		routeText = (TextView) v.findViewById(R.id.routeText);
		onMapReady();
		
		return v;
	}

	@Override
	public void onMapReady() {
		mapReadyFlag++;
		if (mapReadyFlag == 2) {
			mapReadyFlag = 0;
			map.drawPolygon(event.getPoints());
			if (map.atRiskArea()) {
				routeText.setText("Esta rota de evacuação foi alocada de acordo com sua localização e tráfico.");
				map.evacuationRoute(smallerDistance());
			}
			else
				routeText.setText("Você está em uma área segura");
		}
	}
	
	public LatLng smallerDistance() {
		
		Location cl = coordToLocation(map.getLastLatLng(), "atual");
		
		ArrayList<Location> bounds = new ArrayList<Location>();
		for (LatLng l: event.getPoints()) {
			bounds.add(coordToLocation(l, ""));
		}
		for (LatLng l: event.getEvpoints()) {
			bounds.add(coordToLocation(l, ""));
		}
		
		Location smaller = null;
		float current = cl.distanceTo(bounds.get(0));
		for (Location loc: bounds) {
			if (cl.distanceTo(loc) <= current) {
				current = cl.distanceTo(loc);
				smaller = loc;
			}
		}
		
		LatLng sm = new LatLng(smaller.getLatitude(), smaller.getLongitude());
		return sm;
	}
	
	private Location coordToLocation(LatLng l, String str) {
		Double lat = l.latitude;
		Double lng = l.longitude;
		Location loc = new Location(str);
		loc.setLatitude(lat);
		loc.setLongitude(lng);
		return loc;
	}
	
}

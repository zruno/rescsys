package ufpb.project.rescsystem.fragments;

import java.util.ArrayList;
import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.maputils.GetDirectionsTask;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Hospital;
import ufpb.project.rescsystem.modules.Shelter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class GMapFragment extends SupportMapFragment {
	
	private GoogleMap gmap;
	private LocationManager locMan;
	
	private Marker userMarker;
	
	private Marker[] placesMarkers;
	private final int MAX_PLACES = 20;
	private MarkerOptions[] placesOptions;
	private ArrayList<Facility> facilities;
	private MapListener parentFragment;
	private LatLng lastLatLng;
	
	public LatLng getLastLatLng() {
		return lastLatLng;
	}

	public void setLastLatLng(LatLng lastLatLng) {
		this.lastLatLng = lastLatLng;
	}

	private Polyline route;
	private int routeColor;
	private LatLngBounds llb;
	
	public Polyline getRoute() {
		return route;
	}

	public void setRoute(Polyline route) {
		this.route = route;
	}

	public interface MapListener {
		public void onMapReady();
	}
	
	public static GMapFragment gMapInstance(Fragment pf, ArrayList<Facility> data) {
		GMapFragment f =  new GMapFragment();
		f.parentFragment = (MapListener) pf;
		f.facilities = data;
		return f;
	}
	
	public static GMapFragment gMapInstance(Fragment pf) {
		GMapFragment f =  new GMapFragment();
		f.parentFragment = (MapListener) pf;
		return f;
	}
	
	public void addMarkers() {
		
		placesOptions = new MarkerOptions[facilities.size()];
		placesMarkers = new Marker[facilities.size()];
		
		int i = 0;
		for (Facility f: facilities) {
			
			LatLng placeLL = f.getLatlng();
			String placeName = f.getName();
			String address = f.getAddress();
			
			int iconId;
			
			if (f instanceof Hospital)
				iconId = R.drawable.marker_hospital;
			else if (f instanceof Shelter)
				iconId = R.drawable.marker_shelter;
			else 
				iconId = R.drawable.marker_default;
			
			BitmapDescriptor icon = BitmapDescriptorFactory.
					fromResource(iconId);
			
			placesOptions[i++] = new MarkerOptions()
		    .position(placeLL)
		    .title(placeName)
		    .icon(icon)
		    .snippet(address);
		}
		
		for(int p=0; p<placesMarkers.length; p++){
	        placesMarkers[p] = gmap.addMarker(placesOptions[p]);
		}
	}
	
	public ArrayList<Facility> getPlaces() {
		return facilities;
	}
		
	public void setHighlightedMarker(int position) {
		Marker m = placesMarkers[position];
		m.showInfoWindow();
		gmap.animateCamera(CameraUpdateFactory.newLatLng(m.getPosition()), 300, null);
	}
	
	public void setRouteToMarker(int position) {
		if (route != null) route.remove();
		
		Marker m = placesMarkers[position];
		LatLng origin = userMarker.getPosition();
		LatLng destination = m.getPosition();
		
		 // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, destination);

        GetDirectionsTask directionsTask = new GetDirectionsTask(this);
        // Start downloading json data from Google Directions API
        directionsTask.execute(url);
	}
	
	public void evacuationRoute(LatLng ll) {
		String url = getDirectionsUrl(lastLatLng, ll);
        GetDirectionsTask directionsTask = new GetDirectionsTask(this);
        // Start downloading json data from Google Directions API
        directionsTask.execute(url);
	}
	
	public void drawPolygon(ArrayList<LatLng> area) {
		PolygonOptions polygOpt;
		polygOpt = new PolygonOptions();
		for (LatLng a: area) {
			polygOpt.add(a);
		}
		polygOpt.fillColor(Color.argb(50, 255, 0, 0));
		polygOpt.strokeWidth(3);
		polygOpt.strokeColor(Color.argb(200, 255, 0, 0));
		Polygon polygon = gmap.addPolygon(polygOpt);
		llb = new LatLngBounds(area.get(3), area.get(1));
	}
	
	public boolean atRiskArea() {
		if (llb.contains(lastLatLng)) return true;
		else return false;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setPlacesMarkers(new Marker[MAX_PLACES]);

	}
	
	public void onStart() {

		super.onStart();
		
		locMan = (LocationManager) 
				getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		gmap = getMap();
		
		Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		double lat = lastLoc.getLatitude();
		double lng = lastLoc.getLongitude();
				
		lastLatLng = new LatLng(-7.187769, -34.840389);

		parentFragment.onMapReady();
		
		userMarker = gmap.addMarker(new MarkerOptions()
	    .position(lastLatLng)
	    .title("Você está aqui")
	    .snippet("Sua última localização registrada"));
		
		gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 13), 300, null);
	}
	
	public void setPlacesMarkers(Marker[] placesMarkers) {
		this.placesMarkers = placesMarkers;
	}
	
    private String getDirectionsUrl(LatLng origin, LatLng dest) {
    	 
        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;
        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
        
        return url;
    }

	public int getRouteColor() {
		return routeColor;
	}

	public void setRouteColor(int routeColor) {
		this.routeColor = routeColor;
	}
}

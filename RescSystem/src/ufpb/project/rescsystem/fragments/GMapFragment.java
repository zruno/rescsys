package ufpb.project.rescsystem.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ufpb.project.rescsystem.R;
import ufpb.project.rescsystem.modules.Facility;
import utils.ImageUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class GMapFragment extends SupportMapFragment {
	
	private GoogleMap gmap;
	private LocationManager locMan;
	
	@SuppressWarnings("unused") // it will be, smartass
	private Marker userMarker;
	
	private Marker[] placesMarkers;
	private final int MAX_PLACES = 20;
	private MarkerOptions[] placesOptions;
	private ArrayList<Facility> places;
	private MapListener parentFragment;
	
	public interface MapListener {
		public void onMapReady();
	}
	
	public static GMapFragment gMapInstance(Fragment pf) {
		GMapFragment f =  new GMapFragment();
		f.parentFragment = (MapListener) pf;
		return f;
	}
	
	public ArrayList<Facility> getPlaces() {
		return places;
	}
	
	private void updateUI() {
	}
	
	public void onCreate(Bundle savedInstanceState) {
		
		places = new ArrayList<Facility>();
		super.onCreate(savedInstanceState);
		
		setPlacesMarkers(new Marker[MAX_PLACES]);
		locMan = (LocationManager) 
				getActivity().getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void onResume() {
		super.onResume();
		gmap = getMap();
		
		Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		double lat = lastLoc.getLatitude();
		double lng = lastLoc.getLongitude();
				
		LatLng lastLatLng = new LatLng(lat, lng);
		userMarker = gmap.addMarker(new MarkerOptions()
	    .position(lastLatLng)
	    .title("You are here")
	    .snippet("Your last recorded location"));
		
		String placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
			    "json?location="+lat+","+lng+
			    "&radius=10000&sensor=true" +
			    "&types=hospital"+
			    "&key=AIzaSyCxntrIHVBoFo_Ndv56cVxyqaxqPDb3CXU";
		
		new GetPlaces().execute(placesSearchStr);
		
		gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 13), 300, null);

	}
	
	public void setPlacesMarkers(Marker[] placesMarkers) {
		this.placesMarkers = placesMarkers;
	}
	
	private class GetPlaces extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... placesURL) {

			//build result as string
			StringBuilder placesBuilder = new StringBuilder();
			//process search parameter string(s)
			for (String placeSearchURL : placesURL) {
				HttpClient placesClient = new DefaultHttpClient();
				try {
					//try to fetch the data
				
					//HTTP Get receives URL string
					HttpGet placesGet = new HttpGet(placeSearchURL);
					//execute GET with Client - return response
					HttpResponse placesResponse = placesClient.execute(placesGet);
					//check response status
	 				StatusLine placeSearchStatus = placesResponse.getStatusLine();
					//only carry on if response is OK
					if (placeSearchStatus.getStatusCode() == 200) {
						//get response entity
						HttpEntity placesEntity = placesResponse.getEntity();
						//get input stream setup
						InputStream placesContent = placesEntity.getContent();
						//create reader
						InputStreamReader placesInput = new InputStreamReader(placesContent);
						//use buffered reader to process
						BufferedReader placesReader = new BufferedReader(placesInput);
						//read a line at a time, append to string builder
						String lineIn;
						while ((lineIn = placesReader.readLine()) != null) {
							placesBuilder.append(lineIn);
						}
					}
				}
				catch(Exception e){ 
					e.printStackTrace(); 
				}
			}
			return placesBuilder.toString();
		}
		
		protected void onPostExecute(String result) {
		
			try {
				JSONObject resultObject = new JSONObject(result);
				JSONArray placesArray = resultObject.getJSONArray("results");
				placesOptions = new MarkerOptions[placesArray.length()];
				placesMarkers = new Marker[MAX_PLACES];
				
				for (int p=0; p<placesArray.length(); p++) {
					boolean missingValue = false;
					LatLng placeLL=null;
					String placeName="";
					String vicinity="";
					String iconUrl = "";
					
					try {
						JSONObject placeObject = placesArray.getJSONObject(p);
						JSONObject loc = placeObject.getJSONObject("geometry").getJSONObject("location");
						
						placeLL = new LatLng(
							    Double.valueOf(loc.getString("lat")),
							    Double.valueOf(loc.getString("lng")));
												
						vicinity = placeObject.getString("vicinity");
						placeName = placeObject.getString("name");
						
					} catch(JSONException jse) {
					    missingValue=true;
					    jse.printStackTrace();
					}
					if (missingValue) placesOptions[p]=null;
					else {
						
						Facility f = new Facility(placeName, "999", vicinity, true);
						places.add(f);
						BitmapDescriptor icon = BitmapDescriptorFactory.
								fromResource(R.drawable.hospital_marker_icon);
						placesOptions[p]=new MarkerOptions()
					    .position(placeLL)
					    .title(placeName)
					    .icon(icon)
					    .snippet(vicinity);
					}
				}
				
			}
			catch (Exception e) {
			    e.printStackTrace();
			}
			if (placesOptions!=null && placesMarkers!=null){
			    for(int p=0; p<placesOptions.length && p<placesMarkers.length; p++){
			        // will be null if a value was missing
			        if (placesOptions[p]!=null)
			            placesMarkers[p]=gmap.addMarker(placesOptions[p]);
			    }
			}
			parentFragment.onMapReady();
		}
	}

	
}

package ufpb.project.rescsystem.fragments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

public class GMapFragment extends SupportMapFragment {
	
	private GoogleMap gmap;
	private LocationManager locMan;
	
	private Marker userMarker;
	
	private Marker[] placesMarkers;
	private final int MAX_PLACES = 20;
	
	private MarkerOptions[] places;
	
	private String searchStr;
	
//	public GMapFragment() {
//		super();
//	}
	
	public static GMapFragment gMapInstance() {
		return new GMapFragment();
	}
	
	public void onCreate(Bundle savedInstanceState) {
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
		
			System.err.println(result +" thistheresult");
			try {
				JSONObject resultObject = new JSONObject(result);
				JSONArray placesArray = resultObject.getJSONArray("results");
				places = new MarkerOptions[placesArray.length()];
				placesMarkers = new Marker[MAX_PLACES];
				
				for (int p=0; p<placesArray.length(); p++) {
					boolean missingValue = false;
					LatLng placeLL=null;
					String placeName="";
					String vicinity="";
					
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
					if (missingValue) places[p]=null;
					else
					    places[p]=new MarkerOptions()
					    .position(placeLL)
					    .title(placeName)
					    .snippet(vicinity);
				}
				
			}
			catch (Exception e) {
			    e.printStackTrace();
			}
			if (places!=null && placesMarkers!=null){
			    for(int p=0; p<places.length && p<placesMarkers.length; p++){
			        // will be null if a value was missing
			        if (places[p]!=null)
			            placesMarkers[p]=gmap.addMarker(places[p]);
			    }
			}
		}
	}

	
}

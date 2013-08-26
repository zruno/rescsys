package ufpb.project.rescsystem.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import utils.DirectionsJSONParser;
import utils.ImageUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class GMapFragment2 extends SupportMapFragment {
	
	private GoogleMap gmap;
	private LocationManager locMan;
	
	private Marker userMarker;
	
	private Marker[] placesMarkers;
	private final int MAX_PLACES = 20;
	private MarkerOptions[] placesOptions;
	private ArrayList<Facility> facilities;
	private MapListener parentFragment;
	private Polyline route;
	
	public interface MapListener {
		public void onMapReady();
	}
	
	public static GMapFragment2 gMapInstance(Fragment pf) {
		GMapFragment2 f =  new GMapFragment2();
		f.parentFragment = (MapListener) pf;
		return f;
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
		System.err.println(origin.toString() +destination.toString() +"latitude");
		 // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, destination);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);

	}
	
	public void onCreate(Bundle savedInstanceState) {
		
		facilities = new ArrayList<Facility>();
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
		
		gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 13), 300, null);
		
		parentFragment.onMapReady();
	}
	
	public void setPlacesMarkers(Marker[] placesMarkers) {
		this.placesMarkers = placesMarkers;
	}
	
	public void addMarkers(ArrayList<Facility> placesList)
		{
			placesOptions = new MarkerOptions[placesList.size()];
			placesMarkers = new Marker[placesList.size()];
			
			int i = 0;
			for (Facility f: placesList) {
				
				LatLng placeLL = f.getLatlng();
				String placeName = f.getName();
				String address = f.getAddress();
					
//				BitmapDescriptor icon = BitmapDescriptorFactory.
//						fromResource(R.drawable.hospital_marker_icon);
//				
				placesOptions[i++] = new MarkerOptions()
			    .position(placeLL)
			    .title(placeName)
			    //.icon(icon)
			    .snippet(address);
			}
		
		for(int p=0; p<placesMarkers.length; p++){
	        placesMarkers[p] = gmap.addMarker(placesOptions[p]);
		}
	}

    private String getDirectionsUrl(LatLng origin,LatLng dest){
    	 
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
        
        System.err.println(url +"directions-url-saida");
        
        return url;
    }
    
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
 
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
 
            // Connecting to url
            urlConnection.connect();
 
            // Reading data from url
            iStream = urlConnection.getInputStream();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
 
            StringBuffer sb = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }
 
            data = sb.toString();
 
            System.err.println(data +"download-url-saida");
            
            br.close();
 
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
	
	  private class DownloadTask extends AsyncTask<String, Void, String>{
		  
	        // Downloading data in non-ui thread
	        @Override
	        protected String doInBackground(String... url) {
	 
	            // For storing data from web service
	            String data = "";
	 
	            try{
	                // Fetching the data from web service
	                data = downloadUrl(url[0]);
	            }catch(Exception e){
	                Log.d("Background Task",e.toString());
	            }
	            return data;
	        }
	 
	        // Executes in UI thread, after the execution of
	        // doInBackground()
	        @Override
	        protected void onPostExecute(String result) {
	            super.onPostExecute(result);
	 
	            ParserTask parserTask = new ParserTask();
	 
	            // Invokes the thread for parsing the JSON data
	            parserTask.execute(result);
	        }
	    }
	 
	    /** A class to parse the Google Places in JSON format */
	    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
	 
	        // Parsing the data in non-ui thread
	        @Override
	        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
	 
	            JSONObject jObject;
	            List<List<HashMap<String, String>>> routes = null;
	 
	            try{
	                jObject = new JSONObject(jsonData[0]);
	                DirectionsJSONParser parser = new DirectionsJSONParser();
	 
	                // Starts parsing data
	                routes = parser.parse(jObject);
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	            return routes;
	        }
	 
	        // Executes in UI thread, after the parsing process
	        @Override
	        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
	            ArrayList<LatLng> points = null;
	            PolylineOptions lineOptions = null;
	            MarkerOptions markerOptions = new MarkerOptions();
	 
	            // Traversing through all the routes
	            for(int i=0;i<result.size();i++){
	                points = new ArrayList<LatLng>();
	                lineOptions = new PolylineOptions();
	 
	                // Fetching i-th route
	                List<HashMap<String, String>> path = result.get(i);
	 
	                // Fetching all the points in i-th route
	                for(int j=0;j<path.size();j++){
	                    HashMap<String,String> point = path.get(j);
	 
	                    double lat = Double.parseDouble(point.get("lat"));
	                    double lng = Double.parseDouble(point.get("lng"));
	                    LatLng position = new LatLng(lat, lng);
	 
	                    points.add(position);
	                }
	 
	                // Adding all the points in the route to LineOptions
	                lineOptions.addAll(points);
	                lineOptions.width(2);
	                lineOptions.color(Color.RED);
	            }
	 
	            // Drawing polyline in the Google Map for the i-th route
	            route = gmap.addPolyline(lineOptions);
	        }
	    }
	
}

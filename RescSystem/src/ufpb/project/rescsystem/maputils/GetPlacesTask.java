package ufpb.project.rescsystem.maputils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import ufpb.project.rescsystem.fragments.GMapFragment.MapListener;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Hospital;

import com.google.android.gms.maps.model.LatLng;
import android.os.AsyncTask;

public class GetPlacesTask extends AsyncTask<String, Void, String> {

	ArrayList<Facility> facilities;
	private MapListener fragment;
	String type;
	
	public GetPlacesTask(ArrayList<Facility> facilities, MapListener fragment, String type) {
		this.facilities = facilities;
		this.fragment = fragment;
		this.type = type;
	}
	
	@Override
	protected String doInBackground(String... placesURL) {

		StringBuilder placesBuilder = new StringBuilder();

		for (String placeSearchURL : placesURL) {
			HttpClient placesClient = new DefaultHttpClient();
		
			try {
				HttpGet placesGet = new HttpGet(placeSearchURL);
				HttpResponse placesResponse = placesClient.execute(placesGet);
 				StatusLine placeSearchStatus = placesResponse.getStatusLine();
			
 				if (placeSearchStatus.getStatusCode() == 200) {
					
 					HttpEntity placesEntity = placesResponse.getEntity();
					InputStream placesContent = placesEntity.getContent();
					InputStreamReader placesInput = new InputStreamReader(placesContent);
					BufferedReader placesReader = new BufferedReader(placesInput);
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
			
			for (int p=0; p<placesArray.length(); p++) {
				
				LatLng placeLL = null;
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
					
				} catch (JSONException jse) {
				    jse.printStackTrace();
				}
				Facility f;
				if (type.equals("hospital")) {
					f = new Hospital(placeName, "999", vicinity, true, placeLL);
				}
				else {
					f = new Facility(placeName, "999", vicinity, true, placeLL);
				}
				facilities.add(f);
			}
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
		fragment.onMapReady();	
	}
}

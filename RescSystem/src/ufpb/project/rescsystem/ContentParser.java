package ufpb.project.rescsystem;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.android.gms.maps.model.LatLng;

import ufpb.project.rescsystem.modules.DisasterEvent;
import ufpb.project.rescsystem.modules.Facility;
import ufpb.project.rescsystem.modules.Shelter;

public class ContentParser {

	public ArrayList<Facility> parseResources(InputStream input) {
		
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(input);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		
		NodeList shelters = doc.getElementsByTagName("shelter");
		
		ArrayList<Facility> sheltersList = new ArrayList<Facility>();
		
		for (int i = 0; i<shelters.getLength(); i++) {
			Node shelter = shelters.item(i);
			
			if (shelter.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) shelter;
				
				String name = e.getElementsByTagName("name").item(0).getTextContent();
				String address = e.getElementsByTagName("address").item(0).getTextContent();
				String phone = e.getElementsByTagName("phone").item(0).getTextContent();
				
				Double lat = Double.parseDouble(e.getElementsByTagName("lat").item(0).getTextContent());
				Double lng = Double.parseDouble(e.getElementsByTagName("lng").item(0).getTextContent());
				LatLng coord = new LatLng(lat, lng);
				
				sheltersList.add(new Shelter(name, address, phone, true, coord));
			}
		}
		
		return sheltersList;
	}
	
	public DisasterEvent parseEvent(InputStream input) {
		
		DisasterEvent event = new DisasterEvent();
		
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(input);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		
		NodeList nh = doc.getElementsByTagName("burghH");
		NodeList nl = doc.getElementsByTagName("burghL");
		NodeList c = doc.getElementsByTagName("city");
		
		String[] neighborH = new String[nh.getLength()];
		for (int i = 0; nh.getLength() > i; i++) {
			Node burgh = nh.item(i);
			
			if (burgh.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) burgh;
				
				String name = e.getTextContent();
				neighborH[i] = name;
			}
		}
		
		String[] neighborL = new String[nl.getLength()];
		for (int i = 0; nl.getLength() > i; i++) {
			Node burgh = nl.item(i);
			
			if (burgh.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) burgh ;
				
				String name = e.getTextContent();
				neighborL[i] = name;
			}
		}
		
		String[] cities = new String[c.getLength()];
		for (int i = 0; c.getLength() > i; i++) {
			Node city = c.item(i);
			
			if (city.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) city;
				
				String name = e.getTextContent();
				cities[i] = name;
			}
		}
		
		NodeList a = doc.getElementsByTagName("about");
		Node ab = a.item(0);
		String about = "";
		if (ab.getNodeType() == Node.ELEMENT_NODE) {
			about = ab.getTextContent();
		}
		
		event.setAbout(about);
		event.setCities(cities);
		event.setNeighboorhoodHigh(neighborH);
		event.setNeighborhoodLow(neighborL);
		
		return event;
	}
	
}

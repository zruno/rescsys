package ufpb.project.rescsystem.modules;

import java.util.Date;

public class DisasterEvent {

	private Date eventWatch;
	private Date eventStart;
	private Date eventAlert;
	private Date eventForeseenEnd;
	
	private String[] neighborhoodLow;
	private String[] neighboorhoodHigh;
	private String[] cities;
	private String about;
	
	public Date getEventWatch() {
		return eventWatch;
	}
	public void setEventWatch(Date eventWatch) {
		this.eventWatch = eventWatch;
	}
	public Date getEventStart() {
		return eventStart;
	}
	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}
	public Date getEventAlert() {
		return eventAlert;
	}
	public void setEventAlert(Date eventAlert) {
		this.eventAlert = eventAlert;
	}
	public Date getEventForeseenEnd() {
		return eventForeseenEnd;
	}
	public void setEventForeseenEnd(Date eventForeseenEnd) {
		this.eventForeseenEnd = eventForeseenEnd;
	}
	public String[] getNeighborhoodLow() {
		return neighborhoodLow;
	}
	public void setNeighborhoodLow(String[] neighborhood) {
		this.neighborhoodLow = neighborhood;
	}
	public String[] getNeighboorhoodHigh() {
		return neighboorhoodHigh;
	}
	public void setNeighboorhoodHigh(String[] neighboorhoodHigh) {
		this.neighboorhoodHigh = neighboorhoodHigh;
	}
	public String[] getCities() {
		return cities;
	}
	public void setCities(String[] cities) {
		this.cities = cities;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public String toStringArray(String[] array) {
		StringBuilder text = new StringBuilder();
		for (int i=0; i<array.length; i++) {
			text.append(array[i]);
			if (i!=array.length-1) text.append(", ");
		}
		return text.toString();
	}
	
	public String toString() {
		return "Cidades atingidas: " +toStringArray(cities) +"\n"
				+"Bairros fortemente atingidos: " +toStringArray(neighboorhoodHigh) +"\n"
				+"Bairros levemente atingidos: " +toStringArray(neighborhoodLow) +"\n\n"
				+"Sobre: " +about;
	}
	
}

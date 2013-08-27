package utils;

import java.util.ArrayList;

import ufpb.project.rescsystem.modules.Facility;

public class Utilities {

	public static void printFList(ArrayList<Facility> data, String tag) {
		System.err.println(tag);
		for (Facility f: data) {
			System.err.println(f.getName());
		}
	}
	
}

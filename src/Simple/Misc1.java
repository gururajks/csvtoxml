package Simple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.stream.XMLOutputFactory;


public class Misc1 {

	public final static String folderPath = "C:/Users/gururajks/Downloads/MBTA_GTFS/";
	public final static String xmlPath = "C:/Users/gururajks/Downloads/MBTA_GTFS/trips.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fileIn;
		Misc1 misc = new Misc1();
		misc.createXml();
		String line;
		try {
			fileIn = new FileInputStream(folderPath + "trips.txt");
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(fileIn));
			int count = 0;
			while((line = buffreader.readLine()) != null) {
				if(count == 0) continue;
				misc.processString(line);
				count++;
			}
			buffreader.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void processString(String line) {
		String[] stringItems = line.split(",");
		for(int i = 0 ; i < stringItems.length; i++) {
			String stringItem = stringItems[i];
			int stringItemSize = stringItem.length();
			String strippedStringItem = stringItem.substring(1, stringItemSize - 1);
			addToXml(strippedStringItem);
		}
		
	}
	
	
	public void createXml() {
		XMLOutputFactory outPutFactory = XMLOutputFactory.newInstance();
		
	}

	private void addToXml(String strippedStringItem) {
		
		
	}
		
	
}

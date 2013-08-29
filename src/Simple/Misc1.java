package Simple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;


public class Misc1 {

	public final static String folderPath = "C:/Users/gururajks/Downloads/MBTA_GTFS/";
	public final static String txtPath = "C:/Users/gururajks/Downloads/MBTA_GTFS/trips_modificed.csv";
	XMLOutputFactory outputFactory;
	XMLEventWriter eventWriter;
	XMLEventFactory eventFactory;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fileIn;
		Misc1 misc = new Misc1();
		
		String line;
		try {
			//misc.createXml();
			fileIn = new FileInputStream(folderPath + "trips_rail_sql.csv");
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(fileIn));
			int count = 0;
			File file = new File(txtPath);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			while((line = buffreader.readLine()) != null) {
				System.out.println(count);
				if(count == 0) {
					//count++;
					//continue;
				}
				//String newFileLine = misc.processString(line);
				String newFileLine = line + ",";
				bw.write(newFileLine);
				bw.newLine();
				count++;
			}
			bw.close();
			//misc.endXml();
			buffreader.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private String processString(String line) throws IOException {		
		String newFileLine="";
		String[] stringItems = line.split(",");
		for(int i = 0 ; i < stringItems.length; i++) {
			
			String stringItem = stringItems[i];			
			if(i == 0)	
				newFileLine += stringItem;						
			if(i == 6)
				newFileLine = newFileLine + "," + stringItem;			
		}
		return newFileLine;
	}
	
	/*
	public void createXml() throws XMLStreamException, FileNotFoundException {
		outputFactory = XMLOutputFactory.newInstance();
		eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(xmlPath));
	    // Create a EventFactory
	    eventFactory = XMLEventFactory.newInstance();	    
	    // Create and write Start Tag
	    StartDocument startDocument = eventFactory.createStartDocument();
	    eventWriter.add(startDocument);
	    // Create trip open tag
	    StartElement tripStartElement = eventFactory.createStartElement("","", "trips");
	    eventWriter.add(tripStartElement);	   
	}
	 
	public void endXml() throws XMLStreamException {
		eventWriter.add(eventFactory.createEndElement("", "", "trips"));
		eventWriter.add(eventFactory.createEndDocument());		
		eventWriter.close();
	}

	private void addToXml(String strippedStringItem, String nodeItemName) throws XMLStreamException {
		XMLEventFactory nodeItemEventFactory = XMLEventFactory.newInstance();
		StartElement sElement = nodeItemEventFactory.createStartElement("", "", nodeItemName);	    
	    eventWriter.add(sElement);
	    Characters nodeValue = nodeItemEventFactory.createCharacters(strippedStringItem);
	    eventWriter.add(nodeValue);
	    EndElement eElement = nodeItemEventFactory.createEndElement("", "",  nodeItemName);
	    eventWriter.add(eElement);
	}
		
	*/
}

package Simple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	public final static String xmlPath = "C:/Users/gururajks/Downloads/MBTA_GTFS/trips.xml";
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
			misc.createXml();
			fileIn = new FileInputStream(folderPath + "trips.txt");
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(fileIn));
			int count = 0;
			while((line = buffreader.readLine()) != null) {
				System.out.println(count);
				if(count == 0) {
					count++;
					continue;
				}
				misc.processString(line);
				count++;
			}
			misc.endXml();
			buffreader.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		
	}

	private void processString(String line) throws XMLStreamException {
		XMLEventFactory nodeEventFactory = XMLEventFactory.newInstance();		
		StartElement sElement = nodeEventFactory.createStartElement("", "", "trip");	    
	    eventWriter.add(sElement);
		String[] stringItems = line.split(",");
		String strippedStringItem = "";
		for(int i = 0 ; i < stringItems.length; i++) {
			String stringItem = stringItems[i];
			int stringItemSize = stringItem.length();
			if(stringItem.contains("\"")) {
				strippedStringItem = stringItem.substring(1, stringItemSize - 1);
			}
			else {
				strippedStringItem = stringItem;
			}			
			if(i == 0)	
				addToXml(strippedStringItem, "route_id");
			/*if(i == 1)
				addToXml(strippedStringItem, "service_id");
			if(i == 2)
				addToXml(strippedStringItem, "trip_id");
			if(i == 3)
				addToXml(strippedStringItem, "trip_headsign");
			if(i == 4)
				addToXml(strippedStringItem, "direction_id");
			if(i == 5)
				addToXml(strippedStringItem, "block_id");
				*/
			if(i == 6)
				addToXml(strippedStringItem, "shape_id");
		}
		EndElement eElement = nodeEventFactory.createEndElement("", "", "trip");
	    eventWriter.add(eElement);
	}
	
	
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
		
	
}

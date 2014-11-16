/*package xml.parsing.example;*/

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.org.apache.xml.internal.serialize.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



public class XMLimporter extends inputManagement{
	static String key = "09D3201436234855F1C0C3DCFC0436EC";				//API search key
	static String remainderURL = "/v001/?key=";							//deault strings for search URLs:
	
	static String accountID = "account_ID=";
	static String makeXML = "format=XML";
	static String hero_ID="hero_id=";
	static String startAt = "start_at_match_id=";
	static String tournamentOnly = "tournament_games_only=";
	
	
	
	public static void getInfo() throws ParserConfigurationException, IOException, SAXException, TransformerException{
		String url = searchString(remainderURL,key,accountID,makeXML);			//compile the string
		writeProductsXML(url);													//write the results to a file
		
		XMLParser.parseInfo(searchType);										//parse the results depending on which search type

	}
	
/*	public static void directClass(String searchmethod){
		switch(searchmethod){
		case "getmatchhistory":
			XMLParser.parseInfo("getmatchhistory");
			break;
		case "getmatchdetails":
			XMLParser.parseInfo("getmatchdetails");
			break;
		case "getheroes":
			XMLParser.parseInfo("getheroes");
			break;
		case "getplayersummaries":
			XMLParser.parseInfo("getplayersummaries");
			break;
		case "economyschema":
			XMLParser.parseInfo("economyschema");
			break;
		case "getleaguelisting":
			XMLParser.parseInfo("getleaguelisting");
			break;
		case "getliveleaguegames":
			XMLParser.parseInfo("getliveleaguegames");
			break;
		case "getmatchhistorybysequencenum":
			XMLParser.parseInfo("getmatchhistorybysequencenum");
			break;
		case "getteaminfobyteamid":
			XMLParser.parseInfo("getteaminfobyteamid");
			break;
		}
		
	}*/
	
	
	private static void copy(InputStream from, OutputStream to) throws IOException {
        byte[] buffer = new byte[4096];
        while (true) {
            int numBytes = from.read(buffer);
            if (numBytes == -1) {
                break;
            }
            to.write(buffer, 0, numBytes);
        }
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		// TODO Auto-generated method stub
		GetHeroes.checkHeroes(key); //update the list of heroes upon startup
		
		getInfo();					//ask for inputs to generate search URLS

	}
	

	
	
	private static void writeProductsXML(String iurl) throws ParserConfigurationException,IOException,SAXException, TransformerException{
		
		URL url = new URL(iurl);
        URLConnection conn = url.openConnection();
        
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("information.html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
        DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docfactory.newDocumentBuilder();
        Document xmlDoc = builder.parse(conn.getInputStream());
        

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();
        
        File myOutput = new File("D:\\Documents\\dota 2 coding\\"+steamID+"\\"+searchType+".xml");
        myOutput.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(myOutput);
        xform.transform(new DOMSource(xmlDoc), new StreamResult(myOutput));
        Element rootElement = xmlDoc.createElement("products");
        
        Element mainElement = xmlDoc.createElement("product");
        
        Text productNameText = xmlDoc.createTextNode("designer plate");
        
        Element productName = xmlDoc.createElement("name");
        
        productName.appendChild(productNameText);
        
        mainElement.appendChild(productName);
        
        rootElement.appendChild(productName);   
	}
	
	protected static void writeProductsXML(String iurl, String directory) throws ParserConfigurationException,IOException,SAXException, TransformerException{
		
		URL url = new URL(iurl);
        URLConnection conn = url.openConnection();
        
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("information.html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
        DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docfactory.newDocumentBuilder();
        Document xmlDoc = builder.parse(conn.getInputStream());
        

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();
        
        File myOutput = new File(directory);
        myOutput.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(myOutput);
        xform.transform(new DOMSource(xmlDoc), new StreamResult(myOutput));
        Element rootElement = xmlDoc.createElement("products");
        
        Element mainElement = xmlDoc.createElement("product");
        
        Text productNameText = xmlDoc.createTextNode("designer plate");
        
        Element productName = xmlDoc.createElement("name");
        
        productName.appendChild(productNameText);
        
        mainElement.appendChild(productName);
        
        rootElement.appendChild(productName);   
	}

}

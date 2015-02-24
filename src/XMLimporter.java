/*package xml.parsing.example;*/

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

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
	static String hero_ID="hero_id=";
	static String startAt = "start_at_match_id=";
	static String tournamentOnly = "tournament_games_only=";
	
	
	
	public static void getInfo() throws ParserConfigurationException, IOException, SAXException, TransformerException, JSONException{
		String url = searchString(remainderURL,key,accountID);			//compile the string
		writeProductsXML(url);
		
		System.out.println("parse file");
		
		XMLParser.parseInfo(searchType);//write the results to a file
		
		System.out.println("finished file");
		
/*		XMLParser.parseInfo(searchType);*/										//parse the results depending on which search type

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
	
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, JSONException {
		// TODO Auto-generated method stub
		GetHeroes.checkHeroes(key); //update the list of heroes upon startup
		System.out.println("heroes checked");
		getInfo();					//ask for inputs to generate search URLS

	}
	

	
	
	private static void writeProductsXML(String iurl) throws ParserConfigurationException,IOException,SAXException, TransformerException{
		System.out.println("write products");
		URL url = new URL(iurl);
		
		System.out.println("iurl: "+iurl);
        URLConnection conn = url.openConnection();
        
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("information.html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
        DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docfactory.newDocumentBuilder();
        Document xmlDoc = builder.parse(conn.getInputStream());
        

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();
        
        File myOutput = new File("D:\\Documents\\dota 2 coding\\"+steamID+"\\"+searchType+".json");
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
		
		System.out.println("writeproducts");
		URL url = new URL(iurl);
		System.out.println(iurl);
        URLConnection conn = url.openConnection();
        System.out.println("conn opened");
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("hero_list.txt");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
        System.out.println("doc fac starting");
        DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docfactory.newDocumentBuilder();
        Document xmlDoc = builder.parse(conn.getInputStream());
        System.out.println("doc fac ended");

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();
        
        System.out.println("writing to: "+directory);
        
        File myOutput = new File(directory);
        System.out.println("writing to: "+directory);
        
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
	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	

	
	//reads the url for the json object
	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		  
		  System.out.println("fetching json string");
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      
	      String jsonText = readAll(rd);//json string
	      
	      
	      JSONObject json = new JSONObject(jsonText); //create the json object
	      System.out.println("json object created");
	      
	      
	      return json;
	      
	    } finally {
	      is.close();
	    }
	  }
	  
	  public static void writeJSONfile(String filename, String directory, JSONObject jsontowrite){
		  
		  System.out.println("writing json file");
		  File file = new File("D:\\Documents\\dota 2 coding\\"+steamID+"\\"+filename+".txt");
		  
		  file.getParentFile().mkdirs();
		  try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	  }
	

}

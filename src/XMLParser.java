import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class XMLParser {
	
	public static void parseInfo(String searchType) throws IOException, JSONException{
		String matchdetailsJSON = readFile("D:\\Documents\\dota 2 coding\\getmatchdetails.txt",StandardCharsets.UTF_8);
		String newJSON = matchdetailsJSON.replaceAll("\\s+","");
		System.out.println(newJSON);
		JSONObject myjson = new JSONObject(newJSON);
		JSONArray the_json_array = myjson.getJSONArray("results");
		
		int size = the_json_array.length();
		ArrayList<JSONObject> arrays=new ArrayList<JSONObject>();
		
		for (int i=0;i<size;i++){
			JSONObject other_json_ob = the_json_array.getJSONObject(i);
			arrays.add(other_json_ob);
		}
		
		JSONObject[] jsons = new JSONObject[arrays.size()];
		arrays.toArray(jsons);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	
	

}

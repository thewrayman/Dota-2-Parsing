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
		
		String matchdetailsJSON = readFile("D:\\Documents\\dota 2 coding\\"+inputManagement.steamID+"\\"+searchType+".json",StandardCharsets.UTF_8);
		
		String newJSON = matchdetailsJSON.replaceAll("\\s+","");
		
		System.out.println(newJSON);
		
		JSONParseGetmh(newJSON);
		
		
	}
	
	public static void JSONParseGetmh(String jsonstring){
		try{
			//go through every row
			JSONObject rootObject = new JSONObject(jsonstring);
			JSONArray rows = rootObject.getJSONArray("rows");
			
			//create all root elements
			for(int i=0; i<rows.length();i++){
				JSONObject row = rows.getJSONObject(i);
				JSONArray elements = row.getJSONArray("elements");
				
				//deserialise each root element
				for(int j=0; j<elements.length();j++){
					JSONObject element = elements.getJSONObject(j);
					//do this now for every field wanted
					
					JSONObject status = element.getJSONObject("status");
					JSONObject results = element.getJSONObject("num_results");
					JSONObject totalresults = element.getJSONObject("total_results");
					JSONObject results_remain = element.getJSONObject("results_remaining");
					
					//deserialise the array
					JSONArray matches = element.getJSONArray("matches");
					
					//deserialise the objects inside the array
					for (int x =0;x<matches.length();x++){
						JSONObject match = matches.getJSONObject(x);
						
						JSONObject match_id = match.getJSONObject("match_id");
						JSONObject match_seq = match.getJSONObject("match_seq_num");
						JSONObject start_time = match.getJSONObject("start_time");
						JSONObject lobby_type = match.getJSONObject("lobby_type");
						
						JSONArray players = match.getJSONArray("players");
						
						for(int y=0;y<players.length();y++){
							JSONObject player = players.getJSONObject(y);
							
							JSONObject account_id = player.getJSONObject("account_id");
							JSONObject player_slot = player.getJSONObject("player_slot");
							JSONObject hero_id = player.getJSONObject("hero_id");
		
						}
					
					}
					
			}			
			}
		} catch (JSONException e){
			//JSON PARSING ERROR
			e.printStackTrace();
		}
	}
	
	
	public static void GMHgson (String jsonstring){
		
	}
	
	

	
	static String readFile(String path, Charset encoding) throws IOException {
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}

}

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class GetHeroes extends XMLimporter {
		
	static String heroURL = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=";

	static void checkHeroes(String key) throws ParserConfigurationException, IOException, SAXException, TransformerException{
		System.out.println("checking heroes");
		String hURL = (heroURL+key+"&language=en_us"+"&"+makeXML);
		writeHeroes(hURL);
	}
	
	static void writeHeroes(String url) throws ParserConfigurationException, IOException, SAXException, TransformerException{
		System.out.println("writing heroes");
		String target = "C:\\users\\emmet\\Documents\\dota 2 coding\\hero_list.xml";
		XMLimporter.writeProductsXML(url, target);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

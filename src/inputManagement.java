import java.util.Scanner;


public class inputManagement {
	static long steamID;
	static String steamID64;
	int heroID;
	

	
	static String searchType = "";
	String option1 = "";
	String option2 = "";
	static String baseURL = "https://api.steampowered.com/IDOTA2Match_570/";
	String a;
	static String b;
	String c;
	
	public static String getSearchType(){
		
		
		DefaultGUI.dropdownselect();
		
		if(checkHelp(searchType,0)==true){
			getSearchType();
		}
		
		System.out.println("searching for:" + searchType);
		return searchType;
	}
	
	public static long getUserID(){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter in your steamID");
		b = input.nextLine();
		
		if(checkHelp(b,1)==true){
			getUserID();
		}
		
		long steam = Long.parseLong(b);
		
		System.out.println("searching for steamID: "+ b);
		steamID=steam;
		return steam;
	}
	
	
	
	public static String searchString(String remainderURL,String key,String accountID){
		System.out.println("Enter 'help' if you are unsure.");
		getSearchType();
		getUserID();
		
		System.out.println("URl is:"+baseURL+searchType+remainderURL+key+steamID);
		String url = baseURL+searchType+remainderURL+key+"&"+ accountID + b;
		System.out.println(url);
		return url;
	}
	
	
	
	public static boolean checkHelp(String searchinput, int x){;
		System.out.println(searchinput);
		
		if (searchinput.equals("help")){
			if(x ==0){
				System.out.println("The search type is what method you want to use to find a game");
			}
			else{
				System.out.println("The steam ID can be found on your account");
			}
			return true;
			
		}
		else{
			return false;
		}

		
	}


}

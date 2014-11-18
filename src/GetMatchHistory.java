import java.util.List;


public class GetMatchHistory {
	long match_ID;
	private long start_time;
	private int lobby_type;
	List<Player> players;
	
	public GetMatchHistory(){}
	public GetMatchHistory(long match_ID, long start_time, int lobby_type,List<Player> players){
		super();
		this.match_ID = match_ID;
		this.start_time = start_time;
		this.lobby_type = lobby_type;
		this.players=players;
	}
	
	//
	public long getMatchID(){
		return match_ID;
	}
	
	public void setMatchID(long match){
		match_ID = match;
	}
	
	private long getStartTime(){
		return start_time;
	}
	
	private void setStartTime(long start){
		start_time = start;
	}
	
	private int getLobbyType(){
		return lobby_type;
	}
	
	public void setLobbyType(int lobby){
		lobby_type=lobby;
	}

	
	public List<Player> getPlayers(){
		return players;
	}
	
	public void setMatches(List<Player> players){
		this.players=players;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

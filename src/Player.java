
public class Player {

	/**
	 * @param args
	 */
	long account_id;
	private int player_slot;
	private int hero_id;
	
	public Player(long acc_id, int playerslot, int hero_id){
		this.account_id=acc_id;
		this.player_slot = playerslot;
		this.hero_id=hero_id;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private long getAccountID(){
		return account_id;
	}
	
	private void setAccountID(long acc_id){
		account_id=acc_id;
	}
	
	
	private int getPlayerSlot(){
		return player_slot;
	}
	
	
	private void setPlayerSlot(int slot){
		player_slot=slot;
	}
	
	
	private int getHeroID(){
		return hero_id;
	}
	
	
	private void setHeroID(int hero){
		hero_id=hero;
	}

}

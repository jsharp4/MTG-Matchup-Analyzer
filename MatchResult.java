
public class MatchResult {
	
	private int gameOne;
	private int gameTwo;
	private int gameThree;
	private String deck;
	private String opponentDeck;
	private int matchWin = 0;
	
	public MatchResult(String deckA, String deckB, int gameOne, int gameTwo, int gameThree) {
		this.gameOne = gameOne;
		this.gameTwo = gameTwo;
		this.gameThree = gameThree;
			
		deck = deckA;
		opponentDeck = deckB;
		
		if ((gameThree == -1 && gameOne == 1) || (gameThree == 1 )) {
			matchWin = 1;
		}
	}
	
	public int getGameOne() {
		return gameOne;
	}
	
	public int getGameTwo() {
		return gameTwo;
	}
	
	public int getGameThree() {
		return gameThree;
	}
	
	public int getMatchWin() {
		return matchWin;
	}
	
	public String getDeck() {
		return deck;
	}
	
	public String getOpponentDeck() {
		return opponentDeck;
	}
	
	
	
}

import java.text.DecimalFormat;

public class SingleMatchupData implements Comparable<SingleMatchupData> {
	private String deck;
	private String oppDeck;
	private int totalGameOne;
	private double gameOnePercent = 0;
	private int totalPostBoard;
	private double postBoardPercent = 0;
	private int totalMatches;
	private double matchPercent = 0;
	
	public SingleMatchupData(String deck, String oppDeck, int totalGameOne, int totalPostBoard,
			int g1wins, int g2wins, int g3wins, int matchWins, int totalMatches) {
		this.deck = deck;
		this.oppDeck = oppDeck;
		this.totalGameOne = totalGameOne;
		this.totalPostBoard = totalPostBoard;
		this.totalMatches = totalMatches;
		
		if (totalGameOne > 0) gameOnePercent = 100.0 * g1wins / (double) totalGameOne;
		if(totalPostBoard > 0) postBoardPercent = 100.0 * (g2wins + g3wins) / (double) totalPostBoard;
		if (totalMatches > 0) matchPercent = 100.0 * matchWins / (double) totalMatches;
	}
	
	public String toString() {
		DecimalFormat df = new DecimalFormat("##.##");
		return oppDeck + "   " + df.format(gameOnePercent) + " (" + totalGameOne + ") " 
				+ df.format(postBoardPercent) + " (" + totalPostBoard + ")   " 
		+ df.format(matchPercent) + " (" + totalMatches + ")\n";
	}
	
	public double getTotalMatches(){
		return totalMatches;
	}

	@Override
	public int compareTo(SingleMatchupData other) {
		if (totalMatches > other.getTotalMatches()) return 1;
		else if (totalMatches < other.getTotalMatches())return -1;
		return 0;
	}
	
}

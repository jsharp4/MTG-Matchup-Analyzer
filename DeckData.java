import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DeckData {
	private DataTable data;
	private String deck;
	private PriorityQueue<SingleMatchupData> matchups;
	
	public DeckData(DataTable data, String deck) {
		this.deck = deck;
		this.data = data;
		ArrayList<String> deckNames = data.getDecks();
		
		matchups = new PriorityQueue<SingleMatchupData>();
		for(int i = 0; i < deckNames.size(); ++i) {
			if (deckNames.get(i) != deck) {
				SingleMatchupData matchUp = data.analyzeMatchup(deck, deckNames.get(i));
				matchups.add(matchUp);
			} //if
		} //for
	}
	
	public String toString() {
		String output = deck + "\n\n";
		Iterator<SingleMatchupData> itr = matchups.iterator();
		while (itr.hasNext()) {
			output += itr.next();
		}
		return output;
	}
}

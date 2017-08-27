import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataTable {
	private ArrayList<String> decks;
	private int[][] gameOnes;
	private int[][] gameTwos;
	private int[][] gameThrees;
	private int[][] matchWins;

	public DataTable(ArrayList<String> gauntlet, File file) throws FileNotFoundException {
		decks = gauntlet;
		Scanner scnr = new Scanner(file);
		boolean empty = false;
		
		gameOnes = new int[decks.size()][decks.size()];
		gameTwos = new int[decks.size()][decks.size()];
		gameThrees = new int[decks.size()][decks.size()];
		matchWins = new int[decks.size()][decks.size()];
		
		if(!scnr.hasNextLine()) {
			empty = true;
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				if (!empty) {
					gameOnes[i][j] = scnr.nextInt();
				}
				else gameOnes[i][j] = 0;
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				if (!empty) {
					gameTwos[i][j] = scnr.nextInt();
				}
				else gameTwos[i][j] = 0;
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				if (!empty) {
					gameThrees[i][j] = scnr.nextInt();
				}
				else gameThrees[i][j] = 0;
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				if (!empty) {
					matchWins[i][j] = scnr.nextInt();
				}
				else matchWins[i][j] = 0;
			}
		}
	}

	public void addResult(MatchResult match) {
		int deckPos = lookUpPos(match.getDeck());
		int oppDeckPos = lookUpPos(match.getOpponentDeck());

		if (match.getGameOne() > 0) gameOnes[deckPos][oppDeckPos] += 1;
		else gameOnes[oppDeckPos][deckPos] += 1;

		if (match.getGameTwo() > 0) gameTwos[deckPos][oppDeckPos] += 1;
		else gameTwos[oppDeckPos][deckPos] += 1;

		if (match.getGameThree() > 0) gameThrees[deckPos][oppDeckPos] += 1;
		if (match.getGameThree() == 0) gameThrees[oppDeckPos][deckPos] += 1;

		if (match.getMatchWin() > 0) matchWins[deckPos][oppDeckPos] += 1;
		else matchWins[oppDeckPos][deckPos] += 1;

	}

	public int lookUpPos(String deck) {
		int deckPos = -1;
		for (int i = 0; i < decks.size(); ++i) {
			if (decks.get(i).equals(deck)) {
				deckPos = i;
				break;
			}
		}
		return deckPos;
	}

	public int totalPreBoard(int deckPos, int oppDeckPos) {
		return gameOnes[deckPos][oppDeckPos] + gameOnes[oppDeckPos][deckPos];
	}

	public int totalPostBoard(int deckPos, int oppDeckPos) {
		return gameTwos[deckPos][oppDeckPos] + gameTwos[oppDeckPos][deckPos]
				+ gameThrees[deckPos][oppDeckPos] + gameThrees[oppDeckPos][deckPos];
	}

	public int totalMatches(int deckPos, int oppDeckPos) {
		return matchWins[deckPos][oppDeckPos] + matchWins[oppDeckPos][deckPos];
	}

	public int getStat(int type, int i, int j) {
		if (type == 1) return gameOnes[i][j];
		if (type == 2) return gameTwos[i][j];
		if (type == 3) return gameThrees[i][j];
		return matchWins[i][j];
	}

	public ArrayList<String> getDecks() {
		return decks;
	}

	public SingleMatchupData analyzeMatchup(String deck, String oppDeck) {
		int deckPos = lookUpPos(deck);
		int oppDeckPos = lookUpPos(oppDeck);
		return new SingleMatchupData(deck, oppDeck, totalPreBoard(deckPos, oppDeckPos),
				totalPostBoard(deckPos, oppDeckPos), getStat(1, deckPos, oppDeckPos), 
				getStat(2, deckPos, oppDeckPos), getStat(3, deckPos, oppDeckPos), 
				getStat(4, deckPos, oppDeckPos), totalMatches(deckPos, oppDeckPos));
	}

	public String toString() {
		String raw = "";
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				raw += gameOnes[i][j] + " ";
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				raw += gameTwos[i][j] + " ";
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				raw += gameThrees[i][j] + " ";
			}
		}
		for (int i = 0; i < decks.size(); ++i) {
			for (int j = 0; j < decks.size(); ++j) {
				raw += matchWins[i][j] + " ";
			}
		}
		return raw;
	}
}

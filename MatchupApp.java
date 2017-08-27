import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchupApp {

	public static void main(String[] args) throws FileNotFoundException {
		File rawInput = new File(args[0]);
		File output = new File(args[1]);
		File deckNames = new File(args[2]);
		Scanner gauntletScnr = new Scanner(deckNames);

		Scanner scnr = new Scanner(System.in);

		ArrayList<String> decks = new ArrayList<String>();

		if (!gauntletScnr.hasNextLine()) {
			System.out.println("Matchup Anaylzer\nEnter a deck, or \"40% Zoo\" when finished.");
			while(true) {
				System.out.println("Deck name: ");
				String next = scnr.nextLine();
				if (!next.equals("40% Zoo")) {
					decks.add(next);
				}
				else break;

			}
			PrintWriter writer = new PrintWriter(deckNames);
			for (int i = 0; i < decks.size() - 1; ++i) {
				writer.print(decks.get(i) + ",");
			}
			writer.print(decks.get(decks.size() - 1));
			writer.close();
		}
		else {
			String[] split = gauntletScnr.nextLine().split(",");
			for (String deck: split) {
				decks.add(deck);
			}
		}

		DataTable table = new DataTable(decks, rawInput);


		String deckA = "";
		String deckB = "";
		int gameOne = -1;
		int gameTwo = -1;
		int gameThree = -1;

		char newResult = 'Y';
		while (newResult == 'Y') {
			System.out.println("Enter a match result? (Y/N)");
			newResult = scnr.nextLine().toUpperCase().charAt(0);
			if (newResult == 'Y') {
				System.out.println("Enter deck A: ");
				deckA = scnr.nextLine();

				System.out.println("Enter deck B: ");
				deckB = scnr.nextLine();

				System.out.println("Match points earned for deck A in game one (1 for win, 0 for loss): ");
		gameOne = scnr.nextInt();

				System.out.println("Match points earned for deck A in game two (1 for win, 0 for loss): ");
				gameTwo = scnr.nextInt();
				scnr.nextLine();

				if (gameOne + gameTwo == 1) {
					System.out.println("Match points earned for deck A in game three (1 for win, 0 for loss): ");
					gameThree = scnr.nextInt();
					scnr.nextLine();
				}
				MatchResult result = new MatchResult(deckA, deckB, gameOne, gameTwo, gameThree);
				table.addResult(result);
			}
		}
		PrintWriter inWrite = new PrintWriter(rawInput);
		inWrite.print(table);
		inWrite.close();
		
		PrintWriter outWrite = new PrintWriter(output);
		for (int i = 0; i < decks.size(); ++i) {
			outWrite.println(new DeckData(table, decks.get(i)));
		}
		outWrite.close();
		
	}	


}

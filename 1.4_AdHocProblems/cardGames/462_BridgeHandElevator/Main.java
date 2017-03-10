import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		Hand hand;

		while(sc.hasNext()) {
			line = sc.nextLine();
			hand = new Hand(line.split(" "));

			System.out.println(hand.getPlay());
		}
	}

	static class Hand {
		// spades, hearts, diamonds, clubs
		static int[] ACES = new int[] { 1, 14, 27, 40 };
		static int[] JACKS = new int[] { 11, 24, 37, 50 };
		static int[] QUEENS = new int[] { 12, 25, 38, 51 };
		static int[] KINGS = new int[] { 13, 26, 39, 52 };

		int score;
		int noTrumpScore;
		// spades, hearts, diamonds, clubs
		boolean[] hasAce;
		boolean[] hasKing;
		boolean[] hasQueen;
		boolean[] hasJack;
		boolean[] isSuitStopped;
		int[] suitCounts;

		Hand(String[] cards) {
			int addition;
			Integer cardValue;

			score = 0;
			noTrumpScore = 0;
			suitCounts = new int[]{ 0, 0, 0, 0};
			hasAce = new boolean[]{ false, false, false, false };
			hasKing = new boolean[]{ false, false, false, false };
			hasQueen = new boolean[]{ false, false, false, false };
			hasJack = new boolean[]{ false, false, false, false };
			isSuitStopped = new boolean[]{ false, false, false, false };
			
			for (String card : cards) {
				addition = 0;
				cardValue = cardsMapping.get(card);
				
				// if ace add 4
				addition = checkForSpecialCard(cardValue, ACES, hasAce, 4);

				// if king add 3
				if (addition == 0) {
					addition += checkForSpecialCard(cardValue, KINGS, hasKing, 3);	
				}

				// if queen add 2
				if (addition == 0) {
					addition += checkForSpecialCard(cardValue, QUEENS, hasQueen, 2);
				}

				// if jack add 1
				if (addition == 0) {
					addition += checkForSpecialCard(cardValue, JACKS, hasJack, 1);
				}

				score += addition;
				noTrumpScore += addition;

				// is the card of spades
				if(cardValue < 14) { ++suitCounts[0]; } 
				// is the card of hearts
				else if(cardValue < 27) { ++suitCounts[1]; } 
				// is the card of diamonds
				else if(cardValue < 40) { ++suitCounts[2]; } 
				// is the card of clubs
				else if(cardValue < 53) { ++suitCounts[3]; } 
			}

			for (int i = 0; i < suitCounts.length; ++i) {
				// A suit is "stopped" if it contains an ace, or if it contains a 
				// king and at least one other card, or if it contains a queen and 
				// at least two other cards.
				if(hasAce[i] || (hasKing[i] && suitCounts[i] >= 2) || 
				   (hasQueen[i] && suitCounts[i] >= 3)) {
					isSuitStopped[i] = true;
				}


				// 2. Subtract a point for any king of a suit in which the hand holds
				// no other cards.
				if (hasKing[i] && suitCounts[i] == 1) {
					--score;
					--noTrumpScore;
				}

				// 3. Subtract a point for any queen in a suit in which the hand holds 
				// only zero or one other cards.
				if (hasQueen[i] && suitCounts[i] < 3) {
					--score;
					--noTrumpScore;
				}

				// 4. Subtract a point for any jack in a suit in which the hand holds 
				// only zero, one, or two other cards.
				if (hasJack[i] && suitCounts[i] < 4) {
					--score;
					--noTrumpScore;
				}

				// 5. Add a point for each suit in which the hand contains exactly two 
				// cards.
				if (suitCounts[i] == 2) {
					++score;
				}

			
				// 6. Add two points for each suit in which the hand contains exactly one 
				// card.
				if (suitCounts[i] == 1) {
					score += 2;
				}

				// 7. Add two points for each suit in which the hand contains no cards.
				if (suitCounts[i] == 0) {
					score += 2;
				}
			}
		}

		int checkForSpecialCard(int cardValue, int[] valueInSuits, boolean[] cardFlags, int scoreToGain) {
			for (int i = 0; i < valueInSuits.length; ++i) {
				if(cardValue == valueInSuits[i]){
					cardFlags[i] = true;
					return scoreToGain;
				}
			}

			return 0;
		}

		String getPlay() {
			// If the hand evaluates to fewer than 14 points, then the player
			// must pass.
			if(score < 14) {
				return "PASS";
			}

			// One may open bidding in “no trump” if the hand evaluates to 16 
			// or more points ignoring rules 5, 6, and 7 and if all four suits 
			// are stopped. A no trump bid is always preferred over a suit bid 
			// when both are possible.
			if(isNoTrump()) {
				return "BID NO-TRUMP";
			}
		
			// One may open bidding in a suit if the hand evaluates to 14 or 
			// more points. Bidding is always opened in one of the suits with 
			// the most cards.
			// NOTE: since we didn't hit the top cases we are a 14 or greater
			return "BID " + getTopSuit();
		}

		boolean isNoTrump() {
			return isSuitStopped[0] && isSuitStopped[1] && 
				   isSuitStopped[2] && isSuitStopped[3] &&
				   noTrumpScore >= 16;
		}

		String getTopSuit() {
			int topSuit = 0;
			int max = 0;

			for (int i = 0; i < suitCounts.length; ++i) {
				if(suitCounts[i] > max) {
					topSuit = i;
					max = suitCounts[i];
				}
			}

			switch(topSuit) {
				case 0:
					return "S";
				case 1:
					return "H";
				case 2:
					return "D";
				case 3:
					return "C";
				default:
					return "";
			}
		}
	}

	static final HashMap<String, Integer> cardsMapping = new HashMap<String, Integer>();
	static {
		// spades
		cardsMapping.put("AS", 1);
		cardsMapping.put("2S", 2);
		cardsMapping.put("3S", 3);
		cardsMapping.put("4S", 4);
		cardsMapping.put("5S", 5);
		cardsMapping.put("6S", 6);
		cardsMapping.put("7S", 7);
		cardsMapping.put("8S", 8);
		cardsMapping.put("9S", 9);
		cardsMapping.put("TS", 10);
		cardsMapping.put("JS", 11);
		cardsMapping.put("QS", 12);
		cardsMapping.put("KS", 13);
		// hearts
		cardsMapping.put("AH", 14);
		cardsMapping.put("2H", 15);
		cardsMapping.put("3H", 16);
		cardsMapping.put("4H", 17);
		cardsMapping.put("5H", 18);
		cardsMapping.put("6H", 19);
		cardsMapping.put("7H", 20);
		cardsMapping.put("8H", 21);
		cardsMapping.put("9H", 22);
		cardsMapping.put("TH", 23);
		cardsMapping.put("JH", 24);
		cardsMapping.put("QH", 25);
		cardsMapping.put("KH", 26);
		// Diamonds
		cardsMapping.put("AD", 27);
		cardsMapping.put("2D", 28);
		cardsMapping.put("3D", 29);
		cardsMapping.put("4D", 30);
		cardsMapping.put("5D", 31);
		cardsMapping.put("6D", 32);
		cardsMapping.put("7D", 33);
		cardsMapping.put("8D", 34);
		cardsMapping.put("9D", 35);
		cardsMapping.put("TD", 36);
		cardsMapping.put("JD", 37);
		cardsMapping.put("QD", 38);
		cardsMapping.put("KD", 39);
		// Clubs
		cardsMapping.put("AC", 40);
		cardsMapping.put("2C", 41);
		cardsMapping.put("3C", 42);
		cardsMapping.put("4C", 43);
		cardsMapping.put("5C", 44);
		cardsMapping.put("6C", 45);
		cardsMapping.put("7C", 46);
		cardsMapping.put("8C", 47);
		cardsMapping.put("9C", 48);
		cardsMapping.put("TC", 49);
		cardsMapping.put("JC", 50);
		cardsMapping.put("QC", 51);
		cardsMapping.put("KC", 52);
	}	
}

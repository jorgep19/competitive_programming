import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] princess;
		int[] prince;
		int n; 
		int princeWinCount;
		boolean[] cardsInPlay;

		while(true) {
			cardsInPlay = new boolean[53];
			princess = new int[] { -1, -1, -1 };
			prince = new int[] { -1, -1 };
			princeWinCount = 0;

			// parse the princess cards
			n = sc.nextInt();
			if (n == 0) { break; } 
			cardsInPlay[n] = true;
			insert(princess, n, true);

			n = sc.nextInt();
			cardsInPlay[n] = true;
			insert(princess, n, true);
			n = sc.nextInt();
			cardsInPlay[n] = true;
			insert(princess, n, true);

			// parse the prince cards
			n = sc.nextInt();
			cardsInPlay[n] = true;
			insert(prince, n, false);
			n = sc.nextInt();
			cardsInPlay[n] = true;
			insert(prince, n, false);

			// play first round
			if(playRound(prince[0], princess)) {
				++princeWinCount;
			}

			// play second round
			if(playRound(prince[1], princess)) {
				++princeWinCount;
			}

			System.out.println(findThirdCard(princess, cardsInPlay, princeWinCount));			
		}
	}

	public static void insert(int[] arr, int n, boolean isAsc) {
		int i;
		int tmp;
		boolean check;

		for(i = 0; i < arr.length; ++i) {
			if(arr[i] == -1) { break; }

			check = isAsc ? n < arr[i] : n > arr[i];

			if(check) {       
				for(int j = i; j < arr.length; ++j) {
					tmp = arr[j];
					arr[j] = n;
					n = tmp;
					if(n == -1) { break; }
				}

				return;
			}
		}

		arr[i] = n;
	}

	public static boolean playRound(int princeCard, int[] princess) {
		int firstUnplayedIndex = -1;


		for(int i = 0; i < princess.length; ++i) {
			if (princess[i] == -1) { 
				continue; 
			} else { 
				if(firstUnplayedIndex == -1) { firstUnplayedIndex = i; }
			}

			if(princeCard < princess[i]) {
				princess[i] = -1;
				return false;
			}
		}

		princess[firstUnplayedIndex] = -1;
		return true;
	}

	public static int findThirdCard(int[] princess, boolean[] cardsInPlay, int princeWinCount) {
		// prince already lost by the third round
		if(princeWinCount == 0) {
			return -1;
		}

		// prince already has enough to win two rounds
		if(princeWinCount == 2) {
			for(int i = 1; i < cardsInPlay.length; ++i) {
				if(!cardsInPlay[i]) {
					return i;
				}	
			}
		}


		int card = -1; 
		for(int i = 0; i < princess.length; ++i) {
			// if we are at the card to beat
			if(princess[i] != -1) {
				card = princess[i] + 1;
				while(cardsInPlay[card]) { 
					++card; 
					// princess last card is 52 :\
					if(card == 53) { return -1; }
				}

				break;
			}
		}

		return card;
	}
}
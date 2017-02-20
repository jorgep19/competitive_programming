import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int participants;
		int budget;
		int hotels;
		int weeks;

		int pricePerHead;
		int availableBeds; 
		int hotelPrice;
			
		int out = -1;

		while(sc.hasNext()) {
			out = -1;
			participants = sc.nextInt();
			budget = sc.nextInt();
			hotels = sc.nextInt();
			weeks = sc.nextInt();


			for (int i = 0; i < hotels; ++i) {
				pricePerHead = sc.nextInt();	
				hotelPrice = pricePerHead * participants;

				for (int j = 0; j < weeks; ++j) {
					availableBeds = sc.nextInt();

					if(hotelPrice <= budget && availableBeds >= participants
					   && (hotelPrice < out || out == -1)) {
						out = hotelPrice;
					}
				}
			}

			if(out == -1) {
				System.out.println("stay home");
			} else {
				System.out.println(out);
			}
		}
		
	}
}
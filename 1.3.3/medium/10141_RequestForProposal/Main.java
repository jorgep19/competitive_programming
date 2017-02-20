import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int rfpNumber = 1;
		int reqCount;
		int propCount;
		
		String name;
		double price;
		int reqMet;
		double compliance;

		String bestName;
		double bestPrice;
		double bestCompliance;

		while(true) {
			bestName = null;
			bestPrice = Double.MAX_VALUE;
			bestCompliance = 0;

			reqCount = sc.nextInt();
			propCount = sc.nextInt();

			// input termiantion
			if(reqCount == 0 && propCount == 0) { break; }

			// +1 to eat the end of the line of the req and prop count
			// skip the requirement lines input
			for(int r = 0; r < reqCount + 1; ++r) {
				sc.nextLine();
			}	

			for(int p = 0; p < propCount; ++p) {
				name = sc.nextLine();
				price = sc.nextDouble();
				reqMet = sc.nextInt();

				// +1 to eat the end of the line of the price and met count
				// skip the requirement lines input
				for(int r = 0; r < reqMet + 1; ++r) {
					sc.nextLine();
				}	

				compliance = reqMet / (reqCount * 1.0);

				if(bestName == null || compliance > bestCompliance
				   || (compliance == bestCompliance && price < bestPrice)) {
					bestName = name;
					bestPrice = price;
					bestCompliance = compliance;
				}

			}

			if(rfpNumber > 1) { System.out.println(); }
			System.out.print(String.format("RFP #%d\n%s\n", rfpNumber, bestName));

			++rfpNumber;
		}
	}
}
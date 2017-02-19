import java.util.*;

class Main {
	public static void main(String[] args) {
		int loanMonths;
		double downPayment;
		double monthlyPayment;
		double loanPrincipal;
		double carValue;
		
		int months;
		int recordsCount;
		int currentDepIndex;
		DepreciationRecord[] depRecords;

		Scanner sc = new Scanner(System.in);

		loanMonths = sc.nextInt();
		while(loanMonths > 0) {
			downPayment = sc.nextDouble(); 
			loanPrincipal = sc.nextDouble();
			monthlyPayment = loanPrincipal/loanMonths;

			recordsCount = sc.nextInt();
			depRecords = new DepreciationRecord[recordsCount];
			for(int i = 0; i < recordsCount; ++i) {
				depRecords[i] = new DepreciationRecord(sc.nextInt(), sc.nextDouble());
			}
			
			currentDepIndex = 0;
			// drive out depreciation
			carValue = (loanPrincipal + downPayment) * (1.0 - depRecords[currentDepIndex].depreciation);
			months = 0;
			
			while(carValue < loanPrincipal) {
				// increase month
				++months;

				// if there are more dep records and the month for the next iteration 
				// is the month of the next dep record the update the dep index.
				if((currentDepIndex+1) < recordsCount 
					&& months == depRecords[currentDepIndex+1].month) { 
					++currentDepIndex;
				}

				loanPrincipal -= monthlyPayment;
				carValue *= (1.0 - depRecords[currentDepIndex].depreciation);

			}

			System.out.println(months == 1 ? "1 month" : months + " months");

			loanMonths = sc.nextInt();
		}

	}

	static class DepreciationRecord {
		final int month;
		final double depreciation;

		DepreciationRecord(int m, double d) {
			this.month = m;
			this.depreciation = d;
		}
	}
}
import java.util.*;

class Main {
	static final int[] LAST_DAY_OF_MONTH = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
	static final int FEB_28 = 59;	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int daysToAdd;
		int day;
		int month; 
		int year;

		int[] resultDate;

		while(true) {
			daysToAdd = sc.nextInt();
			day = sc.nextInt();
			month = sc.nextInt();
			year = sc.nextInt();

			if(daysToAdd == 0 && day == 0 && month == 0 && year == 0) {
				break;
			}

			resultDate = addDays(month, day, year, daysToAdd);
			System.out.println(String.format("%d %d %d", resultDate[0], resultDate[1], resultDate[2]));
		}
	}

	// [0] day
	// [1] month
	// [2] year
	private static int[] addDays(int month, int day, int year, int daysToAdd) {
		int dayOfYear = getDayOfYear(month, day, year);
		
		boolean isLeapYear = isLeapYear(year);
		dayOfYear += daysToAdd;
		while(!isLeapYear && dayOfYear > 365 || 
			  isLeapYear && dayOfYear > 366) {
			
			dayOfYear = isLeapYear ? (dayOfYear - 366) : (dayOfYear - 365);
			++year;
			isLeapYear = isLeapYear(year);
		}

		int[] md = getMonthAndDate(dayOfYear, year);

		return new int[]{ md[0], md[1], year };
	}

	private static int getDayOfYear(int month, int day, int year) {
		int lastOfPreviousMonth =  LAST_DAY_OF_MONTH[(month-1)];

		if(isLeapYear(year) && month > 2) { ++lastOfPreviousMonth; }

		return lastOfPreviousMonth + day;
	}

	private static boolean isLeapYear(int year) {
		return ((year % 4) == 0) && (((year % 100) != 0) || ((year % 400) == 0));
	}
	
	public static boolean isAffectedByLeapYear(int day, int year) {
		return isLeapYear(year) && (day > FEB_28);
	}

	// [0] day
	// [1] month
	public static int[] getMonthAndDate(int day, int year) {
		int m = 0;
		int d = 0;
		int lastOfMonth = 0;
		int lastOfPreviousMonth = 0;

		if(isLeapYear(year) && day == 60) {
			return new int[] { 29, 2 };
		}

		for (; m < LAST_DAY_OF_MONTH.length; ++m) {
			lastOfMonth = isAffectedByLeapYear(LAST_DAY_OF_MONTH[m], year) ? 
				(LAST_DAY_OF_MONTH[m] + 1) : LAST_DAY_OF_MONTH[m];

			if(lastOfMonth >= day) {
				lastOfPreviousMonth = isAffectedByLeapYear(day, year) ? 
					(LAST_DAY_OF_MONTH[m-1] + 1) : LAST_DAY_OF_MONTH[m-1];

				d = day - lastOfPreviousMonth;
				break;
			}
		}

		return new int[] { d, m };
	}
}
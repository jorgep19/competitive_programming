import java.util.*;

class Main {
	// Aquarius: 21 - 50
	// Pisces: 51 - 79
	// Aries: 80 - 110
	// Taurus: 111 - 141
	// Gemini: 142 - 172
	// Cancer: 173 - 203
	// Leo: 204 - 233
	// Virgo: 234 - 266
	// Libra: 267 - 296
	// Scorpio: 297 - 326
	// Sagittarius: 327 - 356
	// Capricorn: 357 - 20
	static final String[] SIGNS = new String[]{ "capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius" };
	static final int[] FIRST_DAY_FOR_SIGN = new int[] { 21, 51, 80, 111, 142, 173, 204, 234, 267, 297, 327, 357 }; // shifted one from the signs array

	static final int[] LAST_DAY_OF_MONTH = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };	
	static final int FEB_28 = 59;
	static final int PREG_DAYS = 280;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		
		String dateInput;
		int m;
		int d;
		int y;
		int dInY;
		boolean isLeapYear;
	
		int t = sc.nextInt();
		sc.nextLine(); // eat rest of first line
		

		for(int i = 0; i < t; ++i) {
			dateInput = sc.nextLine();

			m = Integer.parseInt(dateInput.substring(0, 2));
			d = Integer.parseInt(dateInput.substring(2, 4));
			y = Integer.parseInt(dateInput.substring(4));

			dInY = getDayOfYear(m, d, y);

			dInY += PREG_DAYS;
 			isLeapYear = isLeapYear(y);
			if(isLeapYear && dInY > 366) {
				dInY -= 366;
				++y;
			} else if (!isLeapYear && dInY > 365) {
				dInY -= 365;
				++y;
			}

			System.out.println(String.format("%d %s %s", 
				(i+1), getDateString(dInY, y), getSign(dInY, isLeapYear(y))));
		}
	}

	public static int getDayOfYear(int month, int day, int year) {
		int lastOfPreviousMonth =  LAST_DAY_OF_MONTH[(month-1)];

		if(isLeapYear(year) && month > 2) { ++lastOfPreviousMonth; }

		return lastOfPreviousMonth + day;
	}

	public static String getDateString(int day, int year) {
		int m = 0;
		int d = 0;
		int lastOfMonth = 0;
		int lastOfPreviousMonth = 0;

		if(isLeapYear(year) && day == 60) {
			return String.format("02/29/%d", year);
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

		String mStr = (m > 9) ? String.valueOf(m) : String.format("0%d", m);
		String dStr = (d > 9) ? String.valueOf(d) : String.format("0%d", d);
		return String.format("%s/%s/%d", mStr, dStr, year);
	}

	public static boolean isLeapYear(int year) {
		return ((year % 4) == 0) && (((year % 100) != 0) || ((year % 400) == 0));
	}

	public static boolean isAffectedByLeapYear(int day, int year) {
		return isLeapYear(year) && (day > FEB_28);
	}

	public static String getSign(int day, boolean isLeapYear) {
		String sign = "capricorn";
		int limit;

		for (int i = 0; i < FIRST_DAY_FOR_SIGN.length; ++i) {
			limit = FIRST_DAY_FOR_SIGN[i];
			// adjust for leap years
			limit = (isLeapYear && limit > FEB_28) ? (limit +1) : limit;
			if(day < limit) {
				return SIGNS[i];
			}
		}

		return sign;
	}
}
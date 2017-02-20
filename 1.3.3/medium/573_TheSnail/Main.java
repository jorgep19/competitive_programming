import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int height = sc.nextInt();
		double climbOnDay1;
		double climb;
		int slideDown;
		double fatigue;

		int day;
		double currentPos;
		boolean result;

		while(height != 0) {
			climbOnDay1 = sc.nextInt();
			climb = climbOnDay1;
			slideDown = sc.nextInt();
			fatigue = (sc.nextInt()/100.0) * climbOnDay1;

			day = 1;
			currentPos = 0;
			result = false;
			
			while(true) {
				// System.out.print("day: " + day);
				// System.out.print(" | initialHeight: " + currentPos);
				// System.out.print(" | distanceClimbed: " + climb);

				// climb during the day
				currentPos += climb;
				// System.out.print(" | heightAfterClimb: " + currentPos);
				if(currentPos > height) { 
					result = true;
					break; 
				}
				

				// slide down at night
				currentPos -= slideDown;
				if(currentPos < 0) {
					result = false;
					break;
				}

				// System.out.print(" | heightAfterSlide: " + currentPos + "\n");

				// update values for next day
				climb = (climb - fatigue) > 0 ? (climb - fatigue) : 0;
				++day;
			}

			if(result) {
				System.out.println(String.format("success on day %d", day));
			} else {
				System.out.println(String.format("failure on day %d", day));
			}

			height = sc.nextInt();			
		}
	}
}
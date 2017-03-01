import java.util.*;

class Main {
	final static int MINUTE_FACTOR = 360/60;
	final static int HOUR_FACTOR = 360/12;
	final static double MINUTE_IN_HOUR_FACTOR = 360/(12*60.0);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] input;
		int hour;
		int minutes;
		double hourAngle;
		double minutesAngle;
		double deltaAngle;

		while(true){
			input = sc.nextLine().split(":");
			hour = Integer.parseInt(input[0]);
			minutes = Integer.parseInt(input[1]);

			if(hour == 0) { break; }

			minutesAngle = minutes * MINUTE_FACTOR;
			hourAngle = hour * HOUR_FACTOR + minutes * MINUTE_IN_HOUR_FACTOR;
			deltaAngle = hourAngle - minutesAngle;

			if (deltaAngle < 0) {
				deltaAngle += 360;
			}

			if(deltaAngle > 180) {
				deltaAngle = 360 - deltaAngle;
			}  

			System.out.println(String.format("%.3f", deltaAngle));
		}
	}
}
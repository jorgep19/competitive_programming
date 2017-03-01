import java.util.*;

class Main {
	final static int[][] TRANSITIONS = new int[][]{ 
		new int[] { 2, 3, 4, 5, 0 },
		new int[] { 3, 2, 5, 4, 1 },
		new int[] { 1, 0, 2, 2, 2 },
		new int[] { 0, 1, 3, 3, 3 },
		new int[] { 4, 4, 1, 0, 4 },
		new int[] { 5, 5, 0, 1, 5 }
	};

	final static String[] POSITION_LABEL = new String[]{
		"+x", "-x", "+y", "-y", "+z", "-z"
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int l;
		String[] instructions;
		String inst;

		int position;
		int movement;

		while(true) {
			l = sc.nextInt();
			// end of input
			if(l == 0) { break; }
			sc.nextLine(); // eat rest of "l" input line

			instructions = sc.nextLine().split(" ");
			position = 0;

			for(int i = 0; i < instructions.length; ++i) {
				inst = instructions[i];
				
				if(inst.equals("+y")) {
					movement = 0;
				} else if(inst.equals("-y")) {
					movement = 1;
				} else if(inst.equals("+z")) {
					movement = 2;
				} else if(inst.equals("-z")) {
					movement = 3;
				} else { // equals No
					movement = 4;
				}
				// "No" is just ignored

				position = TRANSITIONS[position][movement];
			}

			System.out.println(POSITION_LABEL[position]);
		}
	}
}
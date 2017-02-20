import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner tc;

		int tCount = sc.nextInt();
		sc.nextLine();

		int N;
		int n;
		int max;

		for(int i = 0; i < tCount; ++i) {
			tc = new Scanner(sc.nextLine());

			N = tc.nextInt();
			max = 0;
			
			for(int j = 0; j < N; ++j) {
				n = tc.nextInt();

				if(n > max) {
					max = n;
				}
			}

			System.out.println(String.format("Case %d: %d", (i+1), max));
		}
	}
}
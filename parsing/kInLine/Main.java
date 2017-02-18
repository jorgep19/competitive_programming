import java.util.*;

// c++ 
// int k, ans, v;
// while (scanf("%d", &k) != EOF) {
//   ans = 0;
//   while (k--) { scanf("%d", &v); ans += v; } printf("%d\n", ans);
//}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int sum;
    int k;
    int n;

    while(sc.hasNext()) {
      sum = 0;
      k = sc.nextInt();

      while(k > 0) {
        n = sc.nextInt();
        sum += n;
        --k;
      }

      System.out.println(sum);
    }

    sc.close();
  }
}
import java.util.*;

// c++ 
// int TC, a, b;
// scanf("%d", &TC); // number of test cases
// while (TC--) { // shortcut to repeat until 0
// scanf("%d %d", &a, &b); // compute answer
// printf("%d\n", a + b); // on the fly 
// }

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int i = sc.nextInt();
    int a;
    int b;

    while(i > 0) {
      a = sc.nextInt();
      b = sc.nextInt();
      System.out.println(a + b);

      --i;
    }

    sc.close();
  }
}
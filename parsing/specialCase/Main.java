import java.util.*;

// c++ 
// int a, b;
// stop when both integers are 0
// while (scanf("%d %d", &a, &b), (a || b))
// printf("%d\n", a + b);

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
      
    int a = sc.nextInt();
    int b = sc.nextInt();

    while(a != 0 || b != 0) {
      System.out.println(a + b);

      a = sc.nextInt();
      b = sc.nextInt();
    }

    sc.close();
  }
}
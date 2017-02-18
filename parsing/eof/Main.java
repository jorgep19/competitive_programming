import java.util.*;

// int a, b;
// scanf returns the number of items read while (scanf("%d %d", &a, &b) == 2)
// or you can check for EOF, i.e.
// while (scanf("%d %d", &a, &b) != EOF)
//  printf("%d\n", a + b);

public class Main {
  public static void main(String[] args) {
    int a;
    int b;    
    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()) {
      a = sc.nextInt();
      b = sc.nextInt();

      System.out.println(a + b);
    }

    sc.close();
  }
}
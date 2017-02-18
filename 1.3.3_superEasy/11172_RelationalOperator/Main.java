import java.util.*;

public class Main {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // int k = sc.nextInt();
    // int a;
    // int b;
    // int n;

    // while(k > 0) {
    //   a = sc.nextInt();
    //   b = sc.nextInt();
    //   n = a - b;

    //   if(n == 0) {
    //     System.out.println('=');
    //   } else if (n > 0) {
    //     System.out.println('>');
    //   } else {
    //     System.out.println('<');
    //   }

    //   --k;
    // }

    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int n;

    while(k > 0) {      
      n = sc.nextInt() - sc.nextInt();

      if(n == 0) {
        System.out.println('=');
      } else if (n > 0) {
        System.out.println('>');
      } else {
        System.out.println('<');
      }

      --k;
    }

    sc.close();
  }
}
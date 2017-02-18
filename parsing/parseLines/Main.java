import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Scanner ls;
    int sum;

    while(sc.hasNext()) {
      sum = 0;
      ls = new Scanner(sc.nextLine());
      
      while(ls.hasNext()) {
        sum += ls.nextInt();
      }

      System.out.println(sum);
    }

    sc.close();
  }
}
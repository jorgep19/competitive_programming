import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k;
    int x0;
    int y0;
    int xi;
    int yi;

    while(sc.hasNext()) {
      k = sc.nextInt();
      if(k == 0) { continue; }

      x0 = sc.nextInt();
      y0 = sc.nextInt();

      for(; k > 0; --k) {
        xi = sc.nextInt();
        yi = sc.nextInt();
        
        if(xi - x0 == 0 || yi - y0 == 0) {
          System.out.println("divisa");
        } else if(xi - x0 > 0) { // either I or IV
          if(yi - y0 > 0) { // I
            System.out.println("NE");
          } else { // IV
            System.out.println("SE");
          }
        } else { // either II or III
          if(yi - y0 > 0) { // II
            System.out.println("NO");
          } else { // III
            System.out.println("SO");
          }
        }
      }
    }

    sc.close();
  }
}
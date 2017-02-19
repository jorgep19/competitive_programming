import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = (sc.nextInt() + 1);
    int[] arr;
    int saved;
    int inputCount;

    for (int i = 1; i < k; ++i) {
      arr = new int[] { -1, -1, -1 };
      
      insert(arr, sc.nextInt(), 0);
      insert(arr, sc.nextInt(), 1);
      insert(arr, sc.nextInt(), 2);
      
      
      System.out.println(String.format("Case %d: %d", i, arr[1]));
    }
  }

  public static void insert(int[] arr, int n, int l) {
    int tmp;

    for(int i = 0; i < l; ++i) {
      
      if(n < arr[i]) {       
        for(int j = i; j < l+1; ++j) {
          tmp = arr[j];
          arr[j] = n;
          n = tmp;
        }

        return;
      }
    }

    arr[l] = n;
  }
}
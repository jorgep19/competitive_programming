import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int arr;
    int saved;
    int inputCount;

    while(k > 0) {
      arr = new int[] { -1, -1, -1 };
      
      insert(arr, sc.nextInt(), 0);
      print(arr);
      insert(arr, sc.nextInt(), 1);
      print(arr);
      insert(arr, sc.nextInt(), 2);
      print(arr);
      
      System.out.println(arr[2]);
      --k;
    }
  }

  public static void insert(int[] arr, int n, int l) {
    int tmp;

    for(int i = 0; i < l; ++i) {
      if(n < arr[i]) {       
        for(int j = i+1; j < l; ++j) {
          tmp = ;
        }
        return;
      }
    }

    arr[l] = n;
  }

  public static void print(int[] arr) {
    for(int i = 0; i < arr.length; ++i) {
      System.out.print(arr[i] + ", ");
    }

    System.out.println();
  }
}
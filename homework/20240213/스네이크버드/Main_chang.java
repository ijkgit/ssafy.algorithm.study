import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, L;
	static int[] arr;
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in) ;
		N = sc.nextInt();
		L = sc.nextInt();
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		for (int i = 0; i <N; i++) {
			if(arr[i] <= L) {
				L++;
			}
		}
		System.out.println(L);
		
		
	}
}
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int arr[][];
	static boolean brokenEgg[];
	static int ans = 0;
	public static void shootEgg(int idx) {

		int tmp = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][0] <= 0) {
				tmp++;
			}
		}
		if(tmp == arr.length || tmp == arr.length-1 || arr.length >= idx) {
			ans = Math.max(tmp,ans);
			return;
		}
		if(arr[idx][0] <= 0) {
			shootEgg(idx+1);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			// 상대 계란 내구도가 남아있으면
			System.out.println(i + " " + idx);
			if (i != idx && arr[i][0] > 0 && arr[idx][0] >0) {
				arr[idx][0] -= arr[i][1];
				arr[i][0] -= arr[idx][1];
				shootEgg(idx+1);
				arr[idx][0] += arr[i][1];
				arr[i][0] += arr[idx][1];
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		arr = new int[N][2];
		for (int tc = 0; tc < N; tc++) {
			arr[tc][0] = sc.nextInt();
			arr[tc][1] = sc.nextInt();
		}
	
		
		shootEgg(0);
		System.out.println(ans);
		
	}

}

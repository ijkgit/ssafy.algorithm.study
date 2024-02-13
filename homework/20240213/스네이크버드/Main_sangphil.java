import java.util.*;
import java.io.*;

/*
 * 		[BOJ 16435] 스네이크버드
 * 			배열을 오름차순으로 정렬하고,
 * 			먹이를 먹을 수 없을 때 까지 순차적으로 처리
 */
public class Main_SnakeBird {

	public static void main (String[] args) throws IOException {
		// System.setIn(Main_SnakeBird.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < n; i++) {
			if (arr[i] > m) {
				break;
			}
			m++;
		}
		
		System.out.println(m);
	}
}

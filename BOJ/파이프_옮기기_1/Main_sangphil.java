import java.util.*;
import java.io.*;

/*
 * 		[BOJ 17070] 파이프 옮기기 1
 * 			- DP 적용
 * 			- 가로, 세로, 대각선으로 방문했을 경우의 배열을 따로 관리 
 */
public class Main_sangphil {
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		int[][][] arr = new int[n+1][n+1][4];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1 ; j <= n; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		arr[1][2][1] = 1;
		// 0(가로) - 1(대각) - 2(세로)
		for (int i = 1; i <= n; i++) {
			for (int j = 3; j <=n; j++) {
				// 가로: 가로, 대각
				// 세로: 대각, 세로
				if (arr[i][j][0] != 1) {
					arr[i][j][1] = arr[i][j-1][1] + arr[i][j-1][2];
					arr[i][j][3] = arr[i-1][j][2] + arr[i-1][j][3];
				}
						
				// 대각: 가로, 세로, 대각
				if (arr[i][j][0] != 1 && arr[i][j-1][0] != 1 && arr[i-1][j][0] != 1) {
					arr[i][j][2] = arr[i-1][j-1][1] + arr[i-1][j-1][2] + arr[i-1][j-1][3];
				}
			}
		}
		System.out.println(arr[n][n][1] + arr[n][n][2] + arr[n][n][3]);
	}
}
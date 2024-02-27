import java.util.*;
import java.io.*;

/*
 * 		[BOJ 11562] 백양로 브레이크
 * 			- 플로이드 워샬 적용
 * 			- 단방향 루트를 양방향이라고 가정했을 때, 거리를 1로 설정해서
 * 				양방향 방문이 필요한 수를 체크
 */
public class Main_sangphil {
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		final int INF = 250 * 249 / 2 + 1;
		int[][] connected = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				connected[i][j] = INF;
			}
		}		
		
		int a,b,c;
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			connected[a][b] = 0;
			if (c == 0) {
				connected[b][a] = 1;
			} else {		
				connected[b][a] = 0;
			}
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <=n; j++) {
					if (i != j && connected[i][j] > connected[i][k] + connected[k][j]) {
						connected[i][j] = connected[i][k] + connected[k][j];
					}
				}
			}			
		}
		
		a = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < a; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(connected[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
		}
        
		System.out.println(sb);
	}
}
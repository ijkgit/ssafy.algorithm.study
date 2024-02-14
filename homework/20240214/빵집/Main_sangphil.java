package problem_solving;
import java.util.*;
import java.io.*;

/*
 * 		[BOJ 3109] 빵집
 * 			1. 윗 부분의 행부터 탐색
 * 				오른쪽 위 - 오른쪽 - 오른쪽 아래의 우선순위로 탐색
 * 			
 * 			2. 1초, 256 메가
 * 				완탐: 10000 * 3**500 => TLE
 * 
 * 			3. 한 번 실패한 위치에서는 어떠한 경우에도 실패 -> 마킹해두고 가지 치기
 */
public class Main_sangphil {
	static final int[] dx = {-1,0,1};
	static boolean[][] graph;
	static int r, c;
    
	public static void main (String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				if (line.charAt(j) == 'x') {
					graph[i][j] = true;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < r; i++) {
			if (shoot(i, 0)) ans++;
		}

		System.out.println(ans);
	}
	
	static boolean shoot(int x, int y) {
		if (y == c-1) return true;
		
		for (int i = 0; i < 3; i++) {
			int h = x + dx[i];
			if (h < 0 || h >= r) continue;
			if (graph[h][y+1]) continue;
			
			graph[h][y+1] = true;
			if (shoot(h, y+1)) return true;
		}
		return false;
	}
}
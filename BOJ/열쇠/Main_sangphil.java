import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair {
		int x, y;
		public Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
	static char[][] arr;
	static boolean[][] visited;
	static boolean[] keys;
	
	static int h, w;
	static int upperSize;
	
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			arr = new char[h][w];
			visited = new boolean[h][w];
			keys = new boolean[26];
			upperSize = 0;
			
			Deque<Pair> q = new LinkedList<Pair>();
			
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					arr[i][j] = line.charAt(j);
					if (i == 0 || i == h-1 || j == 0 || j == w-1) {
						if (arr[i][j] != '*') {
							q.add(new Pair(i, j));
							visited[i][j] = true;
							if (Character.isUpperCase(arr[i][j])) upperSize++;
						}
					}
				}
			}
			
			String line = br.readLine();
			if (!line.equals("0")) for (int i = 0; i < line.length(); i++) keys[line.charAt(i)-'a'] = true;
			
			int ans = 0;
			int cnt = 0;

			while (!q.isEmpty()) {
				Pair p = q.poll();
				char c = arr[p.x][p.y];
				// 대문자인 경우
				if (Character.isUpperCase(c)) {
					// 키가 없을 때
					if (!keys[c-'A']) {
						if (upperSize - 1 == q.size()) {
							if (cnt == upperSize-1)break;
							else cnt++;
						}
						q.add(p);
						continue;
					} else {
						cnt = 0;
						upperSize--;
					}
					//키가 있을 때는 진행
				}
				// 소문자인 경우는 그냥 넣고 진행
				else if (Character.isLowerCase(c)) {
					keys[c-'a'] = true;
				}
				// 문서인 경우 정답 카운트 + 1
				else if (c == '$') {
					ans++;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (0 <= nx && nx < h && 0 <= ny && ny < w && arr[nx][ny] != '*') {
						if (!visited[nx][ny]) {
							visited[nx][ny] = true;
							q.add(new Pair(nx, ny));
							if (Character.isUpperCase(arr[nx][ny])) upperSize++;
						}
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private int r, c, ans = 0;
	private boolean[][] graph;
	private int[] direction = { -1, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		graph = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String string = br.readLine();
			for (int j = 0; j < c; j++) {
				if (string.charAt(j) == '.') {
					graph[i][j] = true;
				} else {
					graph[i][j] = false;
				}
			}
		}

		for (int i = 0; i < r; i++) {
			dfs(i, 0);
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private boolean dfs(int x, int y) {
		if (y == c - 1) {
			ans++;
			return true;
		}

		graph[x][y] = false;

		for (int d = 0; d < direction.length; d++) {
			int nx = x + direction[d];
			int ny = y + 1;
			if (0 <= nx && nx < r && 0 <= ny && ny < c && graph[nx][ny]) {
				if (dfs(nx, ny)) {
					return true;
				}
			}
		}

		return false;
	}
}

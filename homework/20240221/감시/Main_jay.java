import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private int n;
	private int m;
	private int[][] map;
	private List<Point> cctvList;
	private int ans = Integer.MAX_VALUE;
	private static final int[][] direction = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	class Point {
		int x, y, type;

		public Point(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		cctvList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					cctvList.add(new Point(i, j, map[i][j]));
			}
		}

		dfs(0);

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void dfs(int idx) {
		if (idx == cctvList.size()) {
			ans = Math.min(ans, getAns());
			return;
		}

		Point p = cctvList.get(idx);
		
		int[][] tmp = new int[n][m];
		for (int d = 0; d < direction.length; d++) {
			tmp = copy(map);
			switch (p.type) {
			case 1:
				go(p, d);
				dfs(idx + 1);
				break;
			case 2:
				go(p, d);
				go(p, (d + 2) % 4);
				dfs(idx + 1);
				break;
			case 3:
				go(p, d);
				go(p, (d + 1) % 4);
				dfs(idx + 1);
				break;
			case 4:
				go(p, d);
				go(p, (d + 1) % 4);
				go(p, (d + 3) % 4);
				dfs(idx + 1);
				break;
			case 5:
				for (int i = 0; i < direction.length; i++) {
					go(p, (d + i) % 4);
				}
				dfs(idx + 1);
				break;
			}
			map = copy(tmp);
			if (p.type == 2)
				d += 2;
			if (p.type == 5)
				break;
		}
	}

	private int[][] copy(int[][] map) {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			tmp[i] = map[i].clone();
		}
		return tmp;
	}

	private void go(Point p, int d) {
		int nx = p.x + direction[d][0];
		int ny = p.y + direction[d][1];

		if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 6)
			return;
		if (map[nx][ny] != 0)
			go(new Point(nx, ny, p.type), d);
		if (map[nx][ny] == 0) {
			map[nx][ny] = p.type;
			go(new Point(nx, ny, p.type), d);
		}
	}

	private int getAns() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}

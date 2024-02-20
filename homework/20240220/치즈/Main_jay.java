import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int c;
	private int r;
	private int[][] map;
	private Queue<Point> queue;
	private int ans;
	private ArrayList<Point> delList;
	private static final int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		
		
		int x = 0, y = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0, 0);
		int time = 0;
		while(delList.size() != 0) {
			time++;
			bfs(delList.get(0).x, delList.get(0).y);
		}
		
		sb.append(time).append("\n").append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void bfs(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[r][c];
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		delList = new ArrayList<>();
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < direction.length; d++) {
				int nx = p.x + direction[d][0];
				int ny = p.y + direction[d][1];
				
				if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
				
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx, ny));
				}
				
				if(map[nx][ny] == 1) {
					visited[nx][ny] = true;
					delList.add(new Point(nx, ny));
				}
			}
		}
		
		if(delList.size() == 0) return;
		
		for(Point p: delList) {
			map[p.x][p.y] = 0;
		}
		
		ans = delList.size();
	}
	
	class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}

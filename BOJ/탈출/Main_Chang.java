import java.util.*;

import javax.swing.GroupLayout.Alignment;

import java.io.*;

public class Main {
	static class Pair {
		short x, y;

		public Pair (int x, int y) {
			this.x=(short)x;
			this.y=(short)y;
		}

	}
	static final int[] dx = {-1,0,1,0};
	static final int[] dy = {0,1,0,-1};
	static final int INF = Integer.MAX_VALUE;
	
	static int t;
	static int r, c;
	static char[][] graph;
	static boolean[][] visited;
	static ArrayList<Pair> list = new ArrayList<Pair>();
	static Pair start;
	static Pair goal;
	static int anss= INF;
	public static void main(String[] args) throws IOException {
//		System.setIn(Main1.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));;
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new char[r][c];
		visited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int j = 0; j < c; j++) {
				graph[i][j] = line.charAt(j);
				if (graph[i][j]=='*') {
					list.add(new Pair(i, j));
					//visited[i][j] = 0;
				} else if (graph[i][j] == 'S') {
					start = new Pair(i,j);
					visited[i][j] = true;
				}
			}
		}
		

		
		alist.add(new Arrs(graph));
//		System.out.println(Arrays.deepToString(visited));
		dfs(start.x,start.y,1);
		System.out.println(anss == INF ? "KAKTUS": anss);
	
	}
	static class Arrs{
		char arr[][] = new char [r][c];

		public Arrs(char[][] arr) {
			for (int i = 0; i <r; i++) {
				this.arr[i] = arr[i].clone();
			}
		}
		
		
		
	} 
	static ArrayList<Arrs> alist = new ArrayList<>();
	private static void dfs(int x, int y, int t2) {
		if(alist.size() < t2+1) {
			spread();
		}
		for (int i = 0 ; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0<= nx &&nx < r && 0<=ny && ny < c) {
				if (alist.get(t2).arr[nx][ny] == '.' && !visited[nx][ny]) {
					visited[nx][ny] =true;
					dfs(nx, ny, t2+1);
					visited[nx][ny] =false;
				}
				else if (graph[nx][ny] == 'D'){
//					System.out.println(t2);
					anss = Math.min(t2, anss);
					return;
				}
			}
		}
		
		
	}

	private static void spread() {
		ArrayList<Pair> tmp = new ArrayList<Pair>();
		for (Pair p : list) {
			for (int i = 0 ; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0<= nx &&nx < r && 0<=ny && ny < c) {
					if (graph[nx][ny] =='.') {
						graph[nx][ny] = '*';
						tmp.add(new Pair(nx, ny));
					}
				}
			}
		}
		alist.add(new Arrs(graph));
		list = tmp;
		
	}
}
package edu.ssafy.im.BOJ.Gold.G1.No17472;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int n;
	private int m;
	private int[][] map;
	private static final int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private int depth;
	private ArrayList<Edge> edgeList;
	private int[][] weight;
	private int[] set;
	
	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) map[i][j] = -1;
				if(map[i][j] == 1) map[i][j] = -2;
			}
		}

		sb.append(sol());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		setBoundary(); // 섬 구분해주기 -> 섬의 넘버가 곧 vertex 가 된다.
		findPath(); // 길 찾기 -> 감시, 프로세서 연결하기 등등 -> 일직선으로 가서 vertex와 vertex 연결시키기 
		return kruskal(); // 크루스칼 
	}
	
	private void setBoundary() { // 섬 구분은 bfs 로 구현
		depth = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == -2) {
					bfs(i, j, depth++);
				}
			}
		}
	}

	private void bfs(int x, int y, int depth) { // 여기서 depth 는 섬의 개수고 곧 vertex의 수가 됨
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		map[x][y] = depth;
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < direction.length; d++) {
				int nx = p.x + direction[d][0];
				int ny = p.y + direction[d][1];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (map[nx][ny] != -2) continue;
				
				map[nx][ny] = depth;
				queue.offer(new Point(nx, ny));
			}
		}
	}
	
	private void findPath() { // 다리 연결
		edgeList = new ArrayList<Edge>();
		weight = new int[depth][depth];
		
		for (int i = 0; i < depth; i++) { // 가중치 배열 -> 인접행렬로 만들어짐
			for (int j = 0; j < depth; j++) {
				weight[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (map[x][y] != -1) {
					int from = map[x][y];
					for (int d = 0; d < direction.length; d++) {
						int nx = x + direction[d][0];
						int ny = y + direction[d][1];
						
						if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
						if (map[nx][ny] == map[x][y]) continue; // 주변이 같은 섬일 경우 pass, 즉 가장자리부분만 탐색
						
						dfs(nx, ny, d, 0, from);
					}
				}
			}
		}
		
		for (int i = 0; i < weight.length; i++) { // 인접행렬을 탐색하면서 간선리스트 생성해주기
			for (int j = 0; j < weight.length; j++) {
				if (weight[i][j] != Integer.MAX_VALUE) {
					edgeList.add(new Edge(i, j, weight[i][j]));
				}
			}
		}
	}
	
	private void dfs(int x, int y, int d, int w, int from) {
		if (map[x][y] != -1) { // 새로운 섬에 도달 했을 때
			if (w > 1) { // 문제 조건 : 다리 길이 2 이상
				int to = map[x][y];
				if(w < weight[from][to]) { // 기존 섬보다 다리 길이 짧은 경우 갱신
					weight[from][to] = w;
				}
			}
			return;
		}
		
		int nx = x + direction[d][0];
		int ny = y + direction[d][1];
		
		if (nx < 0 || ny < 0 || nx >= n || ny >= m) return;
		
		dfs(nx, ny, d, w+1, from);
	}
	
	private int kruskal() {
		makeSet();
		
		int weight = 0;
		int cnt = 0;
		Collections.sort(edgeList);
		for (Edge edge: edgeList) {
			if(!unionSet(edge.from, edge.to)) continue;
			weight += edge.weight;
			if(++cnt == depth-1) return weight; // 문제 조건 달성
		}
		
		return -1; // 문제 조건 달성 실패
	}
	
	private void makeSet() {
		set = new int[depth];
		for (int i = 0; i < depth; i++) {
			set[i] = i;
		}
	}
	
	private int findSet(int org) {
		if (set[org] == org) return org;
		else return set[org] = findSet(set[org]); 
	}
	
	private boolean unionSet(int a, int b) {
		int c = findSet(a);
		int d = findSet(b);
		
		if (c == d) return false;
		
		set[c] = d;
		return true;
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
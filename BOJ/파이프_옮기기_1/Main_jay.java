package edu.ssafy.im.BOJ.Gold.G5.No17070;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 가로, 세로, 대각선 상태일 때 전진할 수 있는 방향
	private final static int[][][] direction = { { { 0, 1 }, { 1, 1 } }, { { 1, 0 }, { 1, 1 } },
			{ { 0, 1 }, { 1, 0 }, { 1, 1 } } };
	
	// 대각선으로 가야할 때 검증할 부분
	private final static int[][] check = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int n;
	private int[][] map;
	private int ans = 0;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void dfs(int x, int y, int d) {
		if (x == n-1 && y == n-1) {
			ans++; // 목적지 도착 카운트
			return;
		}
		
		for (int dr = 0; dr < direction[d].length; dr++) {
			int nx = x + direction[d][dr][0];
			int ny = y + direction[d][dr][1];
			if (direction[d][dr][0] == 1 && direction[d][dr][1] == 1) { // 대각선일 경우
				boolean flag = true;
				for (int c = 0; c < check.length; c++) { // 대각선 한번 더 검증
					int cx = x + check[c][0];
					int cy = y + check[c][1];
					
					if (cx < 0 || cy < 0 || cx >= n || cy >= n || map[cx][cy] == 1) {
						flag = false;
						break;
					}
				}
				if(flag) { // 대각선 이동이 가능한 경우
					dfs(nx, ny, 2); // 대각선으로 이동
				}
			} else { // 가로 또는 세로 이동 시
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 1) continue;
				
				if (d == 2) dfs(nx, ny, dr); // 대각선의 경우 dr = 0 : 가로, dr = 1 : 세로
				else dfs(nx, ny, d); // d = 0 : 가로, d = 1 : 세로
			}
		}
	}
}

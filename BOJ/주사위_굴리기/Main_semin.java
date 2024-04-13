import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_semin {
	static int N, M, x, y, K;
	static int[][] maps;
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int[] dice;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		maps = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dice = new int[6];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			move(dir);
		}
		System.out.println(sb);
	}

	// 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
	private static void move(int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (nx >= N || ny >= M || nx < 0 || ny < 0) {
			return;
		}
		x = nx;
		y = ny;
		int[] copy = dice.clone();
		switch (dir) {
		case (1): // 동쪽
			copy[0] = dice[3];
			copy[2] = dice[0];
			copy[5] = dice[2];
			copy[3] = dice[5];
			break;
		case (2): // 서쪽
			copy[3] = dice[0];
			copy[0] = dice[2];
			copy[2] = dice[5];
			copy[5] = dice[3];
			break;
		case (3): // 북쪽
			copy[1] = dice[0];
			copy[0] = dice[4];
			copy[4] = dice[5];
			copy[5] = dice[1];
			break;
		case (4): // 남쪽
			copy[0] = dice[1];
			copy[4] = dice[0];
			copy[5] = dice[4];
			copy[1] = dice[5];
			break;
		}
		dice = copy;
		if (maps[x][y] != 0) {
			dice[5] = maps[x][y];
			maps[x][y] = 0;
		} else {
			maps[x][y] = dice[5];
		}
		sb.append(dice[0]).append("\n");
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
	static char[][] maps;
	static int R, C, N;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 몇 초 후
		maps = new char[R][C];

		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				maps[r][c] = line.charAt(c);
			}
		}

		if (N == 1) {
			printMaps();
			return;
		}

		if (N % 2 == 0) {
			sb = new StringBuilder();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					sb.append('O');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };
		for (int i = 0; i <= N % 4 + 1; i = i + 2) {
			char[][] temp = new char[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					temp[r][c] = 'O';
				}
			}
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (maps[r][c] == 'O') {
						temp[r][c] = '.';
						for (int k = 0; k < 4; k++) {
							int nx = c + dx[k];
							int ny = r + dy[k];
							if (nx >= C || ny >= R || nx < 0 || ny < 0) {
								continue;
							}
							temp[ny][nx] = '.';
						}
					}
				}
			}
			maps = temp;
		}
		printMaps();
	}

	private static void printMaps() {
		sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(maps[r][c]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

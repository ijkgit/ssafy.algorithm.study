import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		char[][] graph = new char[r][c];
		char[][] original = new char[r][c];

		for (int x = 0; x < r; x++) {
			String row = br.readLine();
			for (int y = 0; y < c; y++) {
				graph[x][y] = row.charAt(y);
				original[x][y] = row.charAt(y);
			}
		}

		if (!(n == 0 || n == 1)) {
			// 봄버맨 1 에서 추가한 것 두 줄
			n %= 4;
			n += 2;
			int[][] direction = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
			for (int i = 0; i <= n; i++) {
				if (i % 4 == 2 || i % 4 == 0) {
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							graph[x][y] = 'O';
						}
					}
				} else {
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							if (original[x][y] == 'O') {
								for (int d = 0; d < 5; d++) {
									int newX = x + direction[d][0];
									int newY = y + direction[d][1];
									if (0 <= newX && newX < r && 0 <= newY && newY < c) {
										graph[newX][newY] = '.';
									}
								}
							}
						}
					}
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							original[x][y] = graph[x][y];
						}
					}
				}
			}
		}

		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				sb.append(graph[x][y]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}

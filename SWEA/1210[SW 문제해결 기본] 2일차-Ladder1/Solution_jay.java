import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {
			br.readLine();
			int[][] graph = new int[100][100];
			for (int r = 0; r < graph.length; r++) {
				String row = br.readLine();
				StringTokenizer st = new StringTokenizer(row);
				for (int c = 0; c < graph.length; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int c = 0; c < graph.length; c++) {
				int y = 0;
				if (graph[0][c] == 1) {
					y = c;
					for (int r = 0; r < graph.length; r++) {
						boolean status = false;
						for (int i = y - 1; i >= 0; i--) {
							if (graph[r][i] == 1) {
								y = i;
								status = true;
							} else {
								break;
							}
						}
						if (status) continue;
						for (int i = y + 1; i < graph.length; i++) {
							if (graph[r][i] == 1) {
								y = i;
							} else {
								break;
							}
						}
					}
					if (graph[99][y] == 2)
						sb.append("#" + t + " " + c + "\n");
				}
			}
		}
		System.out.print(sb);
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private int[][] graph;
	private StringBuilder sb = new StringBuilder();

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		graph = new int[n][n];

		for (int i = 0; i < graph.length; i++) {
			String string = br.readLine();
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = string.charAt(j) - '0';
			}
		}

		recursive(n, 0, 0, n, n);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void recursive(int n, int x, int y, int X, int Y) {
		if (n == 1) {
			sb.append(graph[x][y]);
			return;
		}

		int sum = 0;
		for (int i = x; i < X; i++) {
			for (int j = y; j < Y; j++) {
				sum += graph[i][j];
			}
		}
		
		if (sum == n * n) {
			sb.append(1);
		} else if (sum == 0) {
			sb.append(0);
		} else {
			int m = n/2;
			sb.append("(");
			recursive(m, x, y, x + m, y + m);
			recursive(m, x, y + m, x + m, y + n);
			recursive(m, x + m, y, x + n, y + m);
			recursive(m, x + m, y + m, x + n, y + n);
			sb.append(")");
		}
	}
}

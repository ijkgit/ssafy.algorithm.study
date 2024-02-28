import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int T;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int[][] B = new int[M+1][N+1];
			for (int j = 0; j <= M; j++) {
				for (int k = 0, end = Math.min(j, N); k <= end; k++) {
					if (k == 0 || k == j) B[j][k] = 1;
					else B[j][k] = B[j-1][k-1] + B[j-1][k];
				}
			}
			
			sb.append(B[M][N]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}

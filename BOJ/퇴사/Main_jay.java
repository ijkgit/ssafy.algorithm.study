import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static Consult[] consultArray;
	private static int[] dp;

	static class Consult {
		int day, value;

		public Consult(int day, int value) {
			super();
			this.day = day;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		consultArray = new Consult[N]; 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			consultArray[i] = new Consult(day, value);
		}
		
		dp = new int[N+1];
		for (int i = 0; i < N; i++) {
			Consult c = consultArray[i];
			dp[i+1] = Math.max(dp[i], dp[i+1]); // 오늘의 최대 금액 갱신
			if (i+c.day >= N+1) continue;
			dp[i+c.day] = Math.max(dp[i+c.day], dp[i]+c.value); // 상담 기간 계산 후 최대 금액 갱신
		}
		
		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}

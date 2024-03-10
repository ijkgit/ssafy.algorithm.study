package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {
	//N+1일 째 되는 날 퇴사
	//남은 N일동안 최대한 많은 상담
	//Ti : 상담하는데 걸리는 기간
	//Pi 금액
	static int T[];
	static int P[];
	static int N;
	static int ans = Integer.MIN_VALUE;
	static int memo[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = new int[N];
		P = new int[N];
		memo = new int[N+5];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
			
		}
		
		recursive(0,0);//idx, pay, day
		System.out.println(ans);
	}
	private static void recursive(int idx, int pay) {
		
		if(memo[idx]>pay)return;
		
		if(idx >= N) {
			
			if(idx>N)return;
			ans = Math.max(pay, ans);
			return;
		}
		memo[idx]=pay;
		
		recursive(idx+T[idx], pay+P[idx]);
		
		recursive(idx+1, pay);
	}
}

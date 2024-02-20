import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
//dp 사용 재경 코드 참고했습니당
public class Solution {
	static ArrayList<String> strs = new ArrayList<>();
	static int ans;
	static int dp[] = new int[100000];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		int T = Integer.valueOf(tk.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			tk = new StringTokenizer(br.readLine());
			int K = Integer.valueOf(tk.nextToken());
			ans = -1;	
			System.out.println("#" + tc + " " + cuting(K,0));
		}


	}

	private static int cuting(int k , int dep) {
		if( k/10 == 0) {
			ans = Math.max(ans, dep);
			return 0;
		}
		
		
		String tmp = k + "";
		strs = new ArrayList<>();
		makeCombi(tmp,1 , tmp.length());
		ArrayList<String> 리스트 = strs;
		
		int max = 0;
		for (String 자를거 : 리스트) {
			String str[] = 자를거.split(",");
			int mul = 1;
			for (int i = 0; i < str.length; i++) {
				mul *= Integer.valueOf(str[i]);
			}
			if (dp[mul] == 0) {
				max = Math.max(max,cuting(mul , dep+1) +1) ;
			}else {
				max = Math.max(max, dp[mul]);
			}
		}
		dp[k] = max+1;
		return max;


		
	}

	private static void makeCombi(String tmp, int i , int fistSize) {
		if(tmp.length() == i) {
			if(fistSize != tmp.length()) {
				strs.add(tmp);
			}
			return;
		}
		makeCombi(tmp, i+1 , fistSize);
		tmp = tmp.substring(0,i) +"," +tmp.substring(i);
		makeCombi(tmp, i+2, fistSize);
		
	}



}

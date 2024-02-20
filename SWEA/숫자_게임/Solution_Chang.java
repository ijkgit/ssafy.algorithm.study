import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] gear;
	static int[] head;
	static boolean[] arr;
	static ArrayList<String> strs = new ArrayList<>();
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		int T = Integer.valueOf(tk.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			tk = new StringTokenizer(br.readLine());
			int K = Integer.valueOf(tk.nextToken());
			ans = -1;
			cuting(K,0);
			System.out.println("#" + tc + " " + ans);
		}
		
		

	}

	private static void cuting(int k , int dep) {
		if( k/10 == 0) {
			ans = Math.max(ans, dep);
			return;
		}
		
		
		String tmp = k + "";
		strs = new ArrayList<>();
		makeCombi(tmp,1 , tmp.length());
		ArrayList<String> 리스트 = strs;
		for (String 자를거 : 리스트) {
			String str[] = 자를거.split(",");
			int mul = 1;
			for (int i = 0; i < str.length; i++) {
				mul *= Integer.valueOf(str[i]);
			}
			cuting(mul , dep+1);
			
		}
		

		
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

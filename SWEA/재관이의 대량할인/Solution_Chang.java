import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	static int N ,M;
	static int arr[][];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int T = Integer.valueOf(tk.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			//입력 및 전처리
			tk = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			ArrayList<Integer> list = new ArrayList<>();
			tk = new StringTokenizer(br.readLine());
			int total = 0;
			for (int i = 0; i <N; i++) {
				int tmp = Integer.valueOf(tk.nextToken());
				total += tmp;
				list.add(tmp);
			}
			Collections.sort(list);
			int discount = 0;
			for (int i = N-3; i >= 0; i=i-3) {
				discount += list.get(i);
			}
			System.out.println("#"+ tc + " " + (total - discount));
		}
	}
}




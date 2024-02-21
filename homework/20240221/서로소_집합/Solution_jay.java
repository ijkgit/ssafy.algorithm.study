import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private int n;
	private int m;
	private int[] set;
	private int[] height;
	private StringBuilder sb = new StringBuilder();

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			makeSet();
			
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				doCommand(c, a, b);
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void makeSet() {
		set = new int[n];
		height = new int[n];
		for (int i = 0; i < set.length; i++) {
			set[i] = i;
		}
	}
	
	private void doCommand(int c, int a, int b) {
		switch (c) {
		case 0:
			unionSet(a, b);
			break;
		case 1: 
			if(findSet(a) == findSet(b)) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			break;
		}
	}
	
	private int findSet(int org) {
		if (set[org] == org) return org;
		else return set[org] = findSet(set[org]);
	}
	
	private void unionSet(int a, int b) {
		int c = findSet(a);
		int d = findSet(b);
		
		if(c != d) {
			if(height[c] > height[d]) {
				set[d] = c;
			} else {
				set[c] = d;
				if(height[c] == height[d]) height[d]++;
			}
		}
	}
}

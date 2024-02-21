import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private int n;
	private int[] arr;
	private List<ArrayList<Integer>> graph;
	private int[] sel;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());

			arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			graph = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					if (st.nextToken().charAt(0) == '1')
						graph.get(i).add(j);
				}
			}
			sb.append("#").append(t).append(" ").append(sol()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		int ans = 0;
		for (int i = 0; i < n; i++) {
			sel = new int[i];
			if (permutation(0, 0)) {
				ans = i;
				break;
			}
		}
		return ans;
	}
	
	private boolean check(int k, int v, int[] tmp) {
		if (k == sel.length) {
			int[] tmp2 = arr.clone();
			
			for(int i=0; i<sel.length; i++) {
				tmp2[sel[i]] = tmp[i];
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < graph.get(i).size(); j++) {
					if (tmp2[graph.get(i).get(j)] == tmp2[i]) {
						return false;
					}
				}
			}
			return true;
		}

		for (int i = 1; i <= 4; i++) {
			if ((v & (1 << i)) == 0) {
				tmp[k] = i;
				if(check(k + 1, v | 1 << i, tmp))
					return true;
			}
		}
		
		return false;
	}

	private boolean permutation(int k, int v) {
		if (k == sel.length) {
			if (check(0, 0, new int[sel.length])) {
				return true;	
			}
			return false;
		}

		for (int i = 0; i < n; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = i;
				if(permutation(k + 1, v | 1 << i))
					return true;
			}
		}

		return false;
	}
}

import java.util.*;
import java.io.*;

public class Main_sangphil {
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] inOrder;
	static int n, m;
	static StringBuilder sb = new StringBuilder();

	public static void main (String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n+1; i++) graph.add(new ArrayList<Integer>());
		inOrder = new int[n+1];

		int a, b; 
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			inOrder[b]++;
		}

		topologySort();

		System.out.println(sb);
	}

	static void topologySort() {
		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 1; i < n+1; i++) {
			if (inOrder[i] == 0) q.add(i);
		}

		while (!q.isEmpty()) {
			int p = q.poll();
			sb.append(p + " ");
			for (int x : graph.get(p)) {
				inOrder[x]--;
				if (inOrder[x] == 0) q.add(x);
			}
		}
	}
}
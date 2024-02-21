import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private int n;
	private int m;
	private List<ArrayList<Integer>> graph;
	private StringBuilder sb = new StringBuilder();

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		
		for (int i = 0; i < graph.size(); i++) {
			boolean[] v = new boolean[n];
			v[i] = true;
			dfs(i, v, 0);
			if (sb.length() != 0) break;
		}
		if (sb.length() == 0) sb.append(0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void dfs(int k, boolean[] v, int cnt) {
		if(sb.length() != 0) {
			return;
		}
		
		if(cnt == 4) {
			sb.append(1);
			return;
		}

		for (int j = 0; j < graph.get(k).size(); j++) {
			int cur = graph.get(k).get(j);
			if(!v[cur]) {
				v[cur] = true;
				dfs(cur, v, cnt+1);
				v[cur] = false;
			}
		}
	}
}

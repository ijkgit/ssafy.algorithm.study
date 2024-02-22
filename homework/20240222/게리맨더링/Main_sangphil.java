import java.util.*;
import java.util.Map.Entry;
import java.io.*;

/*
 * 		[BOJ 17471] 게리맨더링
 * 			1. 멱급수를 이용하여, 선거구역을 쪼개기
 * 			2. 본인이 포함되어 있는 Sub Set 끼리만 연결되는 최소신장트리 형성
 * 			3. 단 2개의 구역으로 나뉘어질 때, 인구 차이를 추출
 * 			4. 인구 차이의 최소값을 브루트포스 서치
 */
public class Main_sangphil {
	static ArrayList<ArrayList<Integer>> adjList;
	static int n;
	static int[] weight;
	static int[] p;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// System.setIn(Main2.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		adjList = new ArrayList<ArrayList<Integer>>();
		n = Integer.parseInt(br.readLine());
		weight = new int[n+1];
		p = new int[n+1];

		for (int i = 0; i < n+1; i++) {
			p[i] = i;
			adjList.add(new ArrayList<Integer>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) weight[i] = Integer.parseInt(st.nextToken());
		
		int m;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) adjList.get(i).add(Integer.parseInt(st.nextToken()));
		}
		
		powerSet(0, 0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void powerSet(int depth, int k, int bit) {
		if (depth == n) {
			for (int i = 0; i < n+1; i++) p[i] = i;
			for (int i = 1; i <= n; i++) {
				if ((bit & (1<<i)) != 0) {
					for (int x : adjList.get(i)) {
						if ((bit & (1<<x)) != 0) {
							union(i, x);
						}
					}
				} else {
					for (int x : adjList.get(i)) {
						if ((bit & (1<<x)) == 0) {
							union(i, x);
						}
					}
				}
			}
			Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
			for (int i = 1; i <= n; i++) {
				int root = find(i);
				if (map.containsKey(root)) {
					map.put(root, map.get(root) + weight[i]);
				} else {
					map.put(root, weight[i]);
				}
			}
			if (map.size() == 2) {
				int[] arr = new int[2];
				int o = 0;
				for (Entry<Integer, Integer> x : map.entrySet()) {
					arr[o++] = x.getValue();
				}
				ans = Math.min(ans, Math.abs(arr[0] - arr[1]));
			}
			return;
		}
		if (k == n) return;

		powerSet(depth+1, k, bit);
		powerSet(depth+1, k+1, (bit | (1<<depth)));
		
	}
	
	static void union (int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) p[b] = a;
	}
	
	static int find (int a) {
		if (p[a] == a) return a;
		return p[a] = find(p[a]);
	}
}
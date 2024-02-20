import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int n;
	private int m;
	private ArrayList<ArrayList<Integer>> arrayList;
	private int[] indegree;
	private StringBuilder sb = new StringBuilder();
	
	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arrayList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
			arrayList.add(new ArrayList<>());
		}
        
        indegree = new int[n+1];
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int v1 = Integer.parseInt(st.nextToken());
        	int v2 = Integer.parseInt(st.nextToken());
        	
        	arrayList.get(v1).add(v2);
        	indegree[v2]++;
		}
        
        topologicalSort();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
	}

	private void topologicalSort() {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");
			for(int idx: arrayList.get(current)) {
				indegree[idx]--;
				
				if(indegree[idx] == 0) {
					queue.offer(idx);
				}
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private Queue<Integer> pq;
	int l;

	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		sb.append(sol());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		while (!pq.isEmpty()) {
			if (l >= pq.poll())
				l++;
			else
				break;
		}

		return l;
	}
}

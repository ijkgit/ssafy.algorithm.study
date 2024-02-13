import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	Queue<Time> pq;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.end == o2.end) {
				return o1.start - o2.start;
			}
			return o1.end - o2.end;
			});
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Time(start, end));
		}
		
		sb.append(sol());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		int ans = 1;
		int end = pq.poll().end;
		while(!pq.isEmpty()) {
			if (end <= pq.peek().start) {
				ans++;
				end = pq.poll().end;
			} else {
				pq.poll();
			}
		}
		return ans;
	}

	class Time {
		int start, end;

		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
}

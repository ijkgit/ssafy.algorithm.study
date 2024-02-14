package edu.ssafy.im.JUNGOL.Gold.G5.No2097;

import java.io.*;
import java.util.*;

public class Main {
	private int n, m;
	ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();

	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			nodes.add(new ArrayList<Node>());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				nodes.get(i).add(new Node(j, Integer.parseInt(st.nextToken())));
			}
		}

		Node ans = dijkstra();
		sb.append(ans.cost).append("\n");
		sb.append(ans.route);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private Node dijkstra() {
		// 거리 초기화
		int[] dist = new int[n];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		dist[0] = 0;
		pq.offer(new Node(0, dist[0], "1"));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			// 목적지에 도달한 경우
			if (current.idx == m - 1) {
				return current;
			}

			// 최단 거리가 아닌 경우
			if (dist[current.idx] < current.cost)
				continue;

			// 현재 정점에서 갈 수 있는 다음 정점들을 탐색
			for (int i = 0; i < nodes.get(current.idx).size(); i++) {
				Node next = nodes.get(current.idx).get(i);

				// 다음 정점까지의 거리 비용이 기존 비용보다 낮은 경우
				// 새로운 최소 거리 갱신 및 경로 저장
				if (dist[next.idx] > current.cost + next.cost) {
					dist[next.idx] = current.cost + next.cost;
					pq.offer(new Node(next.idx, dist[next.idx], current.route + " " + Integer.toString(next.idx+1)));
				}
			}
		}

		return null;
	}

	class Node {
		int idx, cost;
		String route;

		public Node(int idx, int cost, String route) {
			this.idx = idx;
			this.cost = cost;
			this.route = route;
		}

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + ", route=" + route + "]";
		}

	}
}

package edu.ssafy.im.BOJ.Gold.G3.No2252;

import java.io.*;
import java.util.*;

public class Main {
    int n, m;
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] indegree = new int[n + 1];
        List<List<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            arrayList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            arrayList.get(v1).add(v2);
            indegree[v2]++;
        }

        Queue<Integer> ans = topologicalSort(indegree, arrayList);

        for (int a : ans) {
            sb.append(a + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private Queue<Integer> topologicalSort(int[] indegree, List<List<Integer>> array) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            result.offer(idx);

            for (int i : array.get(idx)) {
                indegree[i]--;

                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        return result;
    }
}

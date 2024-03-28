package edu.ssafy.im.BOJ.Gold.G5.No9205;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static ArrayList<Vertex> vertexList;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;
    private static boolean flag;

    static class Vertex {
        int x, y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int T = 0; T < TC; T++) {
            N = Integer.parseInt(br.readLine());

            vertexList = new ArrayList<>();
            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                vertexList.add(new Vertex(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            sb.append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String sol() {
        graph = new ArrayList<>();
        for (int i=0; i<vertexList.size(); i++)
            graph.add(new ArrayList<>());

        makeEdge();

        visited = new boolean[N+2];
        flag = false;
        // 0 : 집 위치 : 탐색 시작
        dfs(0);

        if (flag) return "happy";
        return "sad";
    }

    private static void dfs(int k) {
        visited[k] = true;
        if (flag) return;
        // N+1 : 페스티벌 위치 : 도착 시 종료
        if (k == N+1) {
            flag = true;
            return;
        }

        // 연결 된 정점 간 탐색
        for (int i = 0; i < graph.get(k).size(); i++) {
            int next = graph.get(k).get(i);
            if (flag) return;
            if (!visited[next]) dfs(next);
        }
    }

    private static void makeEdge() {
        for (int i = 0; i < vertexList.size(); i++) {
            Vertex current = vertexList.get(i);
            for (int j = i + 1; j < vertexList.size(); j++) {
                Vertex next = vertexList.get(j);
                // 조건 만족 시 양방향 간선 생성
                if (Math.abs(current.x - next.x) + Math.abs(current.y - next.y) <= 1000) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
    }
}

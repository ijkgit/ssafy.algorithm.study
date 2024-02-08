package edu.ssafy.im.SWEA.D0.No1767;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private int[][] graph;
    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    List<Point> searchList;
    int n, ans, line, able, ableMax;
    boolean[] flag;

    public static void main(String[] args) throws NumberFormatException, IOException {
        new Solution().io();
    }

    private void io() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            graph = new int[n][n];
            searchList = new ArrayList<>();
            for (int i = 0; i < graph.length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < graph[0].length; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == 1) {
                        if ((i != 0) && (j != 0) && (i != (graph.length - 1)) && (j != (graph[0].length - 1))) {
                            searchList.add(new Point(i, j));
                        }
                    }
                }
            }
            ans = Integer.MAX_VALUE;
            flag = new boolean[searchList.size()];
            able = 0;
            ableMax = 0;
            line = 0;
            dfs(0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /*
     * 탐색하면서, 끝에 도달할 경우 true 처리 true 처리 이후 다른 정점 탐색을 위해 다시 false 처리
     */

    private void dfs(int depth) {
        if (depth == searchList.size()) {
            if (able > ableMax) {
                ableMax = able;
                ans = line;
            } else if (able == ableMax) {
                ans = Math.min(ans, line);
            }
            return;
        }

        for (int i = 0; i < searchList.size(); i++) {
            if(searchList.size() - i + able < ableMax) return;
            if (!flag[i]) {
                int x = searchList.get(i).x;
                int y = searchList.get(i).y;
                for (int d = 0; d < direction.length; d++) {
                    if(!checkBranch(x, y, d)) continue;
                    int nx = x + direction[d][0];
                    int ny = y + direction[d][1];
                    if (checkStatus(nx, ny) && checkGo(nx, ny, d)) {
                        line += go(nx, ny, d);
                        flag[i] = true;
                        dfs(depth + 1);
                        flag[i] = false;
                        line -= back(nx, ny, d);
                    } else {
                        flag[i] = true;
                        dfs(depth + 1);
                        flag[i] = false;
                    }
                }
            }
        }
    }
    private boolean checkBranch(int x, int y, int d) {
        switch(d) {
            case 0:
                if(graph[x][y + direction[d][1] * (graph.length - y - 1)] == 1) return false;
                if(graph[x][y + direction[d][1] * (graph.length - y - 1)] == -1) return false;
                else return true;
            case 1:
                if(graph[x][y + direction[d][1] * y] == 1) return false;
                if(graph[x][y + direction[d][1] * y] == -1) return false;
                else return true;
            case 2:
                if(graph[x + direction[d][0] * (graph.length - x - 1)][y] == 1) return false;
                if(graph[x + direction[d][0] * (graph.length - x - 1)][y] == -1) return false;
                else return true;
            case 3:
                if(graph[x + direction[d][0] * x][y] == 1) return false;
                if(graph[x + direction[d][0] * x][y] == -1) return false;
                else return true;
            default:
                return false;
        }
    }

    private int back(int x, int y, int d) {
        graph[x][y] = 0;
        int cnt = 1;
        while (x != 0 && y != 0 && x != graph.length - 1 && y != graph.length - 1) {
            x += direction[d][0];
            y += direction[d][1];
            graph[x][y] = 0;
            cnt++;
        }
        able--;
        return cnt;
    }

    private int go(int x, int y, int d) {
        graph[x][y] = -1;
        int cnt = 1;
        while(x != 0 && y != 0 && x != graph.length - 1 && y != graph.length - 1) {
            x += direction[d][0];
            y += direction[d][1];
            graph[x][y] = -1;
            cnt++;
        }

        able++;
        return cnt;
    }

    private boolean checkGo(int x, int y, int d) {
        if (x == 0 || y == 0 || x == graph.length - 1 || y == graph.length - 1) {
            return true;
        }

        int nx = x + direction[d][0];
        int ny = y + direction[d][1];
        if (checkStatus(nx, ny))
            return checkGo(nx, ny, d);

        return false;
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && graph[x][y] == 0;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

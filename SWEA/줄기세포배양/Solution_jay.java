package edu.ssafy.im.SWEA.D0.No5653;

import java.io.*;
import java.util.*;

public class Solution {
    private int n, m, k;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;
    private ArrayList<Point> cellList;
	private int SIZE;
    private static final int MINVALUE = -99999;
    
    class Point implements Comparable<Point> {
        int x, y, t, v;

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.v = t;
        }
        
        /*
         * v는 생명력 수치, t는 시간
         * 시간을 기준으로 내림차순 정렬 수행
         * 만약 동시에 세포가 활성화 됐을 경우, 생명력 수치를 기준으로 내림차순 정렬 수행
         */
        @Override
        public int compareTo(Point o) {
            if (o.t == this.t) {
                return o.v - this.v;
            }
            return o.t - this.t;
        }
    }

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            SIZE = Math.max(n, m) + 2 * k; // 배열의 사이즈 동적할당
            cellList = new ArrayList<>();
            visited = new boolean[SIZE][SIZE];
            for (int i = (SIZE - n) / 2; i < (SIZE + n) /2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = (SIZE - m) / 2; j < (SIZE+m) / 2; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if(v != 0) {
                    	visited[i][j] = true;
                        cellList.add(new Point(i, j, v));
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol() {
        for (int now = 1; now <= k; now++) {
            Collections.sort(cellList); // 내림차순 정렬 수행
            ArrayList<Point> delList = new ArrayList<>();
            for (int i = 0; i < cellList.size(); i++) {
            	// MINVALUE 는 비활성화된 세포, 내림차순 정렬 시 첫 발견된 비활성화세포 이후는 탐색 필요 X
            	if (cellList.get(i).t == MINVALUE) break; 
            	
            	// 활성화를 위해 시간을 줄여나감
            	cellList.get(i).t--;
            	
            	// 활성화 조건 : 시간이 -1이 됐을 경우
            	// 활성화를 위해 탐색 리스트에 할당 및 방문 처리
            	// 내림차순 정렬을 수행 시에, 만약 동시에 활성화 조건을 달성했을 경우
            	// 생명력 수치가 높은 세포를 먼저 방문하게 되며 탐색 리스트에 들어감
            	// 따라서, 동시에 활성화 됐을 때 겹치는 영역은 생명력 수치가 높은 세포가 차지하게 됨
            	if(cellList.get(i).t == -1) {
                    visited[cellList.get(i).x][cellList.get(i).y] = true;
                    delList.add(new Point(cellList.get(i).x, cellList.get(i).y, cellList.get(i).v));
                }
            	
            	// 비활성화 조건 : 시간이 -생명력 수치가 됐을 경우
            	if (cellList.get(i).t == -cellList.get(i).v) {
                	cellList.get(i).t = MINVALUE;
                }
            }
            for (Point p : delList) {
                for (int d = 0; d < direction.length; d++) {
                    int nx = p.x + direction[d][0];
                    int ny = p.y + direction[d][1];

                    if(visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true; // 생명력 수치가 높은 세포가 먼저 탐색을 수행하고 먼저 번식해놓음 
                    cellList.add(new Point(nx, ny, p.v)); 
                }
            }
        }

        int ans = 0;
        Collections.sort(cellList);
        for (Point p: cellList) {
            if(p.t == MINVALUE) break;
            ans++;
        }
        return ans;
    }
}

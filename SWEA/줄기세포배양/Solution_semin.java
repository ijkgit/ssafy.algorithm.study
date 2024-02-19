import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N, M, K, res;
    static Map<Point, Cell> cells = new HashMap<>();
    static Map<Point, Cell> spreadMap = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Cell {
        int origin, current;
        boolean died;

        Cell(int origin) { //origin = K = 생명력
            this.origin = origin;
            this.current = origin;
            this.died = false;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "origin=" + origin +
                    ", current=" + current +
                    ", died=" + died +
                    '}';
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cells.clear();
            res = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num != 0) {
                        cells.put(new Point(j, i), new Cell(num));
                    }
                }
            }

            for (int hour = 1; hour <= K; hour++) {
                spreadMap.clear();
                for (Map.Entry<Point, Cell> cell : cells.entrySet()) {
                    // 0이 되면 활성화 상태 -> 4방 탐색해서 이미 세포가 존재하는 위치가 아니면 확산 가능, spreadMap 에 넣어주기
                    if (cell.getValue().current == 0) {
                        for (int k = 0; k < 4; k++) {
                            int nx = cell.getKey().x + dx[k];
                            int ny = cell.getKey().y + dy[k];
                            Point np = new Point(nx, ny);
                            if (!cells.containsKey(np)) {
                                Cell newCell = new Cell(cell.getValue().origin);
                                if (!spreadMap.containsKey(np) || spreadMap.get(np).origin < newCell.origin) {
                                    spreadMap.put(np, newCell);
                                }
                            }
                        }
                    }
                    cell.getValue().current--;
                    if (Math.abs(cell.getValue().current) >= cell.getValue().origin) { // 생명력만큼 활성화된 상태로 보냈음
                        cell.getValue().died = true;
                    }

                }
                //한 턴이 끝나면 add All, 중복된 cell 있으면 파워가 높은 애들만 남겨서 current 에 넣기
                cells.putAll(spreadMap);
            }
            for (Cell c : cells.values()) {
                if (!c.died) {
                    res++;
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
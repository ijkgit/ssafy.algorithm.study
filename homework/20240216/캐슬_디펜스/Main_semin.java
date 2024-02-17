import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_semin {
    static int N, M, D, selected[], res;
    static List<Point> enemies = new ArrayList<>();
    static boolean[] visited;

    static class Point {
        int x, y, idx;

        Point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        selected = new int[3];
        res = 0;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    enemies.add(new Point(j, i, idx++));
                }
            }
        }
        combination(0, 0);
        System.out.println(res);
    }

    public static void combination(int idx, int k) {
        if (k == 3) {
            shoot();
            return;
        }
        if (idx == M) {
            return;
        }
        selected[k] = idx;
        combination(idx + 1, k + 1);
        combination(idx + 1, k);
    }

    /**
     * 궁수 3명 for문
     * i번째 궁수와 적까지의 거리 구하기, D이하인지 보기, 그 중에서 가장 가까운 거리를 가지고 있는 애 가져오기-> set에 넣기
     * 궁수 3명의 공격이 끝났을 때, 한 턴이 끝난 것이므로 set에 들어있는 애들만큼 제거한 적 수 ++
     * 적의 y좌표는 +1되고, N 이상이 되면 visited 처리하기
     */
    private static void shoot() {
        int step = 0; //적이 내려온 횟수
        int temp = 0; //이번 조합의 적 처치 횟수
        visited = new boolean[enemies.size()]; //남은 적 관리
        while (step < N) {
            Set<Point> set = new HashSet<>();
            for (int e = 0; e < 3; e++) {
                int ax = selected[e]; //궁수 좌표
                int ay = N;
                int[] died = new int[]{Integer.MAX_VALUE, M, -1}; // 0:거리, 1:c, 2:Point.idx
                for (int i = 0; i < enemies.size(); i++) {
                    if (visited[i]) {
                        continue;
                    }
                    int ex = enemies.get(i).x; //적 좌표
                    int ey = enemies.get(i).y + step;
                    int distance = Math.abs(ax - ex) + Math.abs(ay - ey);
                    if (distance <= D) {
                        if (died[0] == distance) {
                            if (died[1] > ex) {
                                died = new int[]{distance, ex, i};
                            }
                        } else if (died[0] > distance) {
                            died = new int[]{distance, ex, i};
                        }
                    }
                }
                if (died[2] != -1) {
                    set.add(enemies.get(died[2]));
                }
            }
            temp += set.size(); //적 처치 횟수 업데이트
            for (Point e : set) { //처치했으면 더이상 볼 필요 x
                visited[e.idx] = true;
            }

            step++;
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).y + step >= N) { //다음 스탭에 좌표 벗어나면 볼 필요 x
                    visited[i] = true;
                }
            }
        }
        res = Math.max(res, temp);
    }
}

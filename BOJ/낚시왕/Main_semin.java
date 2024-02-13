import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_semin {
    static int R, C, M;
    static Shark[] sharks;
    static boolean[] visited;
    static int[] dc = {0, 0, 0, 1, -1};
    static int[] dr = {0, -1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[M];
        visited = new boolean[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sharks[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), i);
        }

        int sum = 0;
        for (int angler = 1; angler <= C; angler++) {
            List<Shark> current = new ArrayList<>();
            for (Shark s : sharks) {
                if (s.c == angler && !visited[s.idx]) { // 낚시인과 열이 같고, 잡히거나 먹히지 않은 상어
                    current.add(s);
                }
            }
            if (!current.isEmpty()) {
                Collections.sort(current); // 중에 땅과 가까운 것으로 정렬
                sum += current.get(0).z; // 낚시
                visited[current.get(0).idx] = true; // 잡힌 상어는 더이상 연산 필요 x
            }
            moveShark(); //상어 이동
        }

        System.out.println(sum);
    }

    private static void moveShark() {
        // 상어가 이동한다.
        for (int i = 0; i < sharks.length; i++) {
            if (sharks[i].d == 1 || sharks[i].d == 2) { // 세로로 움직이는 상어라면
                for (int k = 0; k < sharks[i].s; k++) {
                    if (sharks[i].d == 1) { // 위로 이동
                        if (sharks[i].r == 1) {
                            sharks[i].d = 2; // 방향 전환
                        }
                    } else { // 아래로 이동
                        if (sharks[i].r == R) {
                            sharks[i].d = 1; // 방향 전환
                        }
                    }
                    sharks[i].r += dr[sharks[i].d];
                }
            }
            if (sharks[i].d == 3 || sharks[i].d == 4) { // 가로로 움직이는 상어라면
                for (int k = 0; k < sharks[i].s; k++) {
                    if (sharks[i].d == 3) { // 오른쪽으로 이동
                        if (sharks[i].c == C) {
                            sharks[i].d = 4; // 방향 전환
                        }
                    } else { // 왼쪽으로 이동
                        if (sharks[i].c == 1) {
                            sharks[i].d = 3; // 방향 전환
                        }
                    }
                    sharks[i].c += dc[sharks[i].d];
                }
            }
        }

        // r,c가 같은게 있으면 크기가 큰 상어만 남기기(먹힌 상어는 방문 처리해서 더이상 탐색 못하게) <------ 이 부분을 깔끔하게 못했어요 ㅠ
        int[][] maps = new int[R + 1][C + 1]; //중복 좌표가 있는지 체크하기 위한 맵
        for (Shark currentShark : sharks) {
            if (visited[currentShark.idx]) { //낚시 되었거나 먹힌 상어는 탐색(연산)하지 x
                continue;
            }
            if (maps[currentShark.r][currentShark.c] == 0) { //해당 좌표에 이미 존재하는 상어가 없으면
                maps[currentShark.r][currentShark.c] = currentShark.idx + 1; //visited에 1 더해서 표기해두기(0과 구분위해)
                continue;
            }
            //지금 들어가려는 좌표에 이미 상어가 있다
            int prevSharkIdx = maps[currentShark.r][currentShark.c] - 1; //기존 상어 인덱스 찾기, 인덱스는 0부터 시작
            if (sharks[prevSharkIdx].z > currentShark.z) {
                visited[currentShark.idx] = true; //기존에 있던 상어 크기가 더 크면 현재 상어가 먹힘
            } else {
                visited[prevSharkIdx] = true; //현재 상어가 더 크면 기존 상어가 먹힘
                maps[currentShark.r][currentShark.c] = currentShark.idx + 1; //교체됨
            }
        }
    }

    public static class Shark implements Comparable<Shark> {
        int r, c, s, d, z, idx;

        public Shark(int r, int c, int s, int d, int z, int idx) {
            super();
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d; // 방향
            this.z = z;
            this.idx = idx;
        }

        @Override
        public int compareTo(Shark o) { // 땅이랑 가까운 것으로 정렬
            return this.r - o.r;
        }

    }
}
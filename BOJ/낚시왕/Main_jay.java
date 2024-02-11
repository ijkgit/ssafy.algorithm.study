import java.io.*;
import java.util.*;

public class Main {
    int R, C;
    List<Shark> sharkList;
    int ans = 0;
    int[][] direction = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int visited[][];

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        sharkList = new ArrayList<>();
        setArray();
        int idx = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharkList.add(new Shark(r, c, s, d, z));
            visited[r][c] = idx++; // 방문 배열에는 상어리스트의 인덱스 값이 들어간다.
        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        for (int i = 0; i < C; i++) { // 오른쪽 한 칸씩 이동
            catchShark(i); // 가장 가까운 상어 잡기

            setArray(); // 방문 배열 초기화

            for (Shark s : sharkList) { // 상어 움직이기
                s.move();
            }

            checkShark(); // 겹친 열 체크하기
        }
    }

    private void setArray() {
        visited = new int[R][C]; // 방문 배열 초기화
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                visited[r][c] = -1;
            }
        }
    }

    private void catchShark(int c) {
        for (int r = 0; r < R; r++) {
            if (visited[r][c] != -1) { // 윗 행부터 낚시꾼이 서있는 열의 방문배열을 확인하면서 값이 -1이 아닌 경우 잡아야할 상어
                ans += sharkList.get(visited[r][c]).z;
                sharkList.get(visited[r][c]).z = -1; // 삭제 처리를 무게 = -1로 처리함
                return;
            }
        }
    }

    private void checkShark() {
        for (int i = 0; i < sharkList.size(); i++) {
            int r = sharkList.get(i).r;
            int c = sharkList.get(i).c;
            int z = sharkList.get(i).z;
            int idx = visited[r][c];

            if (z == -1) continue; // 무게 -1인 경우 삭제된 상어

            if (idx == -1) {
                visited[r][c] = i; // 방문배열에 인덱스 남기기
            } else { // 무게 확인해서 밀어내거나, 밀리거나
                if (z > sharkList.get(idx).z) {
                    sharkList.get(idx).z = -1;
                    visited[r][c] = i;
                } else {
                    sharkList.get(i).z = -1;
                }
            }
        }
    }

    class Shark {
        int r, c, s, d, z; // 위치, 속력, 이동 방향, 크기

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.d = d;
            if (d == 1 || d == 2) { // 사이클 수 줄이기
                s %= 2 * (R - 1);
            } else if (d == 3 || d == 4) {
                s %= 2 * (C - 1);
            }
            this.s = s;

            if (r == 0 && d == 1) { // 방향 바로잡기
                this.d = 2;
            } else if (r == R - 1 && d == 2) {
                this.d = 1;
            } else if (c == 0 && d == 4) {
                this.d = 3;
            } else if (c == C - 1 && d == 3) {
                this.d = 4;
            }
        }

        public void move() {
            if (this.z == -1) { // 무게가 1일 경우 삭제된 상어
                return;
            }
            if (this.d == 1 || this.d == 2) {
                int nr = this.r + direction[this.d][0] * this.s;
                if (0 <= nr && nr <= R - 1) { // 방향 변경 없이 한번에 이동 가능한 경우 한번에 처리
                    this.r = nr;
                    if (this.r == 0) {
                        this.d = 2;
                    } else if (this.r == R - 1) {
                        this.d = 1;
                    }
                } else {
                    for (int i = 0; i < this.s; i++) { // 방향 변경 필요한 경우 일일히 이동
                        this.r += direction[this.d][0];
                        if (this.r == 0) {
                            this.d = 2;
                        } else if (this.r == R - 1) {
                            this.d = 1;
                        }
                    }
                }
            } else if (this.d == 3 || this.d == 4) {
                int nc = this.c + direction[this.d][1] * this.s;
                if (0 <= nc && nc <= C - 1) {
                    this.c = nc;
                    if (this.c == 0) {
                        this.d = 3;
                    } else if (this.c == C - 1) {
                        this.d = 4;
                    }
                } else {
                    for (int i = 0; i < this.s; i++) {
                        this.c += direction[this.d][1];
                        if (this.c == 0) {
                            this.d = 3;
                        } else if (this.c == C - 1) {
                            this.d = 4;
                        }
                    }
                }
            }
        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
    // 격자의 크기 N, 시뮬레이션 년 수 M, 제초제 확산 범위 K, 제초제 지속 시간 C
    private static int N, M, K, C;
    private static int[][] map, copyMap, visited; // 각각 현재 나무 상태, 복제 나무 상태, 제초제 방문 상태를 저장
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 상하좌우 이동을 위한 방향 배열
    private static final int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}; // 대각선 이동을 위한 방향 배열
    private static int ans = 0; // 전체 제초제로 박멸된 나무의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력을 통해 파라미터 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 격자 정보 입력
        map = new int[N][N];
        copyMap = new int[N][N];
        visited = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 제초제 지속시간 갱신 및 처리
    private static void updateVisited() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[x][y] > 0) visited[x][y]--; // 제초제 카운트 다운
                else if (map[x][y] == -2) map[x][y] = 0; // 제초제 만료 시 칸 초기화
            }
        }
    }

    // 주어진 년도만큼 성장, 번식, 제초제 처리 반복
    private static int sol() {
        for (int m = 0; m < M; m++) {
            updateVisited();
            grow();
            breed();
            kill();
        }
        return ans;
    }

    // 최대 박멸 지점 찾고 제초제 확산
    private static void kill() {
        Tree tree = findMax();
        if (tree.x != -1) spread(tree);
    }

    // 제초제 확산 처리
    private static void spread(Tree tree) {
        map[tree.x][tree.y] = -2;
        visited[tree.x][tree.y] = C;
        for (int[] d : diagonal) {
            for (int k = 1; k <= K; k++) {
                int nx = tree.x + d[0] * k;
                int ny = tree.y + d[1] * k;

                if (checkRange(nx, ny)) break;
                if (map[nx][ny] == -1) break;
                int tmp = map[nx][ny];
                map[nx][ny] = -2;
                visited[nx][ny] = C;
                if (tmp == 0 || tmp == -2) break;
            }
        }
    }

    // 제초제를 뿌릴 위치 선정 (최대 박멸 지점 찾기)
    private static Tree findMax() {
        int rs = 0, rx = -1, ry = -1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue; // 나무가 없거나 벽인 경우 무시
                int sum = map[x][y];
                for (int[] d : diagonal) {
                    for (int k = 1; k <= K; k++) {
                        int nx = x + d[0] * k;
                        int ny = y + d[1] * k;

                        if (checkRange(nx, ny) || map[nx][ny] <= 0) break; // 범위 밖이거나 나무가 없는 경우 중단
                        sum += map[nx][ny];
                    }
                }
                // 조건 만족 시 최대값 갱신
                if (sum < rs || (sum == rs && x > rx) || (sum == rs && x == rx && y > ry)) continue;
                rs = sum;
                rx = x;
                ry = y;
            }
        }

        ans += rs; // 박멸된 나무 수 추가
        return new Tree(rx, ry); // 박멸 시작 위치 반환
    }

    // 나무 번식 처리
    private static void breed() {
        copy(); // 현재 상태 복제

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue; // 나무가 없거나 벽인 경우 무시

                int count = 0;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue; // 범위 밖이면 무시
                    if (map[nx][ny] == 0) count++; // 번식 가능한 빈 칸 세기
                }

                if (count == 0) continue; // 번식할 곳이 없으면 넘어감
                int newTree = map[x][y] / count; // 나무 그루 수 나누기

                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue; // 범위 밖이면 무시
                    if (map[nx][ny] == 0) { // 번식 가능한 경우
                        copyMap[nx][ny] += newTree; // 복제된 맵에 추가
                    }
                }
            }
        }

        paste(); // 복제된 상태를 원본에 반영
    }

    // 복제된 상태를 원본에 반영
    private static void paste() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                map[x][y] = copyMap[x][y];
            }
        }
    }

    // 원본 상태를 복제
    private static void copy() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copyMap[x][y] = map[x][y];
            }
        }
    }

    // 나무 성장 처리
    private static void grow() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue; // 나무가 없거나 벽인 경우 무시
                int count = 0;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue; // 범위 밖이면 무시
                    if (map[nx][ny] > 0) count++; // 성장할 수 있는 나무 카운트
                }
                map[x][y] += count; // 성장
            }
        }
    }

    // 범위 체크
    private static boolean checkRange(int x, int y) {
        return 0 > x || x >= N || 0 > y || y >= N; // 격자 범위를 벗어나는지 검사
    }

    // 나무 정보를 저장하기 위한 내부 클래스
    static class Tree {
        int x, y;

        public Tree(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

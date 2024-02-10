import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 기존 코드는 재귀 함수 내부에서 이중 루프로 코어를 탐색, 탐색한 코어는 visited로 관리해 다음 재귀에서 탐색하지 않도록 했음 -> 모든 경우의 수를 확인 하지 못하는 문제.
 * 방문해야 하는 코어 위치를 초반에 List에 담아두고, depth로 관리해서 해결 (풀이 참고) --->> basis part 잡기 수월해 짐
 * 2. 기존 코드는 전체 전선 길이를 변경하고, 재귀를 나오면 원복시키도록 하고 있었으나, (문제는 없었지만 번거로웠음)
 * 전선 한 줄의 길이를 따로 연산하는 메서드(fill)을 만들어, 매개 변수에서만 더해서 다음 재귀로 넘김. --->> 원복시킬 필요 x
 */
public class Main_semin {
    static int N, maps[][], oneLineLength;
    static Ans res;
    static List<Point> coreList;
    static int[] dx = {1, 0, -1, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            coreList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                    if (maps[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
                        coreList.add(new Point(j, i)); //가장 자리에 있으면 어짜피 전선 길이에 영향 x -> 빼고 담기
                    }
                }
            }
            res = new Ans(12345, -12345); //전선 길이는 짧아야, 코어는 많아야 함으로 반대로 초기화
            connectProcess(0, 0, 0);
            sb.append(res.line).append("\n");
        }
        System.out.println(sb);
    }

    private static void connectProcess(int depth, int totalCore, int totalLine) {
        if (depth == coreList.size()) {
            Ans ans = new Ans(totalLine, totalCore);
            if (res.compareTo(ans) < 0) { //기존 res가 ans보다 작다면
                res = ans;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            if (!existLineOrCore(coreList.get(depth), d)) {
                fill(coreList.get(depth), d, 2);
                connectProcess(depth + 1, totalCore + 1, totalLine + oneLineLength);
                fill(coreList.get(depth), d, 0);
            }
        }
        connectProcess(depth + 1, totalCore, totalLine);
    }

    static void fill(Point p, int d, int value) {
        oneLineLength = 0;
        int nx = p.x + dx[d];
        int ny = p.y + dy[d];
        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            oneLineLength++;
            maps[ny][nx] = value; //전선은 2로 표시
            nx += dx[d];
            ny += dy[d];
        }
    }

    private static boolean existLineOrCore(Point p, int d) {
        int nx = p.x + dx[d];
        int ny = p.y + dy[d];
        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            if (maps[ny][nx] != 0) {
                return true;
            }
            nx += dx[d];
            ny += dy[d];
        }
        return false;
    }

    static class Ans implements Comparable<Ans> {
        int line;
        int core;

        Ans(int line, int core) {
            this.line = line;
            this.core = core;
        }

        @Override
        public int compareTo(Ans o) {
            if (this.core - o.core == 0) {
                return o.line - this.line;
            }
            return this.core - o.core;
        }
    }

}
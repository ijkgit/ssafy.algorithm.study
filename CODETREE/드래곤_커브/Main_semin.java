import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 규칙 : 이전까지의 방향 진행 사항을 가져와서 reverse, +1 해서 기존 것에 더하기
 * 0
 * 0 (1)
 * 0 1 (2 1)
 * 0 1 2 1 (2 3 2 1)
 * */
public class Main {
    static int x, y, d, g, maps[][];
    static int[] dx = {0, -1, 0, 1}; //0:오, 1:위, 2:왼, 3:아
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        maps = new int[101][101];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); //시작 x
            y = Integer.parseInt(st.nextToken()); //시작 y
            d = Integer.parseInt(st.nextToken()); //시작 방향
            g = Integer.parseInt(st.nextToken()); //차수 g
            maps[x][y] = 1;
            List<Integer> prev = new ArrayList<>();
            prev.add(d);
            for (int j = 0; j < g; j++) { //좌표의 진행 방향을 추가한다. (이전 것을 뒤집어서 +1만큼)
                List<Integer> cur = new ArrayList<>();
                cur.addAll(prev);
                for (int k = prev.size() - 1; k >= 0; k--) {
                    int nd = (prev.get(k) + 1) % 4;
                    cur.add(nd);
                }
                prev = cur;
            }
            for (int k = 0; k < prev.size(); k++) { //드래곤 커브를 지도에 마크한다.
                d = prev.get(k);
                int nx = x + dx[d];
                int ny = y + dy[d];
                maps[nx][ny] = 1;
                x = nx;
                y = ny;
            }
        }
        int square = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isSquare(i, j)) { //정사각형인지 확인
                    square++;
                }
            }
        }
        System.out.println(square);
    }

    private static boolean isSquare(int i, int j) {
        try {
            if (maps[i][j] == 1 && maps[i][j + 1] == 1 && maps[i + 1][j] == 1 && maps[i + 1][j + 1] == 1) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

}
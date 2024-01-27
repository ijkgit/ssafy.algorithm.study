import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Solution_sangphil {
    static ArrayList<ArrayList<Integer>> board;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1};
    static int[] dy = {1, -1, 0};
    static int ans;
	public static void main (String[] args) throws IOException {
    	System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 0; t < 10; t++) {
            br.readLine();
            ans = 0;

            board = new ArrayList<>();
            visited = new boolean[100][100];

            for (int i = 0; i < 100; i++) {
                board.add(Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new)));
            }

            for (int i = 0; i < 100; i++) {
                if (board.get(0).get(i) == 1) {
                    dfs(0, i, i);
                    if (ans != 0) break;
                }
            }

            System.out.printf("#%d %d\n", t+1, ans);
        }
    }

    static void dfs(int x, int y, int s) {
        visited[x][y] = true;

        if (board.get(x).get(y) == 2) {
            ans = s;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < 100 && 0 <= ny && ny < 100) {
                if (!visited[nx][ny] && board.get(nx).get(ny) != 0) {
                    dfs(nx,ny,s);
                    break;
                }
            }
        }

        visited[x][y] = false;
    }
}
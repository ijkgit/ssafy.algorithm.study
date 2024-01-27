import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Solution_sangphil {
    static class Pair {
        int x;
        int y;
        int w;
        public Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

    }

    static ArrayList<ArrayList<Integer>> board;
    static int[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, ans;
	public static void main (String[] args) throws IOException {
    	// System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = Integer.MAX_VALUE;

            board = new ArrayList<>();
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = Integer.MAX_VALUE;
                }
                
            }

            for (int i = 0; i < N; i++) {
                board.add(Arrays.stream(br.readLine().split("")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new)));
            }

            visited[0][0] = board.get(0).get(0);
            Deque<Pair> q = new LinkedList<Pair>() {{ add(new Pair(0, 0, visited[0][0]));}};
            while (!q.isEmpty()) {
                Pair p = q.poll();
                if (p.x == N-1 && p.y == N-1) {
                    ans = Math.min(ans, p.w);
                }

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if (0 <= nx && nx < N&& 0 <= ny && ny < N) {
                        int nw = visited[p.x][p.y] + board.get(nx).get(ny);
                        if (visited[nx][ny] > nw) {
                            visited[nx][ny] = nw;
                            q.add(new Pair(nx, ny, nw));
                        }
                    }
                }

            }

            System.out.printf("#%d %d\n", t+1, ans);
        }
    }
}
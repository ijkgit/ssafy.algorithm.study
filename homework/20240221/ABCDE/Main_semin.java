import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_semin {
    private static int n, m;
    private static boolean exist = false;
    private static ArrayList<Integer>[] graphs;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graphs = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graphs[a].add(b);
            graphs[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, 0);
            if (exist) {
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }
        System.out.println(0);
    }

    private static void dfs(int start, int depth) {
        if (depth == 4) {
            exist = true;
            return;
        }
        for (int next : graphs[start]) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            dfs(next, depth + 1);
            visited[next] = false;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 플로이드 워셜 : 모든 정점에서 모든 정점으로의 최단 경로
 * 거쳐가는 정점을 기준으로 한다. (거쳐가는 노드가 반복문의 중심)
 */
public class Main_semin {
    static int n, m, maps[][], INF = 1000000; // 최단경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maps = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    maps[i][j] = 0;
                    continue;
                }
                maps[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            int u, v, b;
            st = new StringTokenizer(br.readLine());
            // u->v 로 가고, 0일 경우 u->v 일방통행, 1일 경우 u<->v 양방통행
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (b == 1) {
                maps[u][v] = 0;
                maps[v][u] = 0;
                continue;
            }
            maps[u][v] = 0;
            maps[v][u] = 1; //반대로 가려고 할 때 비용 발생
        }

        //플로이드 워셜 (모든정점 to 모든정점)
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (maps[i][k] + maps[k][j] < maps[i][j]) {
                        maps[i][j] = maps[i][k] + maps[k][j];
                    }
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 1; i <= k; i++) { //경유지
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); //출발
            int e = Integer.parseInt(st.nextToken()); //도착
            sb.append(maps[s][e]).append("\n");
        }
        System.out.println(sb);
    }
}

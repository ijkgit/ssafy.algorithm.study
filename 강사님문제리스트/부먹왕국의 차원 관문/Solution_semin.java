import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_semin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int city = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int[] mapData = new int[city + 2];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= city; i++) {
                mapData[i] =Integer.parseInt(st.nextToken());
            }
            mapData[0] = mapData[city + 1] = 1;
            int idx = 0;
            int cnt = 0;
            L:
            while (true) {
                if (idx >= city + 1) {
                    break;
                }
                for (int j = 1; j <= D; j++) {
                    if (mapData[idx + j] == 1) {
                        idx = idx + j;
                        continue L;
                    }
                }
                cnt++;
                idx = idx + D;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}

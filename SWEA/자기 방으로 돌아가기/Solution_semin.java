import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_semin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] maps = new int[201]; //1,2번방 복도 ~ 399,400번방 복도 총 200개
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == b) {
                    continue;
                }
                int big = Math.max(a, b);
                int small = Math.min(a, b);
                big = big % 2 == 0 ? big : big + 1;
                small = small % 2 == 0 ? small : small + 1; //홀짝 통일 (전부 홀수 만들기)
                for (int k = small; k < big + 1; k += 2) {
                    maps[Math.floorDiv(k, 2)]++;
                }
            }
            sb.append(Arrays.stream(maps).max().getAsInt()).append("\n");
        }
        System.out.println(sb);
    }
}

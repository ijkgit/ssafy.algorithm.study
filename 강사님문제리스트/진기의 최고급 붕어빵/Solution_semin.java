import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution_semin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // N명의 사람이 자격을 얻는다.
            int M = Integer.parseInt(st.nextToken()); // M초의 시간을 들이면
            int K = Integer.parseInt(st.nextToken()); // K개의 붕어빵을 만들 수 있다.

            st = new StringTokenizer(br.readLine());
            int[] times = new int[N];
            for (int i = 0; i < N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(times);

            int idx;
            for (idx = 0; idx < N; idx++) {
                if ((times[idx] / M) * K - (idx) > 0) {
                    continue;
                }
                break;
            }
            if (idx == N) {
                sb.append("Possible\n");
            }else{
                sb.append("Impossible\n");
            }
        }
        System.out.println(sb);
    }
}

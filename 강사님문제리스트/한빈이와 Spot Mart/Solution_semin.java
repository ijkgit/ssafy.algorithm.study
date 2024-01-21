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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int result = -1;
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i]=(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int sum = arr[i] + arr[j];
                    if (sum <= m && result < sum) {
                        result = sum;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}

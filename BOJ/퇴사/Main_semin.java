import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, dp[];

    static Consult[] consults;

    static class Consult {
        int time, price;

        Consult(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        consults = new Consult[N + 1];
        dp = new int[N + 1];
        for (int i = N; i >0; i--) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            consults[i] = new Consult(t, p);
        }

        for (int i = 1; i <= N; i++) {
            if (consults[i].time > i) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 1], consults[i].price + dp[i - consults[i].time]);
            }
        }

        System.out.println(dp[N]);

    }
}


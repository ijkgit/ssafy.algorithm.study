import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, D, K, C;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int ans = 0, count = 0;
        int[] check = new int[D+1];

        // 0 ~ k-1 => 첫 연속 먹기
        for (int k = 0; k < K; k++) {
            if (check[arr[k]] == 0) count++;
            check[arr[k]]++;
        }

        for (int n = 0; n < N; n++) {
            // 쿠폰 체크
            if (check[C] == 0) ans = Math.max(ans, count+1);
            else ans = Math.max(ans, count);

            // 첫번째 인덱스 한 칸 앞으로
            check[arr[n]]--;
            if(check[arr[n]] == 0) count--;
            // 끝 인덱스 한 칸 앞으로
            if(check[arr[(n+K)%N]] == 0) count++;
            check[arr[(n+K)%N]]++;
        }

        return ans;
    }
}

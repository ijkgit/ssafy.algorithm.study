import java.io.*;
import java.util.*;

public class Main_sangphil {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
        }

        int a, b;
        double diff;
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            diff = arr[b] - arr[a-1];
            sb.append(String.format("%.2f", Math.round((diff/(b-a+1))* 100) / 100.0) + "\n");
        }
        System.out.println(sb);
    }
}
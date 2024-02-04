import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int n, ans;
    int[] durability, weight;
    boolean[] isBroken, visited;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        durability = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        sol(0, 0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol(int idx, int cnt) {
        // basis part
        // 마지막 계란
        if (idx == n) {
            ans = Math.max(ans, cnt);
            return;
        }

        // 손에든 계란이 깨졌거나 모든 계란이 깨진 경우
        if (durability[idx] <= 0 || cnt == n - 1) {
            sol(idx + 1, cnt);
            return;
        }

        // inductive part
        int tmp = cnt;
        for (int i = 0; i < n; i++) {
            if (i == idx) continue; // 본인인 경우
            if (durability[i] <= 0) continue; // 이미 깨진 경우

            // 깨기
            durability[i] -= weight[idx];
            durability[idx] -= weight[i];

            // 깨진 경우
            if (durability[idx] <= 0) cnt++;
            if (durability[i] <= 0) cnt++;

            // 다음 계란
            sol(idx + 1, cnt);

            // 깨진 거 복구
            durability[i] += weight[idx];
            durability[idx] += weight[i];
            cnt = tmp;
        }
    }
}

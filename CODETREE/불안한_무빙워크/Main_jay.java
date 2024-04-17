import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2 * N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(sb.append(sol()).toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int t = 1;
        while (true) {
            rotate();
            move();
            start();
            if(check()) return t;
            t++;
        }
    }

    private static void print() {
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(visited));
    }

    private static boolean check() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if(arr[i] == 0) cnt++;
            if (cnt == K) return true;
        }
        return false;
    }

    private static void start() {
        if (visited[0] || arr[0] == 0) return;
        visited[0] = true;
        arr[0]--;
    }

    private static void rotate() {
        int tmp = arr[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

        for (int i = N - 1; i > 0; i--) {
            visited[i] = visited[i - 1];
        }
        visited[0] = false;
        visited[N - 1] = false;
    }

    private static void move() {
        for (int i = N - 2; i >= 0; i--) {
            if (visited[i+1] || arr[i+1] == 0) continue;
            if (!visited[i]) continue;
            visited[i+1] = visited[i];
            visited[i] = false;
            arr[i+1]--;
        }
        visited[N - 1] = false;
    }
}

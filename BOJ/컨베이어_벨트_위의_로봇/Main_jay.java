import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int K;
    private static int[] A;
    private static boolean[] visited;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[2*N];
        visited = new boolean[2*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        ans = 1;
        while(true) {
            rotate(); // 1번 동작
            move(); // 2번 동작
            put(); // 3번 동작
            if(count()) return; // 4번 동작
            ans++;
        }
    }

    private static boolean count() {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            if(A[i] == 0) cnt++;
        }
        if (cnt >= K) return true;
        return false;
    }

    private static void put() {
        if (A[0] != 0) {
            visited[0] = true;
            A[0]--;
        }
    }

    private static int findLast() {
        for (int i = N-2; i >= 0; i--) {
            if(visited[i]) return i;
        }
        return -1;
    }

    private static void move() {
        int key = findLast();
        if (key == -1) return;
        for (int i = key; i >= 0; i--) {
            if (visited[i] && !visited[i+1] && A[i+1] != 0) {
                visited[i] = false;
                visited[i+1] = true;
                A[i+1]--;
            }
        }
        visited[N-1] = false;
    }

    private static void rotate() {
        int tmp = A[2*N-1];
        boolean tmp2 = visited[2*N-1];
        for (int i = A.length - 1; i > 0; i--) {
            A[i] = A[i-1];
            visited[i] = visited[i-1];
        }
        A[0] = tmp;
        visited[0] = tmp2;
        visited[N-1] = false;
    }
}

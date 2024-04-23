package edu.ssafy.im.CodeTree.UnstableMovingWalk;

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

        arr = new int[2 * N]; // 내구도를 저장하는 배열
        // 각 사람은 1번 칸에 올라서서 n번 칸에서 내리기 때문에 사람배열의 사이즈는 N
        visited = new boolean[N]; // 사람을 저장하는 배열
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
        while (true) { // 안정성이 0인 칸이 k개 이상이 아닐 경우 과정을 반복합니다.
            rotate();
            move();
            start();
            if(check()) return t; // 안정성이 0인 칸이 k개 이상이라면 과정을 종료합니다.
            t++;
        }
    }

    // 1. 무빙워크가 한 칸 회전합니다.
    private static void rotate() {
        // 내구도 이동
        int tmp = arr[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

        // 사람 이동
        for (int i = N - 1; i > 0; i--) {
            visited[i] = visited[i - 1];
        }
        visited[0] = false;

        visited[N - 1] = false; // n번 칸에서 내리게 됩니다.
    }

    // 2. 무빙워크가 회전하는 방향으로 한 칸 이동할 수 있으면 이동합니다.
    private static void move() {
        // 가장 먼저 무빙워크에 올라간 사람부터
        for (int i = N - 2; i >= 0; i--) {
            // 앞선 칸에 사람이 이미 있거나 앞선 칸의 안정성이 0인 경우에는 이동하지 않습니다.
            if (visited[i+1] || arr[i+1] == 0) continue;
            // 사람이 없는 칸이면 조치를 취할 필요가 없음
            if (!visited[i]) continue;

            // 사람이 이동하면 그 칸의 안정성은 즉시 1만큼 감소
            visited[i+1] = visited[i];
            visited[i] = false;
            arr[i+1]--;
        }
        visited[N - 1] = false; // n번 칸에서 내리게 됩니다.
    }

    // 3. 1번 칸에 사람이 없고 안정성이 0이 아니라면 사람을 한 명 더 올립니다.
    private static void start() {
        if (visited[0] || arr[0] == 0) return;
        visited[0] = true;
        arr[0]--; // 사람이 어떤 칸에 올라가면 그 칸의 안정성은 즉시 1만큼 감소
    }

    // 4. 안정성이 0인 칸이 k개 이상이라면 과정을 종료합니다. 그렇지 않다면 다시 위의 과정을 반복합니다.
    private static boolean check() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if(arr[i] == 0) cnt++;
            if (cnt == K) return true; // 안정성이 0인 칸이 k개 이상이라면 과정을 종료합니다.
        }
        return false;
    }
}

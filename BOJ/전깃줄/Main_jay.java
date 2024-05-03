import java.io.*; 
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Line[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /*
    문제 : 시작 점을 오름차순으로 정렬했을 때, 도착 점이 연속으로 증가할 수 있는 최대 길이 구하기
    LIS : 최장 증가 부분 수열
     */
    private static int sol() {
        Arrays.sort(lines);

        int[] length = new int[N];
        for (int k = 0; k < N; k++) {
            length[k] = 1;
            for (int i = 0; i < k; i++) {
                if (lines[i].b < lines[k].b) length[k] = Math.max(length[k], length[i] + 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, length[i]);
        }

        return N - ans;
    }

    static class Line implements Comparable<Line> {
        int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            return a - o.a;
        }
    }
}

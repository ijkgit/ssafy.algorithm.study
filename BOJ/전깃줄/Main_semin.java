import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//풀이 1
public class Main_semin {
    static int N;
    static 전깃줄.Pair[] candi;

    static class Pair implements Comparable<전깃줄.Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(전깃줄.Pair o) {
            return Integer.compare(this.a, o.a);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        candi = new 전깃줄.Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            candi[i] = new 전깃줄.Pair(a, b);
        }
        Arrays.sort(candi);
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (candi[j].b < candi[i].b) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        System.out.println(N - Arrays.stream(dp).max().getAsInt());
    }
}

//풀이 2
public class Main_semin_2 {
    static int N;
    static Pair[] candi;

    static class Pair implements Comparable<Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.a, o.a);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        candi = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            candi[i] = new Pair(a, b);
        }

        Arrays.sort(candi);
        int[] LIS = new int[N];
        LIS[0] = candi[0].b;
        int len = 1; //지금까지 만든 LIS의 길이

        /**
         * 1. 마지막 것보다 cur이 크면 그냥 추가
         * 2. 아니라면 cur 보다 큰 수 중에 가장 작은 수를 찾아, cur로 대치
         *    LIS 배열에 담긴 수가 가장 작게 유지되어야 뒤에 추가할 수 있는 경우의 수가 더 많아짐
         * */
        for (int i = 0; i < N; i++) {
            int cur = candi[i].b;
            if (LIS[len - 1] < cur) {
                len++;
                LIS[len - 1] = cur;
            } else {
                int low = 0;
                int high = len;
                while (low < high) { //이분 탐색 조건
                    int mid = (low + high) / 2;
                    if (LIS[mid] < cur) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                LIS[low] = cur;
            }
        }
        System.out.println(N - len);
    }
}

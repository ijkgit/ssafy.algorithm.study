import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_semin {
    static int n, k, cnt;
    static Node[] con;

    static class Node {
        int num;
        boolean status;
        int strong;

        public Node(int num, boolean status, int strong) {
            this.num = num;
            this.status = status;
            this.strong = strong;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", status=" + status +
                    ", strong=" + strong +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        con = new Node[n * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            con[i] = new Node(i, false, Integer.parseInt(st.nextToken()));
        }
        cnt = 0;
        System.out.println(solve());
    }


    private static int solve() {
        int solvingCnt = 1;
        while (true) {
            //무빙워크가 한 칸 회전
            Node[] tmp = new Node[n * 2];
            tmp[0] = con[n * 2 - 1];
            for (int i = 1; i < n * 2; i++) {
                tmp[i] = con[i - 1];
            }

            if (tmp[n - 1].status) {
                tmp[n - 1].status = false; // 사람 내리기
            }

            //사람들의 이동
            for (int i = n - 1; i > 0; i--) {
                if (!tmp[i].status && tmp[i].strong > 0 && tmp[i - 1].status) { //현재 사람이 없고, i-1에 사람이 있으면 이동
                    tmp[i - 1].status = false;
                    tmp[i].status = true;
                    if (check(i, tmp)) {
                        return solvingCnt;
                    }
                }
            }

            if (tmp[n - 1].status) {
                tmp[n - 1].status = false; // 사람 내리기
            }

            //1번 칸에 사람이 없고 안정성이 0이 아니라면 사람을 한 명 더 올립니다.
            if (!tmp[0].status && tmp[0].strong > 0) {
                tmp[0].status = true;
                if (check(0, tmp)) {
                    return solvingCnt;
                }
            }

            con = tmp;
            solvingCnt++;
        }
    }

    private static boolean check(int i, Node[] tmp) {
        tmp[i].strong--;
        if (tmp[i].strong == 0) {
            cnt++;
        }
        if (cnt >= k) {
            return true;
        }
        return false;
    }
}

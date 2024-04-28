import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 101;
    static int r, c, k, width, height;
    static int[][] maps;

    static class Num implements Comparable<Num> {
        int n, cnt;

        public Num(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Num o) {
            if (this.cnt == o.cnt) {
                return Integer.compare(this.n, o.n);
            }
            return Integer.compare(this.cnt, o.cnt);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        maps = new int[100][100];
        width = 3;
        height = 3;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int turn = 0; turn <= 100; turn++) {
            if (maps[r][c] == k) {
                System.out.println(turn);
                return;
            }
            if (height >= width) {
                maps = sortByRow();
            } else {
                maps = sortByColumn();
            }
        }
        System.out.println(-1);
    }

    private static int[][] sortByColumn() {
        int[][] temp = new int[100][100];
        int maxHeight = 0;
        for (int i = 0; i < 100; i++) {
            Num[] dic = new Num[101];
            for (int j = 0; j <= 100; j++) { //정렬할 때 오름차순 정렬이라, cnt는 INF로 초기화 해야함
                dic[j] = new Num(j, INF);
            }
            for (int j = 0; j < 100; j++) {
                int n = maps[j][i];
                if (n == 0) { //0은 세지 않음
                    continue;
                }
                if (dic[n].cnt == INF) { //해당 숫자가 있는 것이므로, 0으로 초기화하고 셈 시작
                    dic[n].cnt = 0;
                }
                dic[n].n = n;
                dic[n].cnt++;
            }
            Arrays.sort(dic); //1.cnt 오름차순, 2.숫자 오름차순
            int idx = 0;
            for (int j = 0; j < 100; j = j + 2) {
                if (dic[idx].n == 0) { //0이 나온건 이 줄 연산이 끝난 것
                    break;
                }
                temp[j][i] = dic[idx].n;
                temp[j + 1][i] = dic[idx].cnt;
                idx++;
            }
            maxHeight = Math.max(idx * 2, maxHeight);
        }
        height = maxHeight;
        return temp;
    }

    private static int[][] sortByRow() {
        int[][] temp = new int[100][100];
        int maxWidth = 0;
        for (int i = 0; i < 100; i++) {
            Num[] dic = new Num[101];
            for (int j = 0; j <= 100; j++) {
                dic[j] = new Num(j, 101);
            }
            for (int j = 0; j < 100; j++) {
                int n = maps[i][j];
                if (n == 0) {
                    continue;
                }
                if (dic[n].cnt == 101) {
                    dic[n].cnt = 0;
                }
                dic[n].n = n;
                dic[n].cnt++;
            }
            Arrays.sort(dic);
            int idx = 0;
            for (int j = 0; j < 100; j = j + 2) {
                if (dic[idx].n == 0) {
                    break;
                }
                temp[i][j] = dic[idx].n;
                temp[i][j + 1] = dic[idx].cnt;
                idx++;
            }
            maxWidth = Math.max(idx * 2, maxWidth);
        }
        width = maxWidth;
        return temp;
    }

}

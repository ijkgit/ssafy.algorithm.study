import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
    static int[][] eggs;
    static int N, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        res = 0;
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2]; //0:내구도, 1:무게
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }
        permutation(0);
        System.out.println(res);
    }

    public static void permutation(int idx) {
        //basis
        if (idx == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                //System.out.println(Arrays.toString(eggs[i]));
                if (eggs[i][0] <= 0) {
                    sum++;
                }
            }
            res = Math.max(sum, res);
            return;
        }

        //inductive
        boolean flag = false;
        if (eggs[idx][0] > 0) {
            for (int i = 0; i < N; i++) {
                if (i == idx) {
                    continue;
                }
                if (eggs[i][0] < 0) {
                    continue;
                }
                eggs[idx][0] -= eggs[i][1];
                eggs[i][0] -= eggs[idx][1];
                permutation(idx + 1);
                eggs[idx][0] += eggs[i][1];
                eggs[i][0] += eggs[idx][1];
                flag = true;
            }
        }
        if (!flag || eggs[idx][0] <= 0) {
            permutation(idx + 1);
        }
    }
}

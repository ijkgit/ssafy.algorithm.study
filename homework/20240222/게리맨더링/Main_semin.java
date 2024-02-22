import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_semin {
    static int[] population;
    static int N, res;
    static List<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        res = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        adj = new ArrayList[N + 1];
        adj[0] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int size = 1; size <= (N + 1) / 2; size++) {
            combination(1, 0, new int[size]);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    public static void combination(int idx, int k, int[] selected) {
        if (k == selected.length) {
            int[] others = makeOthers(selected);
            int aSum = getSum(selected);
            int bSum = getSum(others);
            int diff = Math.abs(aSum - bSum);
            if (diff < res) {
                if (isConnected(selected) && isConnected(others)) {
                    res = diff;
                }
            }
            return;
        }
        if (idx > N) {
            return;
        }
        selected[k] = idx;
        combination(idx + 1, k + 1, selected);
        combination(idx + 1, k, selected);
    }

    private static boolean isConnected(int[] arr) {
        // arr에 있는 애들이 전부 visited 체크 되어있는지 보기
        visited = new boolean[N + 1];
        visited[arr[0]] = true;
        dfs(arr[0], arr);
        for (int num : arr) {
            if (!visited[num]) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int start, int[] arr) {
        for (int next : adj[start]) {
            if (!visited[next] && exist(next, arr)) {
                visited[next] = true;
                dfs(next, arr);
            }
        }
    }

    private static boolean exist(int next, int[] arr) {
        for (int num : arr) {
            if (next == num) {
                return true;
            }
        }
        return false;
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += population[arr[i]];
        }
        return sum;
    }

    private static int[] makeOthers(int[] selected) {
        int[] others = new int[N - selected.length];
        int cnt = 0;
        L:
        for (int num = 1; num <= N; num++) {
            for (int j = 0; j < selected.length; j++) {
                if (selected[j] == num) {
                    continue L; // selected 에 num 에 포함된 게 있으면
                }
            }
            others[cnt++] = num;
            if (cnt == others.length) {
                break;
            }
        }
        return others;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, K;
    static LinkedList<Stat> belt;

    static class Stat {
        int strong;
        boolean robot;

        public Stat(int strong, boolean robot) {
            this.strong = strong;
            this.robot = robot;
        }

        @Override
        public String toString() {
            return "[" + strong + "," + robot + ']';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            belt.add(new Stat(Integer.parseInt(st.nextToken()), false));
        }
        solve();
    }

    private static void solve() {
        int result = 1;
        while (true) {
            int cnt = 0;

            //벨트+로봇이 이동한다.
            belt.addFirst(belt.getLast());
            belt.removeLast();

            //로봇이 내리는 위치에 도달하면 그 즉시 내린다
            if (belt.get(N - 1).robot) {
                belt.get(N - 1).robot = false;
            }

            //로봇이 이동한다. (0~2N)
            for (int i = 2 * N - 1; i >= 0; i--) {
                Stat s = belt.get(i);
                if (s.strong == 0) {
                    cnt++;
                }
                if (s.robot) {
                    int next = i + 1 == 2 * N ? 1 : i + 1;
                    if (!belt.get(next).robot && belt.get(next).strong >= 1) {
                        belt.get(next).robot = true;
                        belt.get(next).strong--;
                        s.robot = false;
                        if (belt.get(next).strong == 0) {
                            cnt++;
                        }
                    }
                }
            }

            if (belt.get(N - 1).robot) {
                belt.get(N - 1).robot = false;
            }

            //i=1에 로봇을 올린다.
            if (belt.get(0).strong != 0) {
                belt.get(0).robot = true;
                belt.get(0).strong--;
                if (belt.get(0).strong == 0) {
                    cnt++;
                }
            }

            //내구도가 0인 칸이 k개 이상이면 종료한다.
            if (cnt >= K) {
                break;
            }
            result++;
        }
        System.out.println(result);
    }

}

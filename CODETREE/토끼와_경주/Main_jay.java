package edu.ssafy.im.CodeTree.rabitAndRace;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, P;
    private static Queue<Rabbit> rabbits;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Rabbit implements Comparable<Rabbit> {
        int pid, d, n, r, c;
        long s;

        public Rabbit(int pid, int d, int n, int r, int c, long s) {
            this.pid = pid;
            this.d = d;
            this.n = n;
            this.r = r;
            this.c = c;
            this.s = s;
        }

        @Override
        public int compareTo(Rabbit o) {
            if (this.n == o.n) {
                if (this.r + this.c == o.r + o.c) {
                    if (this.r == o.r) {
                        if (this.c == o.c) return this.pid - o.pid;
                        return this.c - o.c;
                    }
                    return this.r - o.r;
                }
                return (this.r + this.c) - (o.r + o.c);
            }
            return this.n - o.n;
        }
    }

    private static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        rabbits = new PriorityQueue<>();
        for (int p = 0; p < P; p++) {
            int pid = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            rabbits.offer(new Rabbit(pid, d, 0, 0, 0, 0));
        }
    }

    private static void run(StringTokenizer st) {
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int fs = 0, fr = 0, fc = 0, fpid = 0;
        for (int k = 0; k < K; k++) {
            Rabbit rabbit = rabbits.poll();
            // 이동
            int rs = 0, rr = 0, rc = 0;
            for (int dir = 0; dir < direction.length; dir++) {
                int nr = rabbit.r + direction[dir][0] * rabbit.d;
                int nc = rabbit.c + direction[dir][1] * rabbit.d;
                if (dir % 2 == 0) {
                    int dd = nr % (N - 1);
                    if ((nr / (N - 1)) % 2 != 0) {
                        if (dd >= 0) nr = (N - 1) - dd;
                        else nr = (N - 1) + dd;
                    } else {
                        nr = Math.abs(dd);
                    }
                } else {
                    int dd = nc % (M - 1);
                    if ((nc / (M - 1)) % 2 != 0) {
                        if (dd >= 0) nc = (M - 1) - dd;
                        else nc = (M - 1) + dd;
                    } else {
                        nc = Math.abs(dd);
                    }
                }
                int ns = (nr+1) + (nc+1);
                if (rs < ns || (rs == ns && rr < nr) || (rs == ns && rr == nr && rc < nc)) {
                    rs = ns;
                    rr = nr;
                    rc = nc;
                    if (fs < rs || (fs == rs && fr < rr) || (fs == rs && fr == nr && fc < rc)) {
                        fs = rs;
                        fr = rr;
                        fc = rc;
                        fpid = rabbit.pid;
                    }
                }
            }

            // 뛴 토끼 제외 점수 얻기
            for (Rabbit r : rabbits) r.s += rs;

            // 뛴 토끼 다시 넣기
            rabbits.offer(new Rabbit(rabbit.pid, rabbit.d, rabbit.n + 1, rr, rc, rabbit.s));
        }

        // 최종 토끼 점수 얻기
        for (Rabbit r : rabbits) {
            if (r.pid != fpid) continue;
            r.s += S;
            break;
        }
    }

    private static void change(StringTokenizer st) {
        int pid_t = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        for (Rabbit r : rabbits) {
            if (r.pid != pid_t) continue;
            r.d *= L;
            break;
        }
    }

    private static void finish() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        long ans = 0;
        for (Rabbit r : rabbits) ans = Math.max(ans, r.s);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 100) init(st);
            else if (command == 200) run(st);
            else if (command == 300) change(st);
            else finish();
        }
    }
}

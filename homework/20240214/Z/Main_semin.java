import java.util.Scanner;

public class Main_semin {
    static int N, r, c, cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        cnt = 0;
        int len = (int) Math.pow(2, N);
        dnq(len, 0, 0);
        System.out.println(cnt);
    }

    public static void dnq(int len, int x, int y) {
        if (len == 1) {
            return;
        }
        int hMid = y + len / 2;
        int wMid = x + len / 2;
        int size=(len/2)*(len/2);
        if (r < hMid && c < wMid) {
            dnq(len / 2, x, y);
        }
        if (r < hMid && c >= wMid) {
            dnq(len / 2, wMid, y);
            cnt += size;
        }
        if (r >= hMid && c < wMid) {
            dnq(len / 2, x, hMid);
            cnt += size * 2;
        }
        if (r >= hMid && c >= wMid) {
            dnq(len / 2, wMid, hMid);
            cnt += size * 3;
        }
    }
}
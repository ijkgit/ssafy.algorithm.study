
import java.util.Scanner;

public class Main {
    static int[][] list = new int[10][10]; // 스토쿠

    static int doubleCheck(int cury, int curx, int a) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a == list[cury + i][curx + j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static boolean checkSmallBox() {
        boolean check = true;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int cnt = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        cnt = doubleCheck(i, j, list[i + k][j + l]);
                    }
                }
                if (cnt > 1) return false;
            }
        }
        return check;
    }

    static boolean checkRow() {
        boolean check = true;
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                int initial = list[k][i];
                int cnt = 0;
                for (int j = 0; j < 9; j++) {
                    if (initial == list[i][j]) {
                        cnt++;
                    }
                }
                if (cnt > 1) return false;
            }
        }
        return check;
    }

    static boolean checkCol() {
        boolean check = true;
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                int initial = list[i][k];
                int cnt = 0;
                for (int j = 0; j < 9; j++) {
                    if (initial == list[j][i]) {
                        cnt++;
                    }
                }
                if (cnt > 1) return false;
            }
        }
        return check;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; ++test_case) {
            boolean res = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    list[i][j] = sc.nextInt();
                }
            }
            res = checkSmallBox();
            res = res && checkCol();
            res = res && checkRow();

            if (!res) System.out.println("#" + test_case + " " + 0);
            else System.out.println("#" + test_case + " " + 1);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 시간초과 해결!! */
public class Solution_semin {
    static int size, Ans;
    static Set<Integer> newNums;
    static int[] dp = new int[99999];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            String number = br.readLine();
            Ans = 0;
            Arrays.fill(dp, -1);
            recursive(number, 0);
            sb.append(Ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void recursive(String number, int depth) {
        int num = Integer.parseInt(number);
        if (dp[num] != -1) {
            Ans = Math.max(dp[num] + depth, Ans);
            return;
        }
        if (number.length() <= 1) {
            Ans = Math.max(depth, Ans);
            dp[num] = 0;
            return;
        }
        newNums = new HashSet<>();
        for (int i = 2; i <= number.length(); i++) {
            size = i; // 1~n-1
            int[] idxs = new int[size];
            idxs[size - 1] = number.length() - 1; // 맨 끝 인덱스 넣기
            idxCombination(0, 0, idxs, number);
        }
        int maxTurn = 0; //현재 숫자로 시작해서 1자리 수가 될 때까지의 최대 턴 수
        for (int newNum : newNums) {
            recursive(String.valueOf(newNum), depth + 1);
            maxTurn = Math.max(maxTurn, dp[newNum]);
        }
        dp[num] = maxTurn + 1;
        Ans = Math.max(depth + dp[num], Ans);
    }

    private static void idxCombination(int idx, int k, int[] idxs, String number) {
        if (k == size - 1) { // 인덱스의 조합
            int mul = calculateMul(idxs, number);
            newNums.add(mul);
            return;
        }
        if (idx == number.length() - 1) { // 쪼개는 개수는 숫자 -1
            return;
        }
        idxs[k] = idx;
        idxCombination(idx + 1, k + 1, idxs, number);
        idxCombination(idx + 1, k, idxs, number);
    }

    private static int calculateMul(int[] idxs, String number) {
        char[] nums = number.toCharArray();
        int mul = 1;
        String temp = "";
        int cnt = 0; // split 할 기준
        for (int i = 0; i < nums.length; i++) {
            if (i < idxs[cnt]) {
                temp += nums[i];
            }
            if (i == idxs[cnt]) {
                temp += nums[i];
                mul *= Integer.valueOf(temp);
                temp = "";
                cnt++; // 기준을 업데이트 한다.
            }
        }
        return mul;
    }
}

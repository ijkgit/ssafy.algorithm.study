import java.io.*;
import java.util.*;

public class Solution {
    private String num;
    private ArrayList<Integer> numList;
    private int ans;
    private int[] dp;
    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            num = br.readLine();
            ans = Integer.MIN_VALUE;
            sol();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        dp = new int[Integer.parseInt(num)+1];
        if (num.length() > 1) { // 이미 길이가 1인 경우 터치 = 0
            setPermutation(num);
            ans = dp[Integer.parseInt(num)];
        } else {
            ans = 0;
        }
    }

    // 숫자의 길이에 따라 터치할 횟수를 정하여 어느 부분을 터치할지 결정한다.
    private void setPermutation(String num) {
        for (int i = 1; i < num.length(); i++) {
            int[] sel = new int[i];
            permutation(0, 0, num, sel);
        }
    }

    private void permutation(int k, int v, String num, int[] sel) {
        if(k == sel.length) {
            int res = makeNum(num, sel);

            if(res == -1) {
                return;
            }
            if(res != -1) { // 곱의 합이 10 이상일 경우 해당 숫자를 다시 터치
                if (dp[res] == 0) { // 처음 들르는 값인 경우 터치하여 갱신 필요
                    setPermutation(String.valueOf(res));
                }
                if (dp[res] + 1 > dp[Integer.parseInt(num)]) { // 터치 돌려서 얻어낸 값이 기존 터치 값보다 큰 경우
                    dp[Integer.parseInt(num)] = dp[res] + 1; // 해당 숫자의 터치값 갱신
                }
            }
            return;
        }

        // 터치할 부분 선택
        for (int i = 0; i < num.length()-1; i++) {
            if((v & (1 << i)) == 0) {
                sel[k] = i+1;
                permutation(k+1, v |= 1 << i, num, sel);
            }
        }
    }

    // 터치한 부분에 따라 숫자를 생성해주는 메소드
    // sel 배열에는 터치된 숫자 사이 중 오른쪽 숫자의 인덱스를 담고 있다.
    // 따라서, 슬라이싱 중 첫번째와 끝부분의 숫자는 직접 슬라이싱 해줘야한다.
    private int makeNum(String num, int[] sel) {
        numList = new ArrayList<>();
        numList.add(Integer.parseInt(num.substring(0, sel[0])));
        if (sel.length > 1) {
            for (int i = 0; i < sel.length-1; i++) {
                numList.add(Integer.parseInt(num.substring(sel[i], sel[i+1])));
            }
        }
        numList.add(Integer.parseInt(num.substring(sel[sel.length-1], num.length())));

        int sum = 1;
        for (int n: numList) {
            sum *= n;
        }
        if (sum < 10) {
            if(dp[Integer.parseInt(num)] == 0) { // 덮어 씌워지는 것 방지 : ex) 1, 0, 8, 9 => 0 => dp[1089] = 1
                dp[Integer.parseInt(num)] = 1; // 곱의 합이 10보다 작은 경우, 터치 수 1로 갱신
            }
            return -1;
        }
        return sum;
    }
}

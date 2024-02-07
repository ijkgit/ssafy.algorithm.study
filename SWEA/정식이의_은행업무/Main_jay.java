import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Solution {
    int[] n, m;
    int ans;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        new Solution().io();
    }
 
    private void io() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int testCase = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= testCase; t++) {
            String s = br.readLine();
            n = new int[s.length()];

            // 입력 시 첫번째자리가 끝자리이므로 배열 인덱스를 반대로 저장
            for (int i = s.length()-1; i >= 0; i--) {
                n[s.length()-1-i] = s.charAt(i) - '0';
            }
             
            s = br.readLine();
            m = new int[s.length()];
            for (int i = s.length()-1; i >= 0; i--) {
                m[s.length()-1-i] = s.charAt(i) - '0';
            }
             
            sol();
             
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
         
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
 
    private void sol() {
        for (int i = 0; i < n.length; i++) {
            n[i] = n[i] == 1 ? 0 : 1; // 2진수 바꾸기
            int binarySum = binaryToNum(); // 바뀐 2진수의 10진수 값
            for (int j = 0; j < m.length; j++) {
                int tmp = m[j]; // 돌아갈 수 저장
                for (int k = 0; k < 3; k++) {
                    if (k != tmp) { 
                        m[j] = k; // 3진수 바꾸기
                        int trinarySum = trinaryToNum(); // 바뀐 3진수의 10진수 값
                        if(binarySum == trinarySum) {
                            ans = binarySum;
                            return;
                        }
                    }
                }
                m[j] = tmp; // 복구
            }
            n[i] = n[i] == 1 ? 0 : 1; // 복구
        }
    }

    // 2진수 -> 10진수
    private int binaryToNum() {
        int sum = n[0];
        for (int i = 1; i < n.length; i++) {
            sum += n[i] * Math.pow(2, i);
        }
        return sum;
    }
    // 3진수 -> 10진수
    private int trinaryToNum() {
        int sum = m[0];
        for (int i = 1; i < m.length; i++) {
            sum += m[i] * Math.pow(3, i);
        }
        return sum;
    }
}

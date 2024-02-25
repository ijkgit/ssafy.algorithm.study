package edu.ssafy.im.SWEA.D4.No5672;

import java.io.*;

public class Solution {
    private int N;
    private char[] arr;
    private StringBuilder sb = new StringBuilder();
    private char[] ans;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new char[N];
            ans = new char[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine().charAt(0);
            }
            sb.append("#").append(t).append(" ");
            sol();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /*
    맨앞과 맨뒤를 비교하면서 줄을 세워준다.
     */
    private void sol() {
        int i = 0, j = N-1;
        while(i < j) {
            if (arr[i] < arr[j]) sb.append(arr[i++]);
            else if (arr[i] > arr[j]) sb.append(arr[j--]);
            else {
                sb.append(arr[i]);
                if(check(i+1, j-1)) i++;
                else j--;
            }
        }
        sb.append(arr[i]).append("\n");
    }

    /*
    맨앞과 맨뒤가 서로 같을 경우, +-1 인덱스의 값을 비교해준다.
    그 안이 계속 같을 수 있으므로, 재귀호출하여 값을 계속 비교해준다.
     */
    private boolean check(int i, int j) {
        if (i >= j) return true;
        if (arr[i] < arr[j]) return true;
        if (arr[i] > arr[j]) return false;
        return check(i+1, j-1);
    }
}

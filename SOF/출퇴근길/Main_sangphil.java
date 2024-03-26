import java.io.*;
import java.util.*;

public class Main_sangphil {
    static int[] dp;
    static int size;
    static int binary_search(int num) {
        int left = 0;
        int right = size;
        int mid;
        while (left < right) {
            mid = (left+right)/2;

            if(dp[mid] < num) {
                left = mid+1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n];
        size = 0;

        for (int x : arr) {
            int k = binary_search(x);
            if (size == k) {
                dp[size] = x;
                size++;
            }
            else {
                dp[k] = x;
            }
        }
        System.out.println(size);
    }
}
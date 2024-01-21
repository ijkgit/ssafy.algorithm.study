import java.util.*;
import java.io.*;

public class Solution_sangphil {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            int left = 0;
            int right = n-1;
            int ans = -1;


            // 배열 정렬해두고, 투포인터
            // m 보다크면 한 단계 작은 세트 구성
            // m 보다 작으면 가장 큰 값을 정답으로 갱신
            while (left < right) {
                int temp = arr[left] + arr[right];

                if (temp == m) {
                    ans = temp;        
                    break;
                } else if (temp > m) {
                    right--;
                } else {
                    left++;
                    ans = Math.max(ans, temp);
                }
            }

            System.out.printf("#%d %d\n", t+1, ans);
        }
        sc.close();
    }
}
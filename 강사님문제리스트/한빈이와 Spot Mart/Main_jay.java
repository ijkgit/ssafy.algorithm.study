import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test_case = Integer.parseInt(br.readLine());

        for (int t = 1; t <= test_case; t++) {
            // 입력 받기
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);

            int n = Integer.parseInt(st.nextToken()); // 과자 봉지의 개수
            int m = Integer.parseInt(st.nextToken()); // 무게 합 제한

            ArrayList<Integer> weight = new ArrayList();

            // 각 과자봉지의 무게 입력
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(st.nextToken());
                if (a < m)
                    weight.add(a);
            }

            // 무게가 적은 순서대로 정렬
            Collections.sort(weight);

            int ans = 0;
            // 무게가 많이 나가는 과자 봉지 선택
            for (int i = weight.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    int sum = weight.get(i) + weight.get(j);
                    if (sum <= m) {
                        if (sum > ans)
                            ans = sum;
                    } else
                        break;
                }
            }

            // 출력
            if (ans == 0)
                ans = -1;

            sb.append("#" + t + " " + ans + "\n");
        }
        System.out.print(sb);
    }
}

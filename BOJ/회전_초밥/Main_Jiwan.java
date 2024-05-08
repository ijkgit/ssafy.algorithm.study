import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        // 초밥의 종류 입력
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 초밥 종류별 먹은 횟수 카운트 배열
        int[] cnt = new int[d + 1];
        int count = 1; // 현재 먹은 초밥의 가짓수
        int max = 1; // 최대로 먹을 수 있는 초밥 가짓수
        cnt[c] = 1; // 쿠폰 초밥은 이미 먹은 것으로 카운트

        // 처음에 연속해서 k개를 먹은 경우 카운트
        for (int i = 0; i < k; i++) {
            if (cnt[arr[i]]++ == 0) { // 해당 종류의 초밥을 처음 먹은 경우 카운트 증가
                count++;
            }
        }

        // 슬라이딩 윈도우를 이용하여 가장 많이 먹을 수 있는 경우 탐색
        for (int i = 0; i + k < n; i++) {
            if (--cnt[arr[i]] == 0) { // 윈도우의 왼쪽 끝 초밥을 먹은 횟수를 줄이고 0이면 카운트 감소
                count--;
            }
            if (cnt[arr[i + k]]++ == 0) { // 윈도우의 오른쪽 끝 초밥을 먹은 횟수를 증가시키고 0이면 카운트 증가
                count++;
            }
            max = Math.max(count, max); // 현재까지의 가짓수와 최대 가짓수를 비교하여 갱신
        }

        // 회전 초밥이므로 마지막에 다시 처음으로 돌아가는 경우를 고려하여 탐색
        for (int i = n - k; i < n; i++) {
            if (--cnt[arr[i]] == 0) { // 윈도우의 왼쪽 끝 초밥을 먹은 횟수를 줄이고 0이면 카운트 감소
                count--;
            }
            if (cnt[arr[i - n + k]]++ == 0) { // 윈도우의 오른쪽 끝 초밥을 먹은 횟수를 증가시키고 0이면 카운트 증가
                count++;
            }
            max = Math.max(count, max); // 현재까지의 가짓수와 최대 가짓수를 비교하여 갱신
        }

        // 결과 출력
        System.out.println(max);
    }
}

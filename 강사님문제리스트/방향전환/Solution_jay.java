package edu.ssafy.im.SWEA.No8382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_jay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            // 시작 좌표로부터 끝 좌표까지 거리 구하기
            int distanceX = Math.abs(startX - endX);
            int distanceY = Math.abs(startY - endY);

            int ans = 0;
            // 해당 거리를 새로운 좌표로 기준을 잡는다.
            if (distanceX > distanceY) {
                // 좌표값 x, y 중 큰 값의 인덱스 * 2가 정답의 기준
                ans = distanceX * 2;
                // x, y 의 홀짝 여부에 따라 -1
                if (distanceX % 2 == 0) {
                    if (distanceY % 2 == 1) ans--;
                }
                else {
                    if (distanceY % 2 == 0) ans--;
                }
            } else {
                ans = distanceY * 2;
                if(distanceY % 2 == 0) {
                    if (distanceX % 2 == 1) ans--;
                }
                else {
                    if (distanceX % 2 == 0) ans--;
                }
            }
            sb.append("#" + t + " " + ans + "\n");
        }
        System.out.print(sb);
    }
}

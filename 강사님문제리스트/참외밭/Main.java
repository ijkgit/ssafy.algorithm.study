package edu.ssafy.im.BOJ.No2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        int[] d = new int[6];
        int[] len = new int[6];

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 6; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            d[i] = Integer.parseInt(st.nextToken());
            len[i] = Integer.parseInt(st.nextToken());

            // max 찾기
            max = Math.max(max, len[i]);
            if (max == len[i]) maxIndex = i;
        }

        // max 를 기준으로 양 옆 대소비교
        int leftIndex, rightIndex = 0;
        if (maxIndex == 0) {
            leftIndex = 5;
            rightIndex = 1;
        } else if (maxIndex == 5) {
            leftIndex = 4;
            rightIndex = 0;
        } else {
            leftIndex = maxIndex - 1;
            rightIndex = maxIndex + 1;
        }

        int area = 0;
        if (len[leftIndex] > len[rightIndex]) {
            // 큰 사각형 넓이 구하기
            area = len[leftIndex] * len[maxIndex];

            // 무조건 max 옆 len 이 낮은 변쪽 index +- 2가 작은 사각형의 width, height
            rightIndex++;
            if (rightIndex > 5) rightIndex = 0;
            int x = len[rightIndex];

            rightIndex++;
            if (rightIndex > 5) rightIndex = 0;
            int y = len[rightIndex];

            area -= x*y;
        } else {
            area = len[rightIndex] * len[maxIndex];

            leftIndex--;
            if (leftIndex < 0) leftIndex = 5;
            int x = len[leftIndex];

            leftIndex--;
            if (leftIndex < 0) leftIndex = 5;
            int y = len[leftIndex];

            area -= x*y;
        }

        int res = area * k;
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        sb.append("\n");

        System.out.print(sb);
    }
}

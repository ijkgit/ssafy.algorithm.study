import java.util.*;
import java.io.*;

/*
* 아이디어: 큰 사각형 - 작은 사각형 = 육각형 넓이
*        작은 사각형의 정보: 6각형의 중앙 좌표로부터 가로, 세로 길이 구해서 곱하기
*/

class Main_sangphil {
    // 좌표를 입력 받을 횟수: 6
    static final int INPUT_COUNT = 6;
	
    public static void main (String[] args) throws IOException {
        // 0. 기본 셋팅
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 1. 입력 받기
        int k = Integer.parseInt(br.readLine());
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        int x = 0;
        int y = 0;
    
        // 2. 시작점에서부터 꼭지점들의 상대적 좌표 저장
        int direction, weight;
        for (int i = 0; i < INPUT_COUNT; i++) {
            st = new StringTokenizer(br.readLine());
            direction = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            switch (direction) {
                case 1:
                    x += weight;
                    break;
                case 2:
                    x -= weight;
                    break;
                case 3:
                    y -= weight;
                    break;
                default:
                    y += weight;
            }

            xPoints[i] = x;
            yPoints[i] = y;
        }
        
        // 3. 6각형 중 찌그러진 부분의 중앙 좌표 추출
        int midIndex = 0;
        int w = 0;
        int h = 0;

        int[] tempX = xPoints.clone();
        int[] tempY = yPoints.clone();
        Arrays.sort(tempX);
        Arrays.sort(tempY);

        int midX = tempX[3];
        int midY = tempY[3];
        
        // 입력받은 6개의 좌표 중에서 찌그러진 좌표의 인덱스
        for (int i = 0; i < INPUT_COUNT; i++) {
            if (xPoints[i] == midX && yPoints[i] == midY) {
                midIndex = i;
            }
        }
        
        // 4. 큰 사각형 - 작은 사각형 = 넓이
        // 중간 좌표에서 가로, 세로 길이 구하기
        for (int i = 0; i < INPUT_COUNT; i++) {
            if (xPoints[i] == midX && yPoints[i] != midY) {
                h = Math.abs(yPoints[midIndex] - yPoints[i]);
            } else if (xPoints[i] != midX && yPoints[i] == midY) {
                w = Math.abs(xPoints[midIndex] - xPoints[i]);
            }
        }
        
        // 5. 큰 사각형 넓이 - 작은 사각형 넓이
        int largeWidth = tempX[5] - tempX[0];
        int largeHeight = tempY[5] - tempY[0];

        System.out.println(k * (largeWidth * largeHeight - w * h));        
    }
}
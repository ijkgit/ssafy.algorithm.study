import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_semin {
    static char[] binArr, tritArr;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        L:
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            binArr = br.readLine().toCharArray();
            tritArr = br.readLine().toCharArray();
            for (int i = 0; i < binArr.length; i++) {
                binArr[i] = binArr[i] == '0' ? '1' : '0'; //2진수 중 1자리만 변경
                long decNum = Long.parseLong(String.valueOf(binArr), 2); //해당 2진수를 10진수로 변경, 2^40이 21억 이상
                int different = 0;
                LinkedList<Character> newTritArr = (LinkedList<Character>) getTritNum(decNum); //해당 10진수를 3진수 char list로 얻기
                if (newTritArr.size() <= tritArr.length) { //자릿수가 큰 거는 아예 불가능한 경우
                    for (int d = 0; d < tritArr.length; d++) {
                        if (tritArr[d] == 0 && newTritArr.size() <= d) { //size<=d란 건 0으로 채워진단 뜻, tritArr[d]==0이면 결국 둘이 일치
                            continue;
                        }
                        if (newTritArr.size() <= d) { //위에서 안걸렸으므로 newTritArr의 d자리는 0, tritArr[d]!=0
                            different++;
                            continue;
                        }
                        if (newTritArr.get(d) != tritArr[d]) {
                            different++;
                        }
                    }
                    if (different == 1) {  //새로 만든 3진수 배열이 입력 3진수 배열과 정확히 하나만 다른지 체크
                        sb.append(decNum).append("\n");
                        continue L; //정답을 찾았으므로 더이상 볼 필요 x
                    }
                }
                binArr[i] = binArr[i] == '0' ? '1' : '0';
            }
        }
        System.out.println(sb);
    }

    private static Deque<Character> getTritNum(long decNum) {
        Deque<Character> dq = new LinkedList<>();
        long temp = decNum;
        while (temp > 0) { //새로 나머지가 생길 때마다 앞에 추가해줘야 함
            dq.offerFirst((char) ('0' + temp % 3)); //0~2에 해당하는 아스키 코드가 char 형으로 변환되는거라서 캐스팅 해줘야 함
            temp /= 3;
        }
        return dq;
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//위상정렬..이 뭐람
public class Main_semin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List[] outGraph = new ArrayList[N + 1]; //간선정보 저장: outGraph[a] 뒤에 b가 와야 한다는 정보
        int[] inGraph = new int[N + 1]; //진입 차수 저장

        for (int i = 1; i < N + 1; i++) {
            outGraph[i] = new ArrayList();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            outGraph[a].add(b);
            inGraph[b] += 1;
        }
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) { //진입차수 0이면 넣기(입력 받지 않은 애들은 어디 들어가도 상관 x)
            if (inGraph[i] == 0) {
                dq.add(i);
            }
        }
        while (!dq.isEmpty()) {
            int n = dq.poll();
            for (int i = 0; i < outGraph[n].size(); i++) {
                int target = (int) outGraph[n].get(i);
                inGraph[target]--; // target을 가리키던 간선 하나 --
                if (inGraph[target] == 0) { //진입차수 0되면 남은 애들 중에서는 순번 가장 낮음
                    dq.add(target);
                }
            }
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}

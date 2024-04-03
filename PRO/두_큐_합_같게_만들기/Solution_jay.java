import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        long s1 = 0, s2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for (int q : queue1) {
            q1.offer(q);
            s1 += q;
        }
        
        for (int q : queue2) {
            q2.offer(q);
            s2 += q;
        }
        
        int cnt = 0;
        while (cnt <= queue1.length * 4) {
            if (s1 > s2) {
                int q = q1.poll();
                q2.offer(q);
                s1 -= q;
                s2 += q;
            } else if (s1 < s2) {
                int q = q2.poll();
                q1.offer(q);
                s1 += q;
                s2 -= q;
            } else if (s1 == s2) {
                return cnt;
            }
            cnt++;
        }
        
        return answer;
    }
}

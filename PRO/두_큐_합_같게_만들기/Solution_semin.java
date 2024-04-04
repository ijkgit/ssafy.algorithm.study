import java.util.*;
import java.io.*;

class Solution {
    
    static Deque<Integer> q1;
    static Deque<Integer> q2;
    static long size;
    
    public int solution(int[] queue1, int[] queue2) {
        q1=new ArrayDeque<>();
        q2=new ArrayDeque<>();
        long right=0;
        long left=0;
        
        for(int i=0;i<queue1.length;i++){
            q1.push(queue1[i]);
            right+=queue1[i];
        }
        for(int i=0;i<queue2.length;i++){
            q2.push(queue2[i]);
            left+=queue2[i];
        }
        
        int cnt=0;
        while(true){
            if(right==left){
                return cnt;
            }
            if(cnt>(q1.size()+q2.size())*2){
                return -1;
            }
            if(right>left && !q1.isEmpty()){
                int num = q1.pollLast();
                right-=num;
                q2.addFirst(num);
                left+=num;
            }
            else if(right<left && !q2.isEmpty()){
                int num = q2.pollLast();
                left-=num;
                q1.addFirst(num);
                right+=num;
            }
            cnt++;
        }
    }
}
import java.util.*;
import java.io.*;

class Solution {
    static class Vertex implements Comparable<Vertex>{
        int to, w;
        Vertex(int to, int w){
            this.to=to;
            this.w=w;
        }
        public int compareTo(Vertex o){
            return Integer.compare(this.w, o.w);
        }
        public String toString(){
            return "("+to+","+w+")";
        }
    }
    
    static List<Vertex>[] adjList;
    static int[] dp;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adjList = new ArrayList[n+1];
        dp=new int[n+1];
        for(int i=0;i<=n;i++){
            adjList[i] = new ArrayList<>();
        }
        for(int[] path : paths){
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            if(checkContain(to,summits) || checkContain(from, gates)){ //from에서 나가는 단방향 조건 확인
                adjList[from].add(new Vertex(to, weight));
            }
            else if(checkContain(to, gates) || checkContain(from,summits)){ //to에서 나가는 단방향 조건 확인
                adjList[to].add(new Vertex(from, weight));
            }
            else{ //나머지는 그냥 양방향
                adjList[from].add(new Vertex(to, weight)); 
                adjList[to].add(new Vertex(from, weight));
            }
        }

        dijkstra(gates);
        Arrays.sort(summits);
        
        //등산코스 산봉우리 번호와 dp[번호] 배열 담아 반환
        int[] answer = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        for(int summit: summits){
            if(answer[1]>dp[summit]){
                answer[0]=summit;
                answer[1]=dp[summit];
            }
        }
        
        return answer;
    }
    
    private static void dijkstra(int[] gates){
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int g: gates){
            pq.add(new Vertex(g, 0));
            dp[g]=0;
        }
        
        while(!pq.isEmpty()){
            Vertex cur = pq.poll();
            if(dp[cur.to] < cur.w){
                continue;
            }
            for(Vertex next: adjList[cur.to]){ //다음 방문지
                int intensity= Math.max(next.w, dp[cur.to]); //다음 방문지까지 거리 vs 현재까지 intensity
                if(dp[next.to]>intensity){ //다음 방문지에 지금까지 저장된 intensity가 더 크면
                    dp[next.to]=intensity; //최신 걸로 업데이트
                    pq.add(new Vertex(next.to,dp[next.to]));
                }
            }
        }
    }
    
    private static boolean checkContain(int num, int[] arr){
        for(int node: arr){
            if(node==num){
                return true;
            }
        }
        return false;
    }

}
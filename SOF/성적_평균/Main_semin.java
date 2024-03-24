import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        double[] memo= new double[N+1];
        st= new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            memo[i]=memo[i-1]+Double.parseDouble(st.nextToken());
        }
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int b=Integer.parseInt(st.nextToken());
            int a=Integer.parseInt(st.nextToken());
            System.out.printf("%.2f\n",(memo[a]-memo[b-1])/(a-b+1));
        }
    }
}

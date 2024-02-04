package work;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 계란으로계란치기 {
	
	static int [][] eggs;
	static int N;
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());//중복조합 문제
		StringTokenizer st;
		
		//내구도 무게
		eggs = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken()); //내구도
			eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
		}
		recursive(0); //0번, 깨진계란의 수
		if(ans == Integer.MIN_VALUE) ans =0;
		System.out.println(ans);
	}
	static void recursive(int idx) {
		//basis part
		//System.out.println(idx);
		int cnt=0; 
		for(int i=0; i<N; i++) {
			if(eggs[i][0]<=0) {
				cnt++;
			}
			
		}
		ans = Math.max(cnt, ans);
		if(idx == N) { 
			//logic
			
			//for(int i=0; i<eggs.length; i++)
				//System.out.println(Arrays.toString(eggs[i]));
			return ;
		}
		
		
		
		
		if(eggs[idx][0] <= 0) {
			recursive(idx+1);
			return;
		}
		
		//inductive part
		
		for(int i=0; i<N; i++) {
			
			if(i == idx)continue; //본인
			if(eggs[i][0]<=0)continue; //깨짐
			
			eggs[idx][0]-= eggs[i][1]; //현재계란으로 계란치기 
			eggs[i][0]-= eggs[idx][1]; 
			
			recursive(idx+1);
			
			eggs[idx][0]+= eggs[i][1]; //현재계란으로 계란치기 
			eggs[i][0]+= eggs[idx][1]; 
						
		}
		
		
	}

}

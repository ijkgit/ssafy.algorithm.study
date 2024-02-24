package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 치킨거리 {
	static class Chicken{
		int r,c;
		boolean toggle;
		//boolean toggle;
		Chicken(int r, int c){
			this.r = r;
			this.c = c;
			
			//this.toggle = toggle;
		}
		
	}
	static int N; // NxN도시 
	static int[][] city; 
	static int M; // 치킨집 개수 M보다 작음
	static ArrayList<Chicken> chickens = new ArrayList<>();
	static ArrayList<Chicken> house = new ArrayList<>();
	static int chickenDist =0;
	static int ans = Integer.MAX_VALUE;
	static int localDistSum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());	
				if(city[i][j] == 2)chickens.add(new Chicken(i,j));
				if(city[i][j] == 1)house.add(new Chicken(i,j));
				
			}
		}
		//System.out.println(house.size());
		recursive(0,0,new boolean[chickens.size()]);
		
		System.out.println(ans);
	}
	private static void recursive(int idx, int k, boolean[] sel) {
		if(idx == chickens.size()) {
			
				
			for(int l=1; l<=M; l++) {
			if(k==l) {
				//System.out.println(Arrays.toString(sel));
				localDistSum = 0;
				for(int i=0; i<house.size(); i++) { //집마다 
					
					int localDist = Integer.MAX_VALUE;
					Chicken h = house.get(i);
					//System.out.println("house : "+h.r+" "+ h.c);
					for(int j=0; j<sel.length; j++) {
						if(sel[j]==false)continue;
						Chicken chick = chickens.get(j); //치킨집까지 거리 구해서
						//System.out.println(j);
						//System.out.println(chick.r +" "+chick.c);
						localDist = Math.min(getDist(h.r, h.c, chick.r, chick.c),localDist);
					}
					localDistSum += localDist; //더하기
					//System.out.println("lds"+localDist);
				}
				
				ans = Math.min(ans, localDistSum);
				//System.out.println("ldS :"+localDistSum);
				}
				
			}
			
			return;
		}
		
		///sel[k] = idx;
		sel[idx] =true;
		recursive(idx+1, k+1, sel);
		sel[idx]=false;
		recursive(idx+1, k, sel);
		
		
	}
	private static int getDist(int r, int c, int r2, int c2) {
		// TODO Auto-generated method stub
		
		return Math.abs(r-r2)+Math.abs(c-c2);
		
	}

}

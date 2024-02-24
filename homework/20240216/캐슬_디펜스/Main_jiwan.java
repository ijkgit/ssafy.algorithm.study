package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class 캐슬디펜스 {
	
	//여러 궁수에게 공격당할 수 있다.
	//턴 종료될때 한번에 지워야함
	//거리공식 나오면 2차원 배열로 해결할 수 없다.
	//동시에 혹은 거리공식 마찬가지
	
	static class Enemy{
		int r,c;
		Enemy(int r, int c){
			this.r =r;
			this.c =c;
		}
		@Override
		public String toString() {
			return "Enemy [r=" + r + ", c=" + c + "]";
		}
	}
	static int N,M,D,Ans = Integer.MIN_VALUE;
	//static int [][] map;
	static ArrayList<Enemy> enemys = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		for(int r =0 ; r<N; r++) {
			for(int c= 0; c<M; c++) {
				if(sc.nextInt()==1)
					enemys.add(new Enemy(r,c));
			}
		}
		//M개 중에 3개 뽑는 조합
		combination(0,0,new int[3]);
		System.out.println(Ans);
		
		
		
	}
	private static void combination(int idx, int k, int[] sel) {
		if(k==sel.length) {
			// 다 골랐어요
			// 원본데이터를 바꾸면 안됌
			// 적군 복사
			ArrayList<Enemy>temp = new ArrayList<>();
			for(int i=0; i<enemys.size(); i++) {
				Enemy e = enemys.get(i);
				temp.add(new Enemy(e.r,e.c));
			}
			// tmp에 적이 다 죽을때까지 반복
			int cnt = 0 ;
			while(temp.size()>0) {
				ArrayList<Enemy> deathNote = new ArrayList<>();
				
				//궁수가 활을 쏩니다.
				for (int i = 0; i < 3; i++) {//궁수 3명
					//i는 궁수자리
					int minDist = Integer.MAX_VALUE;
					int minC = M;
					int minIdx = -1; //몇번째 적?
					for (int j = 0; j < temp.size(); j++){//적들 
						//j는 적
						Enemy en = temp.get(j);
						int dist  = Math.abs(N-en.r)+Math.abs(sel[i]-en.c);
						// 사정권안에 있다면
						if(dist <= D) {
							if(minDist > dist) { //거리가 가까운 놈 잡고
								minDist = dist;
								minC = en.c;
								minIdx = j;
							}else if ( minDist == dist) { // 거리가 같다면 왼쪽 놈 잡고
								if(minC>en.c) {
									minDist = dist;
									minC = en.c;
									minIdx = j;
								}
							}
						}
						
					} // end of for j
					if(minIdx != -1) {
						deathNote.add(temp.get(minIdx));
					}
				} //end of for i
				//temp에서 deathNote에 있는 적들을 다 삭제한다.
				
				for (int i = 0; i < deathNote.size(); i++) {
					for(int j = 0; j<temp.size(); j++) {
						if(deathNote.get(i)==temp.get(j)) {
							temp.remove(j--);
							cnt++;
						}
					}
				}
				//1 보 전진
				for(int i=0; i<temp.size(); i++) {
					temp.get(i).r++;
					if(temp.get(i).r == N) {
						temp.remove(i--);
					}
				}
			}// end of while 
			Ans = Math.max(Ans, cnt);
			return;
		}
		if(idx == M)return; // 더 고를 놈이 없어요
		
		sel[k] = idx;
		combination(idx+1, k+1, sel);
		combination(idx+1, k, sel);
	}
	
}

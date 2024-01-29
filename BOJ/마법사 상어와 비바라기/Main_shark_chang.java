package edu.ssafy.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// d 방향 s칸 이동
// 비내림
// 구름 x
// 복사버그(대각선에 물 있으면 있는만큼 + , 단 경계 넘어가기  x)
// 물양2이상인 곳에 구름 생성 & 물 양 -2 (단 구름이 사라진곳이 아니여야 한다)

//       2     4     6     8
//방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙


class Pair{
	int x,y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Main {
	//N*N , M order
	static int N,M,d,s;
	static int[][] board;
	static int[][] orders;
	//방향                          ←,      ↖,     ↑,      ↗,    →,    ↘,    ↓ ,    ↙
	static int[][] dxy = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	//구름
	static Queue<Pair> cloud = new LinkedList<Pair>();
	//구름 사라진 곳
	//아마 set에 객체를 사용하는데 시간을 많이먹음?ㄴ
	static HashSet<Pair> set = new HashSet<Pair>();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		// 초기 구름
		cloud.add(new Pair(N-1,0));
		cloud.add(new Pair(N-2,0));
		cloud.add(new Pair(N-1,1));
		cloud.add(new Pair(N-2,1));
		for(int order=0; order<M ; order++) {
			d = sc.nextInt();
			s = sc.nextInt();
			set = new HashSet<Pair>();
			int size = cloud.size();
			//이동 및 비내리기
			// 구름개수
			for(int i =0 ; i <size ; i++ ) {
				Pair c =cloud.poll();
				c.x =((c.x + (dxy[d][0]*s)) % N);
				c.y = ((c.y + (dxy[d][1]*s)) % N);
				if (c.x <0) {
					c.x = N + c.x;
				}if(c.y<0) {
					c.y = N + c.y;
				}
				board[c.x][c.y]+= 1;
				//set에 사라질 구름위치 넣기
				set.add(c);
				cloud.add(c);
			}
			// 복사버그
			// 구름개수
			while(!cloud.isEmpty()) {
				Pair c = cloud.poll();
				//대각선만
				int cnt = 0;
				for(int i = 2 ; i <9 ; i +=2) {
					int nx = c.x + dxy[i][0];
					int ny = c.y + dxy[i][1];
					if(0<= nx && nx <N && 0<= ny && ny < N && board[nx][ny] >0) {
						cnt ++;
					}	
				}
				board[c.x][c.y] += cnt;
				
			}
			// 구름생성
			// N*N
			for(int i =0 ; i < N ; i++ ) {
				for (int j = 0; j < N; j++) {
					// 물이 2 이상이고 구름이 있던 곳인지 검사
					if (board[i][j] >= 2 && !set.contains(new Pair(i,j))) {
						board[i][j] += -2;
						cloud.add(new Pair(i,j));
					}
					
				}
			}
		}
		int ans =0;
		for(int i =0 ; i < N ; i++ ) {
			for (int j = 0; j < N; j++) {
				// 물이 2 이상이고 구름이 있던 곳인지 검사
				ans += board[i][j];
				
			}
		}
		
		System.out.println(ans);


		

		
		
		
	}

}

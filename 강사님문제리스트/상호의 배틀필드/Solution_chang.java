package edu.ssafy.chap01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		HashMap<String,Integer> hash = new HashMap<>();
		hash.put("^", 0);
		hash.put("v", 1);
		hash.put("<", 2);
		hash.put(">", 3);
		int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};
		for(int tc =1 ; tc<= T ; tc++) {
			//H높이 W 너비
			int H,W;
			int[] tank = new int[2];
			String[] orders;
			H = sc.nextInt();
			W = sc.nextInt();
			String[][] board = new String[H][];
			for(int i = 0; i <H ;i++) {
				String tmp = sc.next();
				board[i] = tmp.split("");
			}
			for(int i =0 ; i < H ;i++) {
				for(int j = 0 ; j <W ; j++) {
					if(board[i][j].equals("^")
							|| board[i][j].equals("v")
							|| board[i][j].equals("<") 
							|| board[i][j].equals(">")) {
						tank[0] = i;
						tank[1] = j;
					}
				}
			}
			int nx,ny;
			int N = sc.nextInt();
			int x = tank[0];
			int y = tank[1];
			orders = sc.next().split("");
			for(String order : orders) {
				switch(order) {

				case "U" :
					board[x][y] = "^";
					nx = x-1;
					if(0<=nx && nx < H && board[nx][y].equals(".")) {
						board[nx][y] = "^";
						board[x][y] = ".";
						x = nx;
					}
					break;
				case "D" :
					board[x][y] = "v";
					nx = x+1;
					if(0<=nx && nx < H && board[nx][y].equals(".")) {
						board[nx][y] = "v";
						board[x][y] = ".";
						x = nx;
					}
					break;
				case "L" :
					board[x][y] = "<";
					ny = y-1;
					if(0<=ny && ny < W && board[x][ny].equals(".")) {
						board[x][ny] = "<";
						board[x][y] = ".";
						y = ny;
					}
					break;
				case "R" :
					board[x][y] = ">";
					ny = y+1;
					if(0<=ny && ny < W && board[x][ny].equals(".")) {
						board[x][ny] = ">";
						board[x][y] = ".";
						y = ny;
					}
					break;
				case "S" :
					int t = 1;
					while(true) {
						nx = x + (dxy[hash.get(board[x][y])][0] * t);
						ny = y + (dxy[hash.get(board[x][y])][1] *t);
						if(0<=nx && 0<= ny && nx<H && ny <W) {
							t++;
							if(board[nx][ny].equals("*")) {
								board[nx][ny] = ".";
								break;	
							}else if(board[nx][ny].equals("#")) {
								break;
							}
						}else break;
					}
				}
				
			}
			System.out.printf("# %d",tc);
			for(int i =0 ; i < H ;i++) {
				for(int j = 0 ; j <W ; j++) {
					System.out.printf("%s",board[i][j]);
				}
				System.out.println();
			}
			
			
		
		
		}
	}

}

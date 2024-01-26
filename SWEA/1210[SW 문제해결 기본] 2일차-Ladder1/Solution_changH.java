
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	static int ans = 0;
	static int board[][];
	public static int move(int x, int y) {
	    if (x==0)return y;
	    else if (y-1 >= 0 && board[x][y-1]==1) { //왼
	        while (board[x][y-1]==1){
	            y=y-1;
	            if (y-1 ==-1) break;
	            }
	        return move(x-1,y);
	    }else if (y+1 <= 99 && board[x][y+1]==1) { //오
	        while (board[x][y+1]==1) {
	            y = y+1;
	            if (y+1 >= 100) break;
	            }
	        return move(x-1,y);
	    }
	    else return move(x-1,y);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in)	;
		for(int tc = 1; tc <= 10; tc++) {
			int gar = sc.nextInt();
			int tmp;
			board = new int[100][100];
			//입력방법및 탐색방법이 맘에 안드는데 추천해주세요
			for(int i = 0; i <100 ; i++) {
				for(int j = 0 ; j <100; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			int start = 0 ;
			for(int j = 0 ; j <100; j++) {
				if(board[99][j] == 2) {
					start = j;
					break;
				}
					
			}
			/////////////////////////////////////
			ans = move(99,start);
			System.out.println("#"+tc+" " +ans);
			ans = 0;
				
			
			
			
			
			
		
		}

	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	static int N ,M;
	static int arr[][];
	static boolean visit[];
	static int gears[][] = new int[4][8];
	static int head[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int T = Integer.valueOf(tk.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			//입력 및 전처리
			tk = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			gears= new int[4][8];
			
			// N극 0 S극 1
			for (int i = 0; i < 4; i++) {
				tk = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					gears[i][j] = Integer.valueOf(tk.nextToken());
				}
			}
			
			head = new int[4];
			//1 시계, -1 반시계
			for (int i = 0; i < N; i++) {
				tk = new StringTokenizer(br.readLine());
				int num = Integer.valueOf(tk.nextToken());
				int order= Integer.valueOf(tk.nextToken());
				spin(num-1,order);
//				System.out.println(Arrays.toString(head));

			}
			
			System.out.println("#"+ tc + " " + (gears[0][head[0]] *1 + gears[1][head[1]] *2 +gears[2][head[2]] *4 + gears[3][head[3]] *8 ) );
		}
	}

	private static void spin(int num, int order) {
		
		int orders[] = new int[4];
		orders[num] = order;
		//num 기준 오른쪽 톱니
		int right = gears[num][ (head[num] +2)%8 ];
		for (int i = num+1; i < 4; i++) {
//			System.out.println(i);
			int tmp = head[i] - 2;
			if(tmp <0)tmp = 8 +tmp;
//			System.out.println(right + " -------- " + gears[i][tmp]);
			if(right != gears[i][tmp]) {
				if(orders[i-1] == 1)orders[i] = -1;
				else orders[i] = 1;
				right = gears[i][(head[i] + 2) %8] ;
			}else break;
		}
		//num 기준 왼쪽 톱니
		int 음수처리 =  head[num]-2;
		if(음수처리 <0)음수처리 = 8 +음수처리;
		int left = gears[num][ 음수처리 ];
//		System.out.println(num + " " +gears[num][ 음수처리 ]);
//		System.out.println(Arrays.toString(gears[num]) );
		for (int i = num-1; i >=0; i--) {
			int tmp = (head[i] +2) %8 ;
			if(left != gears[i][tmp]) {
				if(orders[i+1] == 1)orders[i] = -1;
				else orders[i] = 1;
				int 레프트음수처리 = (head[i] - 2);
				if(레프트음수처리 <0)레프트음수처리 = 8+레프트음수처리;
				left = gears[i][ 레프트음수처리 ];
			}else break;
		}
		//명령별로 회전
		//1시계 -1 반시계 0무회전
		for (int i = 0; i < 4; i++) {
			if(orders[i] == 1)head[i] = (head[i]-1)<0 ? 8 +(head[i]-1):head[i]-1;
			else if(orders[i] == -1)head[i] = (head[i]+1)%8;  
		}
//		System.out.println(Arrays.toString(orders));
	}
}




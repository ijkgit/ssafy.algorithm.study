package test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class Solution1 {


	// #(xy)  >> xy에 할당된 값
	// &(p) >> p가 할단된 점
	// (xy)+(zw) >>(x+z, y+w)
	// p*q >> #(&(p)+&(q))
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in)	;
		int t = sc.nextInt();
		int [][] board = new int[400][400];
		int x_num = 2;
		int y_num = 1;
		int tmp = 1;
		int sum = 1;
		for(int i =1; i <300;i++) {
			y_num = i;
			for(int j =1 ; j <300 ; j++) {
				board[i][j] = sum;
				sum += y_num;
				y_num ++;
			}
			tmp = tmp + x_num;
			sum = tmp;
			x_num ++;

		}
		
		for(int tc = 1; tc <= t; tc++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int tp = 0;
			int [] arrA = new int[2];
			int [] arrB = new int[2];
			out :
			for(int i=1; i<300 ; i++) {
				for(int j=1 ; j<300;j++) {
					if(board[i][j] == A) {
						arrA[0] = i;
						arrA[1] = j;
						tp++;
					}else if(board[i][j] == B) {
						arrB[0] = i;
						arrB[1] = j;
						tp++;
					}
					if(tp == 2) {
						break out;
					}
				}
			}
			arrA[0] += arrB[0];
			arrA[1] += arrB[1];
			System.out.println("#"+tc +" " +board[arrA[0]][arrA[1]]);

		}
		

	}

}

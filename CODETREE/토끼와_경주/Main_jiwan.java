import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Rabbit implements Comparable<Rabbit>{
		int r,c,id,hopCnt,d; //열 행 고유번호, 점수, 뛴횟수, 뛸 거리
		long score;
		boolean chosen;//한번이라도 선택된 경우 true
		public Rabbit(int id, int d, int r, int c, int hopCnt) {
			this.id = id;
			this.d = d;
			this.r = r;
			this.c = c;
			this.hopCnt = hopCnt;
			
			// TODO Auto-generated constructor stub
		}
	
		
		public void addHopCnt() {
			this.hopCnt ++;
		}
		
		@Override
		public String toString() {
			return "Rabbit [r=" + r + ", c=" + c + ", id=" + id + ", score=" + score + ", hopCnt=" + hopCnt + ", d=" + d
					+ ", chosen=" + chosen + "]";
		}

		@Override
		public int compareTo(Rabbit o) {
			//홉카운트 적은 토끼 1순위
			if(this.hopCnt != o.hopCnt) //홉카운트 다른 경우
				return this.hopCnt - o.hopCnt;
			else {//같은경우
				if(this.r+this.c != o.r+o.c) { //r+c다른 경우
					return (this.r+this.c) - (o.r+o.c);
				}
				else {//같은 경우
					if(this.r != o.r) {
						return this.r - o.r;
						
					}
					else {
						if(this.c != o.c) {
							return this.c - o.c;
							
						}
						else {
							return this.id - o.id;
						}
					}
				}
			}
		}
		
	}
	static class Coord implements Comparable<Coord>{
		int r,c; 
		Coord(int r, int c){
			this.r =r ;
			this.c =c ;
			
		}
		
		@Override
		public String toString() {
			return "Coord [r=" + r + ", c=" + c + "]"+(r+c);
		}

		@Override
		public int compareTo(Coord o) {
			if((this.r+this.c)!=(o.r+o.c)) {
				return (o.r+o.c)-(this.r+this.c)  ;
				
			}else {
				if(this.r != o.r) {
					return o.r-this.r;
					
				}
				else {
					return o.c- this.c;
				}
			}
			
		}
	}
	static int Q; 
	static long maxScore = Integer.MIN_VALUE; 
	static int[][] map;
	static int N,M;
	static int P; // 토끼의 수 
	static StringTokenizer st;
	static PriorityQueue<Rabbit> pq = new PriorityQueue<>();
	static class MyComparator implements Comparator<Rabbit>{

		@Override
		public int compare(Rabbit o1, Rabbit o2) {
			if((o1.r+o1.c)!=(o2.r+o2.c)) {
				return (o2.r+o2.c)-(o1.r+o1.c) ;
			}else {
				
				if(o1.r != o2.r) {
					return o2.r-o1.r;
				}else {
					if(o1.c == o2.c) {
						return o2.c-o1.c;
					}
					else {
						return o2.id-o2.id;
					}
				}
			}
			//return 0;
		}
		
	}
	//static ArrayList<Rabbit> rabbits = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("100")) {
				setRabbits();
			}else if(order.equals("200")) {
				moveRabbit();
			}else if(order.equals("300")) {
				changeD();
			}else if(order.equals("400")) {
				break;
			}
		}
		//모든 토끼에 들어있는 점수중 최댓값 출력
		//System.out.println(pq.size());
		while(!pq.isEmpty()) {
			Rabbit r= pq.poll();
			if(r.chosen)
				maxScore = Math.max(maxScore, r.score);
		}
		System.out.println(maxScore); 
	}
	
	private static void changeD() {
		int id = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		LinkedList<Rabbit> temp = new LinkedList<>();
		while(!pq.isEmpty()) {
			Rabbit r = pq.poll();
			temp.add(r);
			if(r.id == id ) {
				r.d *= L;
			}
		}
		
		while(!temp.isEmpty()) {
			
			pq.add(temp.poll());
		}
		
		
	}

	static int[] dr = {0,-1,0,1}; //좌 상 우 하
	static int[] dc = {-1,0,1,0};
	
	private static void moveRabbit() {
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		while(K-- > 0) {
			Rabbit r = pq.poll();
			
//			System.out.println((idx-K)+"번쨰 뽑힌 토끼 : \n"+r);
			r.hopCnt +=1;
			r.chosen = true;
			
			

			PriorityQueue<Coord> coords = new PriorityQueue<>();

		
			for (int dir = 0; dir < 4; dir++) {
				int nr = r.r + r.d * dr[dir];
				int nc = r.c + r.d * dc[dir];
				nr = (nr + (N * 2 - 2)) % (N * 2 - 2);
				nc = (nc + (M * 2 - 2)) % (M * 2 - 2);
				if (nr >= N) {
					nr = N * 2 - 2 - nr;
				}
				if (nc >= M) {
					nc = M * 2 - 2 - nc;
				}
				coords.add(new Coord(nr,nc));
			}
			Coord res = coords.poll();
			
			r.r = res.r;
			r.c = res.c;

			
			ArrayDeque<Rabbit> temp = new ArrayDeque<>();
			
			
			while(!pq.isEmpty()) {
				Rabbit r2 = pq.poll();
				temp.add(r2);
				r2.score += res.r+res.c+2;
			}
			
			
			while(!temp.isEmpty()) {
				pq.add(temp.poll());	
			}
			
			pq.add(r);
			
		
			
			
		}
		
		PriorityQueue<Rabbit> pq3 =  new PriorityQueue<>(new MyComparator());
		while(!pq.isEmpty()) {
			pq3.add(pq.poll());
		}
		Rabbit r3 = pq3.poll();
		r3.score += S;
		pq3.add(r3);
		while(!pq3.isEmpty()) {
			pq.add(pq3.poll());
		}

		
	}
	
	private static void setRabbits() {
		//토끼들과 격자판을 초기화하는 함수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//System.out.println("M : "+M);
		//map = new int[N][M];
		P = Integer.parseInt(st.nextToken());
		
		
		for(int i=0; i<P; i++) {
			int id = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.add(new Rabbit(id, d, 0,0,0));
		}

		
	}

}

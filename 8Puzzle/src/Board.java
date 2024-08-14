import java.util.List;

public class Board  {
	
	private final int SIZE = 3;
	
	private int[][] board = new int[SIZE][SIZE];
	private int zeroI;
	private int zeroJ;
	private String myString;
	
	//c-tor
	public Board(String s) {
		final int RADIX = 10;
		this.myString =s;
		board[0][0] = Integer.parseInt(s, 0, 1, RADIX);
		board[0][1] = Integer.parseInt(s, 1, 2, RADIX);
		board[0][2] = Integer.parseInt(s, 2, 3, RADIX);
		board[1][0] = Integer.parseInt(s, 3, 4, RADIX);
		board[1][1] = Integer.parseInt(s, 4, 5, RADIX);
		board[1][2] = Integer.parseInt(s, 5, 6, RADIX);
		board[2][0] = Integer.parseInt(s, 6, 7, RADIX);
		board[2][1] = Integer.parseInt(s, 7, 8, RADIX);
		board[2][2] = Integer.parseInt(s, 8, 9, RADIX);
	}
	

	public String getString() {
		return this.myString;
	}
	
	public int getBoard(int a,int b){
		return this.board[a][b];
	}
	
	public void GetZero() {
		for (int i = 0 ; i<3; i++) {
			for (int j = 0 ; j<3; j++) {
				if (this.board[i][j] == 0) {
					this.zeroI = i;
					this.zeroJ = j;
				}
			}
		}
	}
	
	public int getZeroI() {
		return this.zeroI;
	}
	
	public int getZeroJ() {
		return this.zeroJ;
	}
	
	public void alternate(int i, int j,int x,int y) {
		int temp = this.board[i][j];
		this.board[i][j] = this.board[x][y];
		this.board[x][y] = temp;
		char [] c = this.myString.toCharArray();
		char r = c[i*3 + j];
		c[(i*3 + j)] = c[x*3 + y];
		c[x*3 + y] = r;
		this.myString = String.valueOf(c);
	}
	
	
	public boolean checkIfIn(List<Board> b) {
		for (Board a : b) {
			if(this.myString.equals(a.myString)) {
				return false;
			    }
		     }
		return true;
		}

		
	//print board
	public void print() {
		System.out.println(board[0][0] + " " + board[0][1] + " " + board[0][2]);
		System.out.println(board[1][0] + " " + board[1][1] + " " + board[1][2]);
		System.out.println(board[2][0] + " " + board[2][1] + " " + board[2][2]);
		System.out.println();
	}
}

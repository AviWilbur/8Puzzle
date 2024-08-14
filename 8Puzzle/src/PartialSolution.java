import java.util.ArrayList;
import java.util.List;

public class PartialSolution implements Comparable<PartialSolution> {
	
	String heuristics;
	private int manhattan,misplaced,ucs,g = 0;

	//a partial solution is a legal series of successive boards
	private List<Board> partialSolution = new ArrayList<Board>();
	
	//c-tor
	public PartialSolution(String H) {
		this.heuristics = H;
	}
	
	public void addPs(Board b) {
		g++;
		this.partialSolution.add(b);
		if(this.heuristics.equals("manhattan")) {
			this.manhattan += h_Manhattan(b) + g;
		}
		if(this.heuristics.equals("misplaced")) {
			this.misplaced = h_Misplaced(b) + g;
		}
		if(this.heuristics.equals("ucs")) {
			this.ucs += 1 + g;
		}
	}
	
	public int getM(String h) {
		if( h == "manhattan") {
			return this.manhattan;
		}
		if(h == "misplaced") {
			return this.misplaced;
		}
		if( h == "ucs") {
			return this.ucs;
		}
		return -1;
	}
	
	public void setM(String h,int m) {
		if(h == "manhattan") {
			this.manhattan = m;
		}
		if(h == "misplaced") {
			this.misplaced = m;
		}
		if(h == "ucs") {
			this.ucs = m;
		}
		
	}
	
	//print path
	public void print() {
		for (Board b: partialSolution) {
			b.print();
		}
	}


	@Override
	public int compareTo(PartialSolution p) {
		if(this.heuristics.equals("ucs")) {
			return p.ucs <= this.ucs ? 1 : -1;
		}
		if(this.heuristics.equals("manhattan")) {
			return p.manhattan < this.manhattan ? 1 : -1;
		}
		if(this.heuristics.equals("misplaced")) {
				return p.misplaced < this.misplaced ? 1 : -1;
		}
		return -1;
	}
	
	//Manhattan distance
	public int h_Manhattan(Board b) {
		int manhattanD =0;
		int result = 0;
		for (int i=0 ; i<3 ; i++) {
			for (int j=0 ; j<3 ; j++) {
				result = Math.abs(b.getBoard(i, j)/3 - i) + Math.abs(b.getBoard(i, j)%3 - j);
				manhattanD = manhattanD + result;
			}
		}
		return manhattanD;
	}

	
	//misplaced tiles
	public static int h_Misplaced(Board b) {
		int misplaceCounter = 0;
		int k = 0;
		for (int i = 0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if(b.getBoard(i, j) != k) {
					misplaceCounter++;
				}
				k++;
			}
		}
		return misplaceCounter;
	}
	
	
	public List<Board> getList(){
		return this.partialSolution;
	}
}

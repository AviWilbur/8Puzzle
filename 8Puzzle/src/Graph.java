import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
		private Map<Board, List<Board>> graph;
		
		public Graph() {
			Map<Board, List<Board>> g = new HashMap();
			this.graph = g;
		}
		
		public Map<Board, List<Board>> getGraph() {
			return this.graph;
		}
		
		
		public void addBoard(Board b) {
			graph.putIfAbsent(b, new ArrayList<>());
		}

		
		public List<Board> getEdges(Board b) {
			return this.graph.get(b);
		}

		
		public void addEdge(Board a,Board b) {
			graph.get(a).add(b);
		}
}
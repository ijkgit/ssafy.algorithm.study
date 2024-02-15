import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*Array List*/
public class AdjList1 {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" +
                    "to=" + to +
                    ", weight=" + weight +
                    ')';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Node>[] graph = new ArrayList[V];
        for (int edge = 0; edge < E; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (graph[from] == null) {
                graph[from] = new ArrayList();
            }
            graph[from].add(new Node(to, weight));
        }
        printGraph(graph);
    }

    private static void printGraph(List[] graph) {
        for (int v = 0; v < graph.length; v++) {
            System.out.println("V[" + v + "] : " + graph[v]);
        }
    }
}

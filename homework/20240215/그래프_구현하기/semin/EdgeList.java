import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EdgeList {
    static class Node {
        int from;
        int weight;
        int to;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" +
                    "from=" + from +
                    ", weight=" + weight +
                    ", to=" + to +
                    ')';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Node[] edgeGraph = new Node[E];
        for (int edge = 0; edge < E; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeGraph[edge] = new Node(from, to, weight);
        }
        printGraph(edgeGraph);
    }

    private static void printGraph(Node[] graph) {
        for (int edge = 0; edge < graph.length; edge++) {
            System.out.println("E[" + edge + "] : " + graph[edge]);
        }
    }
}



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class MinimumHeightTree {
    public static class Node implements Comparable<Node> {
        public final Integer name;
        public final Set<Integer> neighbours;

        public Node(int name) {
            this.name = name;
            neighbours = new HashSet();
        }

        public int compareTo(Node n) {
            int result = this.neighbours.size() - n.neighbours.size();
            if (result == 0) {
                return this.name - n.name;
            }
            return result;
        }
    }


    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        PriorityQueue<Node> pq = new PriorityQueue();
        final Map<Integer, Node> graph = new HashMap<>();
        final Map<Integer, Integer> edgeCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new Node(i));
            edgeCount.put(i, 0);
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).neighbours.add(edges[i][1]);
            graph.get(edges[i][1]).neighbours.add(edges[i][0]);
        }

        for (int i = 0; i < n; i++) {
            pq.add(graph.get(Integer.valueOf(i)));
        }

        while (graph.keySet().size() > 2) {
            List<Node> outerNodes = new ArrayList();
            while (pq.peek().neighbours.size() == 1) {
                outerNodes.add(pq.poll());
            }
            for (Node node : outerNodes) {
                List<Integer> neighboursLst = new ArrayList(node.neighbours);
                Node adj = graph.get(neighboursLst.get(0));
                pq.remove(adj);
                adj.neighbours.remove(node.name);
                pq.add(adj);
                graph.remove(node.name);
            }
        }
        return new ArrayList<>(graph.keySet());
    }

    public static void main(String[] args) {
        System.out.println(findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
        System.out.println(findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
    }
}

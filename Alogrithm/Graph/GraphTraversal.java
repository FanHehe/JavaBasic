package Alogrithm.Graph;

import java.uitl.Queue;
import java.util.HashSet;
import java.util.LinkedList;

import static java.lang.System.out;

public class GraphTraversal {

    // https://www.cnblogs.com/toSeeMyDream/p/5775382.html

    public static void print(Object o) {
        out.print(o);
    }

    public static void DFS(GraphNode[] table) {

        int size = table.length;

        boolean isVisited = new boolean[size];

        for (int i = 0, length = size; i < length; i++) {
            if (!isVisited[i]) {
                innerDFS(table, isVisited, i);
            }
        }
    }

    public static void innerDFS(GraphNode[] table, boolean isVisited, int i) {
        isVisited[i] = true;

        print(table[i].name);

        int w = getFirstNeighbor(i);
    }

    public static void BFS(GraphNode[] table) {
        if (table == null) {
            return;
        }

        GraphNode start = table[0];
        Queue<GraphNode> queue = new LinkedList<>();

        queue.add(start);

        HashSet<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            GraphNode node = queue.poll();

            print(node.name);

            visited.add(node.name);

            while(node.next != null) {

                if (!visited.contains(node.next.name)) {
                    queue.add(node.next);
                }

                node = node.next;
            }
        }
    }

    public static void Topology(GraphNode[] table) {}

    public static void main(String[] args) {

    }
}

package Alogrithm.Graph;

import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

import static java.lang.System.out;

public class GraphTraversal {

    public static final int INF = -1;

    // https://www.cnblogs.com/toSeeMyDream/p/5775382.html

    public static void print(Object o) {
        out.print(o);
    }

    public static void DFS(GraphNode[] table) {

        int size = table.length;

        boolean[] isVisited = new boolean[size];

        innerDFS(table, isVisited, 0);
    }

    public static void innerDFS(GraphNode[] table, boolean[] isVisited, int v) {

        isVisited[v] = true;

        print(table[v].name);

        GraphNode list = table[v];

        while(list.next != null) {

            int idx = -1;

            for (int i = 0; i < table.length; i++) {
                if (table[i].name.equals(list.next.name)) {
                    idx = i;
                    break;
                }
            }

            if (!isVisited[idx]) {
                innerDFS(table, isVisited, idx);
            }

            list = list.next;
        }
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


    public static void Topology(int[][] graph) {
        // 基于dfs
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        TopologyR(graph, visited, 0, queue);
    }

    public static void TopologyR(int[][] graph, boolean[] visited, int v, Queue<Integer> queue) {

        visited[v] = true;

        queue.offer(v);

        for(int i = 0, size = graph.length; i < size; i++) {
            if (!visited[i]) {
                if (graph[v][i] != INF) {
                    TopologyR(graph, visited, i, queue);
                }
            }
        }
    }

    public static boolean Topology2(int[][] graph) {

        // 选择一个入度为0的顶点，输出
        // 将该顶点其出边全部删除，同时更新出边所到达顶点的入度，判断是否有环

        int size = graph.length;
        int[] inDegree = new int[size];
        boolean[] visited = new boolean[size];
        Queue<Integer> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (graph[j][i] != INF) {
                    inDegree[i] ++; // 计算每个节点的入度
                }
            }
        }

        for (int v = 0; v < size; v++) {

            int idx = INF;

            for (int i = 0; i < size; i++) {
                if (!visited[i] && inDegree[i] == 0) {
                    idx = i;
                    break;
                }
            }

            if (idx != INF) {
                result.offer(idx);
                for (int k = 0; k < size; k++) {
                    if (graph[idx][k] != INF) {
                        inDegree[k]--;
                    }
                }
            }
        }

        return result.size() == size;
    }

    public static void main(String[] args) {

    }
}

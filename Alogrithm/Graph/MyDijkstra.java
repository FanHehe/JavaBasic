package Alogrithm.Graph;

public class MyDijkstra {

    public static final int INF = -1;

    public static int[] handleDijkstra(int[][] graph, int from) {
        // https://www.cnblogs.com/biyeymyhjob/archive/2012/07/31/2615833.html
        // 单源最短路径算法，图中不存在负权边。
        // 问题描述：在无向图 G=(V,E) 中，假设每条边 E[i] 的长度为 w[i]，找到由顶点 V0 到其余各点的最短路径。（单源最短路径）

        int size = graph.length;

        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            visited[i] = false; // 均未访问过
            dist[i] = graph[from][i]; // 不可达

            if (dist[i] == INF) {
                prev[i] = INF;
            } else {
                prev[i] = from;
            }
        }

        dist[from] = 0;
        visited[from] = true;

        for (int i = 1; i < size; i++) {
            int min = INF;
            int idx = from;

            for (int j = 0; j < size; j++) {

                if (visited[j] || dist[j] == INF) {
                    continue;
                }

                if (min == INF || min > dist[j]) {
                    idx = j;
                    min = dist[j];
                }
            }

            visited[idx] = true;

            for (int k = 0; k < size; k++) {
                if (visited[k] || graph[idx][k] == INF) {
                    continue;
                }

                if (dist[k] > dist[idx] + graph[idx][k]) {
                    dist[k] = dist[idx] + graph[idx][k];
                    prev[k] = idx;
                }
            }
        }

        return dist;
    }
}

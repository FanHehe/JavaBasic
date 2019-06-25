package Alogrithm.Graph;

public class MyFloyd {
    public static int[][] handleFloyd(int[][] graph) {
        // https://www.cnblogs.com/skywang12345/p/3711532.html
        // https://www.cnblogs.com/biyeymyhjob/archive/2012/07/31/2615833.html

        int size = graph.length;
        int[][] path = new int[size][size];
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                result[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

        return result;
    }
}

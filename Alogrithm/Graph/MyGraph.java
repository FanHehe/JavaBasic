package Alogrithm.Graph;

public class MyGraph {

    public static char[] makeVexs() {
        return new char[] { 'A', 'B', 'C', 'D', 'E', 'F' };
    }

    public static char[][] makeEdges() {
        /**
         * A - B - F
         * | \   / |
         * |  \/   |
         * C - D - E
         */
        return new char[][] {
            { 'A', 'B' },
            { 'A', 'C' },
            { 'A', 'D' },
            { 'B', 'A' },
            { 'B', 'F' },
            { 'C', 'A' },
            { 'C', 'D' },
            { 'D', 'A' },
            { 'D', 'C' },
            { 'D', 'E' },
            { 'D', 'F' },
            { 'E', 'D' },
            { 'E', 'F' },
            { 'F', 'B' },
            { 'F', 'D' },
            { 'F', 'E' }
        };
    }

    public static GraphNode[] makeGraphTable(char[] vexs, char[][] edges) {
        // 邻接表
        int size = vexs.length;
        int length = edges.length;

        GraphNode[] table = new GraphNode[size];

        for (int i = 0; i < size; i++) {
            table[i] = new GraphNode(vexs[i]);
        }

        for (int i = 0; i < length; i++) {

            char to = edges[i][1];
            char from = edges[i][0];

            int iTo = getPosition(to, vexs);
            int iFrom = getPosition(from, vexs);

            GraphNode first = new GraphNode(from);
            first.next = table[iFrom].next;
            table[iFrom].next = first;

            GraphNode second = new GraphNode(to);
            second.next = table[iTo].next;
            table[iTo].next = second;
        }

        return table;
    }

    public static int getPosition(char value, char[] vexs) {
        for (int i = 0, size = vexs.length; i < size; i++) {
            if (vexs[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[][] makeGraphMatrix(char[] vexs, char[][] edges) {
        // 邻接矩阵
        int size = vexs.length;

        boolean[][] table = new boolean[size][size];

        for(int i = 0, length = edges.length; i < length; i++) {
            char from = edges[i][0];
            char to = edges[i][1];

            int iTo = getPosition(to, vexs);
            int iFrom = getPosition(from, vexs);

            table[iTo][iFrom] = table[iFrom][iTo] = true;
        }

        return table;
    }

    public static void main(String[] args) {

        char[] vexs = makeVexs();
        char[][] edges = makeEdges();

        makeGraphTable(vexs, edges);
        makeGraphMatrix(vexs, edges);
    }
}

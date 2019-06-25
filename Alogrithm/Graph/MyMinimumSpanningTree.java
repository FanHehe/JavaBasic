package Alogrithm.Graph;

import java.util.HashSet;

public class MyMinimumSpanningTree {

    public static final int SIZE = 5;

    private static char[] makeVexs() {
        return makeVexs(SIZE);
    }

    private static int[][] makeMatrix() {
        return makeMatrix(SIZE);
    }

    private static char[] makeVexs(int size) {
        char start = 'A';

        char[] result = new char[size];

        for (char i = 0; i < size; i++) {
            result[i] = 'A' + i;
        }

        return result;
    }

    private static int[][] makeMatrix(int size) {
        int[][] matrix = new int[][];

        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = Math.round(Math.random());
            }
        }

        return matrix;
    }


    public static void handlePrim() {

        // 输入：一个加权连通图，其中顶点集合为V1，边集合为E1；
        // 初始化：V2 = {x}，其中x为集合V中的任一节点（起始点），E2 = {},为空；
        // 重复下列操作，直到V2 = V1：
        //  - 在集合E1中选取权值最小的边<u, v>，其中u为集合V2中的元素，而v不在V2集合当中，并且v∈V1
        //  - 将v加入集合V2中，将<u, v>边加入集合E2中；

        char vexs = makeVexs();
        int[][] matrix = makeMatrix();

        char vexNew = new char[vexs.length];
        int[][] tree = new int[vexs.length][vexs.length];



    }

    public static void handleKruskal() {
        // 记Graph中有v个顶点，e个边
        // 新建图Graphnew，Graphnew中拥有原图中相同的e个顶点，但没有边
        // 将原图Graph中所有e个边按权值从小到大排序
        // 循环：从权值最小的边开始遍历每条边 直至图Graph中所有的节点都在同一个连通分量中
        //   if 这条边连接的两个节点于图Graphnew中不在同一个连通分量中
        //       添加这条边到图Graphnew中

    }
}

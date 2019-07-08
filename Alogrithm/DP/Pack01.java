package Alogrithm.DP;

import Util.Common;

// https://juejin.im/post/5b40c99ee51d45190b615f33
public class Pack01 {

    public static int v1(int[] weight, int[] value, int C) {

        int c_size = C + 1;
        int w_size = weight.length;

        int[][] result = new int[w_size][c_size];

        for (int i = 0; i < w_size; i++) {
            result[i][0] = 0;
        }

        for (int i = 0; i < c_size; i++) {

            if (i >= weight[0]) {
                result[0][i] = weight[0];
            } else {
                result[0][i] = 0;
            }
        }

        for (int i = 1; i < w_size; i++) { // item
            for (int j = 1; j < c_size; j++) { // 背包容量
                result[i][j] = result[i - 1][j];

                if (weight[i] <= j) {
                    result[i][j] = Math.max(result[i - 1][j - weight[i]] + value[i], result[i - 1][j]);
                }
            }
        }

        return result[w_size - 1][C];
    }

    public static int v2(int[] weight, int[] value, int C) {

        int c_size = C + 1;

        int[][] result = new int[2][c_size];

        result[0][0] = 0;
        result[1][0] = 0;

        for (int i = 0; i < c_size; i++) {
            if (i > weight[0]) {
                result[0][i] = value[0];
            } else {
                result[0][i] = 0;
            }
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j < c_size; j++) {
                result[i % 2][j] = result[(i + 1) % 2][j];

                if (j >= weight[i]) {
                    result[i % 2][j] = Math.max(result[(i + 1) % 2][j - weight[i]] + value[i], result[i % 2][j]);
                }
            }
        }

        return result[(weight.length + 1) % 2][C];
    }

    public static int v3(int[] weight, int[] value, int C) {
        int c_size = C + 1;
        int w_size = weight.length;

        int[] result = new int[c_size];

        for (int i = 0; i < c_size; i++) {
            result[i] = i > weight[0] ? value[0] : 0;
        }

        for (int i = 1; i < w_size; i++) {
            for (int j = C; j >= 0; j--) {
                if (j >= weight[i]) {
                    result[j] = Math.max(result[j], result[j - weight[i]] + value[i]);
                }
            }
        }

        return result[C];
    }

    public static void main(String[] args) {
        Common.println(v1(new int[] {1, 2, 3}, new int[] {6, 10, 12}, 5));
        Common.println(v2(new int[] {1, 2, 3}, new int[] {6, 10, 12}, 5));
        Common.println(v3(new int[] {1, 2, 3}, new int[] {6, 10, 12}, 5));
    }
}

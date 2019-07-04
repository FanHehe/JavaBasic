package Alogrithm.String;

import Util.Common;
import java.util.LinkedList;


public class Matching {

    public static int BF(String target, String pattern) {

        if (target == null || pattern == null) {
            return -1;
        }

        for (int i = 0, t_size = target.length(), p_size = pattern.length(), offset = t_size - p_size; i <= offset; i++) {

            int j = 0;

            for (; j < p_size; j++) {
                if (target.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            if (j < p_size) {
                continue;
            } else {
                return i;
            }
        }

        return -1;
    }

    public static int[] KMP_Next(String pattern) {

        if (pattern == null) {
            return new int[0];
        }

        int j = 2;
        int size = pattern.length();
        int[] next = new int[size];
        next[0] = -1;

        while (j < size) {
            if (pattern.charAt(next[j - 1]) == pattern.charAt(j - 1)) {
                next[j] = next[j - 1] + 1;
            } else if (pattern.charAt(0) == pattern.charAt(j - 1)) {
                next[j] = 1;
            } else {
                next[j] = 0;
            }
            j++;
        }

        return next;
    }

    public static int KMP(String target, String pattern) {
        if (target == null || pattern == null) {
            return -1;
        }

        int t_size = target.length();
        int p_size = pattern.length();

        if (t_size < p_size) {
            return -1;
        }

        int i = 0, j = 0;
        int[] next = KMP_Next(pattern);

        while (i < t_size && j < p_size) {
            if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == p_size) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {

        LinkedList<String[]> list = new LinkedList<>();

        list.add(new String[] { "dbcabdabdaba", "db" }); // 0
        list.add(new String[] { "dbcabdabdaba", "ba" }); // 10
        list.add(new String[] { "dbcabdabdaba", "cab" }); // 2
        list.add(new String[] { "dbcabdabdaba", "abdaba" }); // 6
        list.add(new String[] { "dbcabdabdaba", "dbcabdabdaba" }); // 0

        for (String[] item: list) {
            Common.println("BF:\t" + BF(item[0], item[1]));
            Common.println("KMP:\t" + KMP(item[0], item[1]));
        }
    }
}

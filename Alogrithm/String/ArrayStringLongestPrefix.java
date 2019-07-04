// Leetcode: 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
package Alogrithm.String;

import Util.Common;
import java.util.Arrays;


public class ArrayStringLongestPrefix {

    public static String getLongestPrefix(String[] list) {

        if (list == null || list.length == 0) {
            return null;
        }

        if (list.length == 1) {
            return list[0];
        }

        Arrays.sort(list);

        String first = list[0];
        String second = list[list.length - 1];

        for (int i = 0; i < first.length() && i < second.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return first.substring(0, i);
            }
        }

        return first.substring(0, Math.min(first.length(), second.length()));
    }

    public static void main(String[] args) {
        String[][] list = new String[][] {
            { "abc", "ab", "a" },
            { "acccc", "ac", "ace", "acd" },
            { "affffddss", "b", "c" }
        };

        for (String[] item: list) {
            Common.println(getLongestPrefix(item));
        }
    }
}

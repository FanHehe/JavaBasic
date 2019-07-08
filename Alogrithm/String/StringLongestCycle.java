package Alogrithm.String;

import Util.Common;
import java.util.HashSet;
import java.util.HashMap;
import java.util.TreeSet;

// https://juejin.im/post/5b8f9aed6fb9a05d2e1b75d9

public class StringLongestCycle {

    public static int handleCountNumberOfContainStringLongestCycle(String str) {
        // LeetCode: 最长回文串 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
        int sum = 0;
        boolean odd = true;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0, size = str.length(); i < size; i++) {
            if (map.get(str.charAt(i)) == null) {
                map.put(str.charAt(i), 1);
            } else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }
        }

        for (HashMap.Entry<Character, Integer> item: map.entrySet()) {
            sum += item.getValue() / 2;
            if (item.getValue() % 2 == 1) {
                odd = false;
            }
        }

        return odd ? 2 * sum : 2 * sum + 1;
    }

    public static int handleCountNumberOfStringLongestCycle(String item) {
        // LeetCode: 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
        if (item == null) {
            return 0;
        }

        int max = 0;

        for (int i = 0, size = item.length(); i < size; i++) {

            int one = 0;
            int two = 0;

            for (int j = i - 1, k = i; j >= 0 && k < size; j--, k++, one += 2) {
                if (item.charAt(j) != item.charAt(k)) {
                    break;
                }
            }

            for (int j = i - 1, k = i + 1; j >= 0 && k < size; j--, k++, two += 2) {
                if (item.charAt(j) != item.charAt(k)) {
                    break;
                }
            }

            two = two == 0 ? two : two + 1;

            max = Math.max(max, Math.max(one, two));
        }

        return max;
    }

    public static boolean handleMatechCycle(String value) {
        // Leetcode: 验证回文串 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串。
        if (value == null) {
            return false;
        }

        if (value.length() == 0) {
            return true;
        }

        for (int i = 0, j = value.length() - 1; i < j; i++, j--) {
            if (value.charAt(i) != value.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static int longestCycleSequence(String s) {
        // DP 问题
        // LeetCode: 最长回文子序列, 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。最长回文子序列和上一题最长回文子串的区别是，子串是字符串中连续的一个序列，而子序列是字符串中保持相对位置的字符序列，例如，"bbbb"可以使字符串"bbbab"的子序列但不是子串。
        if (s == null) {
            return 0;
        }

        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i>=0; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }

        return dp[0][len-1];
    }

    public static boolean handleContainSubString(String s1, String s2) {
        // s1 = "ab", s2 = "eidbaooo"
        // Leetcode: 字符串的排列 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。 换句话说，第一个字符串的排列之一是第二个字符串的子串。
        HashMap<Character, Integer> map = new HashMap<>();


        int j = 0;
        for (int i = 0, p_size = s1.length(), t_size = s2.length(); i < p_size && j < t_size; i++, j++) {
            if (map.get(s1.charAt(i)) == null) {
                map.put(s1.charAt(i), -1);
            } else {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) - 1);
            }

            if (map.get(s2.charAt(j)) == null) {
                map.put(s2.charAt(j), 1);
            } else {
                map.put(s2.charAt(j), map.get(s2.charAt(j)) + 1);
            }
        }

        while (j < s2.length()) {
            if (map.get(s2.charAt(j)) == null || map.get(s2.charAt(j)) >= 0) {
            } else {
                map.put(s2.charAt(j), map.get(s2.charAt(j)) + 1);
            }
            boolean is = true;
            for (HashMap.Entry<Character, Integer> item: map.entrySet()) {
                if (item.getValue() < 0) {
                    is = false;
                    break;
                }
            }

            if (is) {
                return true;
            }

            j++;
        }

        return false;
    }

    public static void hanldeArrangement(char[] array, int k, TreeSet<String> set) {

        if (k == array.length) {
            set.add(String.valueOf(array));
        } else {

            for (int i = k; i < array.length; i++) {

                if (i != k && array[i] == array[k]) {
                    continue;
                }

                Common.swap(array, i, k);
                hanldeArrangement(array, k + 1, set);
                Common.swap(array, i, k);
            }
        }
    }

    HashSet<String> hanldeArrangement2(String value) {

        HashSet<String> set = new HashSet<>();
        char[] array = value.toCharArray();

        if (value == null) {
            return null;
        }

        if (value == "") {
            return null;
        }

        for (int i = 0; i < array.length; i++) {

            char item = array[i];

            HashSet<String> temp = new HashSet<>();

            for (String str: set) {

                for (int j = 0; j <= str.length(); j++) {
                    StringBuilder sb = new StringBuilder(str);

                    temp.add(sb.insert(j, item).toString());
                }
            }
            set.add(item + "");
            set.addAll(temp);
        }

    return set;
}

    public static char firstOnceCharacter(String str) {

        if (str == null) {
            return ' ';
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0, size = str.length(); i < size; i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        for (int i = 0, size = str.length(); i < size; i++) {
            if (map.get(str.charAt(i)) != null && map.get(str.charAt(i)) == 1) {
                return str.charAt(i);
            }
        }

        return ' ';

        // int[] array = new int[128];

        // for (int i = 0, size = str.length(); i < size; i++) {
        //     array[str.charAt(i) - '\0']++;
        // }

        // for (int i = 0, size = array.length; i < size; i++) {
        //     if (array[i] == 1) {
        //         return (char) i;
        //     }
        // }

        // return ' ';
    }

    public static String reverseString(String str) {
        // 剑指offer: 翻转单词顺序列 LeetCode: 翻转字符串里的单词
        // return new StringBuilder(str).reverse().toString();
        StringBuilder sb = new StringBuilder();

        for (int i = 0, size = str.length(); i < size; i++) {
            sb.append(str.charAt(size - i - 1));
        }

        return sb.toString();
    }

    public static boolean isRotateString(String A, String B) {
        // Leetcode: 旋转字符串 给定两个字符串, A 和 B。 A 的旋转操作就是将 A 最左边的字符移动到最右边。
        // 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A能变成B，那么返回True。
        return A.length() == B.length() && (B + B).contains(A);
    }

    public static String leftRotateString(String str, int offset) {
        // 剑指offer: 左旋转字符串
        // 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
        // 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
        // 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
        //
        if (offset < 0) {
            return str;
        }

        if (str == null) {
            return str;
        }

        offset = offset % str.length();

        StringBuilder sb = new StringBuilder();

        for (int i = offset, size = str.length(), k = (offset - 1 + size) % size; i != k; i = (1 + i) % size) {
            sb.append(str.charAt(i));
        }

        sb.append(str.charAt((offset + str.length() - 1) % str.length()));

        return sb.toString();
    }

    public static int convertStringToInt(String str) {
        if (str == null) {
            return 0;
        }

        int result = 0;
        for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }

            result += Character.getNumericValue(str.charAt(i)) * Math.pow(10, j);
        }

        return result;
    }

    public static String handlePrintBySort(String str) {
        // 剑指offer：字符串的排列 输入一个字符串, 按字典序打印出该字符串中字符的所有排列。
        // 例如输入字符串abc, 则打印出由字符a, b, c所能排列出来的所有字符串abc, acb, bac, bca, cab和cba。
        if (str == null) {
            return null;
        }

        TreeSet<String> set = new TreeSet<>();

        // 获取所有的组合
        hanldeArrangement(str.toCharArray(), 0, set);

        for(String item: set) {
            Common.print(item + " ");
        }

        Common.println("");
        return null;
    }

    public static void main(String[] args) {
        Common.println(handleMatechCycle("a"));
        Common.println(handleMatechCycle("aa"));
        Common.println(handleMatechCycle("ab"));
        Common.println(handleMatechCycle("aba"));
        Common.println(handlePrintBySort("abc"));
        Common.println(isRotateString("abc", "bac"));
        Common.println(reverseString("abcdefghijk"));
        Common.println(leftRotateString("abcdefghijk", 3));
        Common.println(firstOnceCharacter("abcdefbca"));
        Common.println(convertStringToInt("09123456"));
        Common.println(handleContainSubString("ab", "eidbaooo"));
        Common.println(handleCountNumberOfStringLongestCycle("abccccdd"));
        Common.println(handleCountNumberOfContainStringLongestCycle("abccccdd"));
    }
}

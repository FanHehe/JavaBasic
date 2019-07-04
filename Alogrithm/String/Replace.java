package Alogrithm.String;

import Util.Common;

public class Replace {

    public static String handleReplaceWhiteSpaceWithPerence20(String value) {

        if (value == null) {
            return "";
        }

        int sum = 0;

        StringBuffer sb = new StringBuffer(value);

        for (int i = 0; i < value.length(); i++) {
            if (sb.charAt(i) == ' ') {
                sum++;
                sb.append("  ");
            }
        }

        if (sum == 0) {
            return value;
        }

        for (int i = value.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == ' ') {
                sb.setCharAt(i + 2 * sum, '%');
                sb.setCharAt(i + 2 * sum - 1, '0');
                sb.setCharAt(i + 2 * sum - 2, '2');
                sum --;
            } else {
                sb.setCharAt(i + 2 * sum, sb.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] list = new String[] { "We Are Happy", "A  ", "  A", "J J", " J J J" };

        for (String item: list) {
            Common.println(handleReplaceWhiteSpaceWithPerence20(item));
        }
    }
}

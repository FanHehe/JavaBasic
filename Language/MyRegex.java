package Language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegex {
    public static final String PHONE_REGEXP = "^1[3456789]\\d{9}$";

    public static boolean matches() {
        return Pattern.matches(PHONE_REGEXP, "13889441200");
    }

    public static void matcher(String content) {
        Pattern p = Pattern.compile(PHONE_REGEXP);

        Matcher m = p.matcher(content);

        if(m.find()) {
            System.out.println(m.group(0)); // 整个内容
            System.out.println(m.group(2)); // 第一个匹配的内容
        }
    }

    public static void main(String[] args) {
        matches();
        matcher("13889441200");

        // 字符串自带的正则match
        boolean is3Point = "abc".matches("...");
    }
}

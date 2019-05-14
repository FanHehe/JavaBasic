public class P1 {
    public static void main(String[] args) {
        handle();
    }


    public static void handle () {
        println(p1("qwertyuiopasdfghjklzxcvbnm1234567890")); // true
        println(p1("qwertyuiopasdfghjkl0xcvbnm1234567890")); // false


        println(p3("123", "321"));
        println(p3("123", "331"));
        
        println(p5("aabcccccaaa"));
        println(p5("abbbd"));
    }

    public static boolean p1(String str) {

        if (null == str) {
            return false;
        }

        int[] flag = new int[8];

        for(int i = 0; i < str.length(); i++) {
            int diff = (int)str.charAt(i);

            int index = diff / 32;
            int offset = diff % 32;

            if ((flag[index] & (1 << offset)) != 0) {
                return false;
            }

            flag[index] = flag[index] | (1 << offset);
        }

        return true;
    }

    public static boolean p3(String str1, String str2) {

        if (str1 == null || str2 == null) {
            return false;
        }
       
        return p3sort(str1).equals(p3sort(str2));
    }

    public static String p5(String str) {

        if (str == null)
            return str;

        int from = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(from)) {
                sb.append(str.charAt(from)).append(i - from);

                from = i;
            }
        }

        sb.append(str.charAt(from)).append(str.length() - from);

        if (sb.toString().length() > str.length()) {
            return str;
        }

        return sb.toString();
    }

    public static void p6(int[][] martix, int n) {
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {

               int temp = martix[i][j];
               
               martix[i][j] = martix[n - 1 - i][j];
              
               martix[n - 1 - i][j] = martix[n - 1 - i][n - 1 - j];

               martix[n - 1 - i][n - 1 - j]  = martix[i][n - 1 - j];

               martix[i][n - 1 - j] = temp;
            }
        }
    }

    public static String p3sort(String value) {
        char[] arr = value.toCharArray();

        java.util.Arrays.sort(arr);

        return new String(arr); 
    }

    public static void println(Object message) {
        System.out.println(message);
    }

    public static void printArray(Object[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
        }
    } 
}
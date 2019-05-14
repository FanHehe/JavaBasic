public class Base {
    public static void main(String[] args) {
        handle();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void handle() {
        printMessage("base");
    }
}

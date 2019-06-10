import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Stack;

class JUList {

    public static void main(String[] args) {
        handleArrayList();
        handleLinkedList();
    }

    /**
     * size()
     * clear()
     * isEmpty()
     * get() set()
     * add() remove()
     * contains() indexOf()
     * =====================
     * sort(Comparator<? super E> c)
     */
    public static void handleList() {}

    /**
     * handleArrayList ArrayList 自己练习。
     * int DEFAULT_CAPACITY = 10;
     * 扩展速率：oldCapacity + oldCapacity >> 1 = 扩展1.5倍
     *
     */
    public static void handleArrayList() {
        ArrayList<Integer> list = new ArrayList<>(1);

        list.add(1);
        list.add(2);
        list.add(3);

        list.get(1);
        list.set(1, 4);
        list.contains(2);
        list.isEmpty();
        list.remove(1);
        list.remove(list.get(2));
        list.indexOf(list.get(2));
    }

    public static void handleLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
    }
}

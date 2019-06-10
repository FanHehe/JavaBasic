import java.util.List;

public class HeapSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null) {
            return;
        }

        // for (int size = list.size(), i = ((size - 1)/ 2); i >= 0; i--) {
        //     E curr = list.get(i);
        //     E left = list.get(2 * i + 1);
        //     E right = list.get(2 * i + 2);

        //     if (right != null && right.compareTo(list.get(i)) < 0) {
        //         list.set(i, right);
        //         list.set(2 * i + 2, curr);
        //     }

        //     if (left != null && left.compareTo(list.get(i)) < 0) {
        //         E temp = list.get(i);
        //         list.set(i, left);
        //         list.set(2 * i + 1, temp);
        //     }
        // }
    }
}

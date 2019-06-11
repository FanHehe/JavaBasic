import java.util.List;
import java.util.ArrayList;

public class CountingSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null) {
            return;
        }

        // E max = list.get(0);
        // int size = list.size();

        // for (int i = 1; i < size; i++) {
        //     if (list.get(i).compareTo(max) > 0) {
        //         max = list.get(i);
        //     }
        // }


        // List<E> newList = new ArrayList<>(size);
        // List<E> counting = new ArrayList<>(max + 1);

        // for (E item: list) {
        //     counting.set()
        // }
    }
}

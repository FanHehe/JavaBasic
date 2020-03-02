import java.util.List;
import java.util.ArrayList;

public class MergeSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {

        List<E> buffer
            = new ArrayList<>(list);

        mergeSort(list, 0, list.size() - 1, buffer);
    }

   void mergeSort(List<E> list, int from, int to, List<E> buffer) {
        if (from < to) {
            int middle = (to + from) / 2;
            mergeSort(list, from, middle, buffer);
            mergeSort(list, middle + 1, to, buffer);
            merge(list, from, middle, to, buffer);
        }
    }

    void merge(List<E> list, int from, int middle, int to, List<E> buffer) {

        int i = from, j = middle + 1, k = 0;

        while (i <= middle && j <= to) {
            if (list.get(i).compareTo(list.get(j)) > 0) {
                buffer.set(k++, list.get(j++));
            } else {
                buffer.set(k++, list.get(i++));
            }
        }

        while (i <= middle) {
            buffer.set(k++, list.get(i++));
        }

        while(j <= to) {
            buffer.set(k++, list.get(j++));
        }

        for (k = 0, i = from; i <= to; i++, k++) {
            list.set(i, buffer.get(k));
        }
    }
}

import java.util.List;

public class HeapSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null) {
            return;
        }

        int size = list.size();

        for (int i = (size - 1) / 2; i >= 0; i--) {
            adjustHeap(list, i, size - 1);
        }

        for (int i = size - 1; i > 0; i--) {
            E temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            adjustHeap(list, 0, i - 1);
        }
    }

    public void adjustHeap(List<E> list, int i, int length) {

        E temp = list.get(i);

        for (int j = 2 * i + 1; j <= length; j = 2 * j + 1) {

            if (j + 1 <= length && list.get(j + 1).compareTo(list.get(j)) > 0) {
                j++;
            }

            if (list.get(j).compareTo(temp) > 0) {
                list.set(i, list.get(j));
                i = j;
            } else {
                break;
            }
        }

        list.set(i, temp);
    }
}

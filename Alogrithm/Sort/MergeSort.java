import java.util.List;
import java.util.ArrayList;

public class MergeSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null || list.size() < 2) {
            return;
        }

        List<E> result = mergeSort(list, 0, list.size() - 1);

        for (int i = 0, size = list.size(); i < size; i++) {
            list.set(i, result.get(i));
        }
    }

    List<E> mergeSort(List<E> list, int from, int to) {
        if (to == from) {
            List<E> array = new ArrayList<>(1);
            array.add(list.get(from));
            return array;
        }

        int middle = (to + from) / 2;

        List<E> left = mergeSort(list, from, middle);
        List<E> right = mergeSort(list, middle + 1, to);

        return merge(left, right);
    }

    List<E> merge(List<E> left, List<E> right) {

        int lsize = left.size();
        int rsize = right.size();

        List<E> array = new ArrayList<>(lsize + rsize);

        int i = 0, j = 0;

        while (i < lsize && j < rsize) {
            if (left.get(i).compareTo(right.get(j)) > 0) {
                array.add(right.get(j));
                j++;
            } else {
                array.add(left.get(i));
                i++;
            }
        }

        while (i < lsize) {
            array.add(left.get(i++));
        }

        while(j < rsize) {
            array.add(right.get(j++));
        }

        return array;
    }
}

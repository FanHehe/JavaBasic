import java.util.List;

public class InsertSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null) {
            return;
        }

        for (int i = 1, size = list.size();  i < size; i++) {

            int j = i - 1;

            E temp = (E)list.get(i);

            if (temp == null || list.get(j) == null) {
                // 不排序
                return;
            }

            for (; j >= 0 && list.get(j).compareTo(temp) > 0; j--) {
                list.set(j + 1, list.get(j));
            }

            list.set(j + 1, temp);
        }
    }
}

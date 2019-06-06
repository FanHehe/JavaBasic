import java.util.List;

public class BubbleSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null) {
            return;
        }

        // list 中可能存在null的情况暂时忽略
        for (int i = 0, size = list.size(); i < size; i++) {
            for (int j = size - 1; j > i; j--) {

                if (list.get(j) == null || list.get(j - 1) == null) {
                    continue;
                }
                // 冒泡排序
                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    E temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
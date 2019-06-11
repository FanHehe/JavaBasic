import java.util.List;
import java.util.ArrayList;

public class CountingSort<E extends Comparable<E>> implements Sort<E> {
    public void sort(List<E> list, boolean desc) {
        if (list == null || list.size() < 2) {
            return;
        }
    }
}

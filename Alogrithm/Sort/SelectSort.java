import java.util.List;

public class SelectSort<E extends Comparable<E>> implements Sort<E> {
	public void sort(List<E> list, boolean desc) {
		if (list == null) {
			return;
		}

		for (int i = 0, size = list.size(); i < size; i++) {

			int idx = i;
			E min = (E)list.get(i);

			for (int j = i + 1; j < size; j++) {
				if (min.compareTo(list.get(j)) > 0) {
					idx = j;
					min = list.get(j);
				}
			}

			E temp = list.get(i);
			list.set(i, min);
			list.set(idx, temp);
		}
	}
}
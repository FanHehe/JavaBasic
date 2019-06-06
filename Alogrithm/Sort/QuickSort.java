import java.util.List;

public class QuickSort<E extends Comparable<E>> implements Sort<E> {
	public void sort(List<E> list, boolean desc) {
		if (list == null) {
			return;
		}

		quickSort(list, 0, list.size() - 1);
	}

	public void quickSort(List<E> list, int from, int end) {
		if (from < end) {
			int mid = quick(list, from, end);
			quickSort(list, from, mid);
			quickSort(list, mid + 1, end);
		}
	}

	public int quick(List<E> list, int from, int end) {

		int i = from;
		int j = end;

		E povit = (E) list.get(from);

		while(i < j) {
			while(i <= j && list.get(j).compareTo(povit) > 0)
				j--;
			list.set(i, list.get(j));
			while(i <= j && list.get(i).compareTo(povit) < 0)
				i++;
			list.set(j, list.get(i));
		}

		list.set(i, povit);

		return i;
	}
}
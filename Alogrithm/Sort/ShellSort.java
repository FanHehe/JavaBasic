import java.util.List;

public class ShellSort<E extends Comparable<E>> implements Sort<E> {
	public void sort(List<E> list, boolean desc) {
		if (list == null) {
			return;
		}

		
		int size = list.size();
		int step = list.size() / 2;

		while (step > 0) {

			for (int j = 0, i = step; i < size; i += step) {

				E temp = (E)list.get(i);

				for (j = i - step; j >= 0 && temp.compareTo(list.get(j)) < 0; j -= step) {
					list.set(j + step, list.get(j));
				}

				list.set(j + step, temp);
			}

			step /= 2;
		}
	}
}
import java.util.List;
import static java.lang.System.out;

public interface Sort<E extends Comparable<E>> {

	void sort(List<E> list, boolean desc);

	default void sort(List<E> list) {
		sort(list, false);
	}

	default void print(List<E> list) {
		for (E item: list) {
			out.print(item);
			out.print("\t");
		}
		out.println("");
	}
}
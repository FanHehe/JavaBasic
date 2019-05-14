import java.util.Set;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyCopyOnWrite {
	public static void main(String[] args) {
		handleCopyOnWriteArraySet();
		handleCopyOnWriteArrayList();
	}

	/**
	 * extends AbstractSet
	 * 内部使用CopyOnWriteArrayList实现的。addIfAbsent
	 */
	public static void handleCopyOnWriteArraySet() {
		Set<Integer> set = new CopyOnWriteArraySet();

		set.add(1);
		set.add(2);
		set.add(1);
	}
	/**
	 * implements List<E> 
	 * 内部一个ReenterantLock 实例进行lock
	 */
	public static void handleCopyOnWriteArrayList() {
		List<Integer> list = new CopyOnWriteArrayList<>();

		list.add(1);
		list.add(2);
		list.add(3);
	}
}
import java.util.HashMap;
import java.util.TreeMap;

class JUMap {


	public static void main(String[] args) {
		handleHashMap();
		handleTreeMap();
	}

	/**
	 * size()
	 * isEmpty()
	 * containsKey()
	 * containsValue()
	 * get() / put() / remove()
	 * clear()
	 * keySet() values () entrySet()
	 * ==============================
	 * forEach()
	 * getOrDefault()
	 * putIfAbsent()
	 */
	public static void handleMap() {}

	/**
	 * 拉链法
	 * Object.hashCode(): native
	 * String.hashCode(): s[0] * 31^(n-1) + s[1] * 31^(n-2) + ... + s[n-1]
	 * Node.hashCode(): Objects.hashCode(key) ^ Objects.hashCode(value);
	 * Map.hash(): (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	 * 扩容：
	 * 容量为2^n次方：(n - 1) & Map.hash(); === Map.hash() % (n - 1) 
	 */
	public static void handleHashMap() {

	}

	/**
	 * 红黑树，有序map
	 * [handleTreeMap description]
	 */
	public static void handleTreeMap() {
		
	}
}


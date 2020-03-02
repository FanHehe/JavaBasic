import java.util.HashMap;
import java.util.TreeMap;

// https://www.jianshu.com/p/8324a34577a0

class JUMap {

    public static void main(String[] args) {
        handleHashMap();
        handleTreeMap();
        handleConcurrentHashMap();
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
     * Node.hashCode(): java.util.Objects.hashCode(key) ^ Objects.hashCode(value);
     * Map.hash(): (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     * 扩容：
     * 默认容量1 << 4
     * 容量为2^n次方：(n - 1) & Map.hash(); === Map.hash() % (n - 1)
     *
     */
    public static void handleHashMap() {}

    /**
     *
     */
    public static void handleConcurrentHashMap() {}

    /**
     * 红黑树，有序map
     */
    public static void handleTreeMap() {}
}


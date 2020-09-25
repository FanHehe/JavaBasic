import java.util.HashMap;
import java.util.TreeMap;

// HashMap & ConcurrentHashMap http://www.jasongj.com/java/concurrenthashmap/
// LinkedHashMap 实现了LRU
//  - accessOrder:
//      true: 访问顺序
//      false(默认): 写入顺序

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


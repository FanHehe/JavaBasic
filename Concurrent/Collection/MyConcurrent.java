import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.Deque;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ConcurrentSkipListMap;


public class MyConcurrent {

    public static void main(String[] args) {
        handleConcurrentHashMap();
        handleConcurrentLinkedQueue();
        handleConcurrentSkipListSet();
        handleConcurrentSkipListMap();
    }

    /**
     * extends AbstractMap
     * implents ConcurrentMap （）
     * Java 1.7 分段锁 segment extends ReentrantLock
     *             segment 可初始化，但不可进行扩容
     * Java 1.8 基于CAS
     *             sizeCtl [0：默认值, -1：哈希表初始化ing, 大于0：类似threshold，表示阈值, 小于-1：有多个线程正在进行扩容]
     */
    public static void handleConcurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("2", "234");
        map.put("3", "234");
        map.put("4", "234");
    }

    public static void handleConcurrentLinkedQueue() {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    }

    public static void handleConcurrentLinkedDeque() {
        Queue<Integer> deque = new ConcurrentLinkedDeque<>();
    }
    /**
     * 基于ConcurrentSkipListMap
     */
    public static void handleConcurrentSkipListSet() {
        Set<Integer> set = new ConcurrentSkipListSet<>();

        set.add(1);
        set.add(2);
        set.add(3);
    }
    public static void handleConcurrentSkipListMap() {
        Map<String, String> map = new ConcurrentSkipListMap<>();

        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
    }
}

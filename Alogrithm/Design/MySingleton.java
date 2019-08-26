package Alogrithm.Design;

class SyncSingleton {

    private static SyncSingleton instance;

    public static synchronized SyncSingleton instance() {
        return instance == null ? instance = new SyncSingleton() : instance;
    }
}

class HungrySignleton {
    private static HungrySignleton instance = new HungrySignleton();

    public static HungrySignleton instance() {
        return instance;
    }
}

class LazySingleton {

    private static LazySingleton instance;

    public static LazySingleton instance() {
        return instance == null ? instance = new LazySingleton() : instance;
    }
}

class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton instance() {
        if (instance == null) {
            synchronized(instance) {
                if (instance == null) {
                    // volatile
                    // 1. 分配内存空间。
                    // 2. 初始化对象。
                    // 3. 将内存空间的地址赋值给对应的引用。
                    instance = new DoubleCheckSingleton(); // 不加volatile则可能造成对象的不完全发布
                }
            }
        }

        return instance;
    }
}

class StaticInnerClassSingleon {
    static class InnerSingleton {
        public static StaticInnerClassSingleon instance = new StaticInnerClassSingleon();
    }

    public static StaticInnerClassSingleon instance() {
        return InnerSingleton.instance;
    }
}

public class MySingleton {

    public static void main(String[] args) {
        LazySingleton lazy = LazySingleton.instance();
        SyncSingleton sycn = SyncSingleton.instance();
        DoubleCheckSingleton check = DoubleCheckSingleton.instance();
        StaticInnerClassSingleon inner = StaticInnerClassSingleon.instance();
    }
}

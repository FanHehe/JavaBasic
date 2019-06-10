import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class MyAtomic {

    volatile int value = 1; // 必须是volatile且不是static对象

    public static void main(String[] args) {
        handleAtomicInteger();
        handleAtomicReference();
        handleAtomicIntegerArray();
        handleAtomicIntegerFieldUpdater();
    }

    /**
     * get / set
     * getAndAdd / addAndGet
     * getAndSet / compareAndSet
     * getAndIncrement / getAndDecrement
     * incrementAndGet / decrementAndGet
     */
    public static void handleAtomicInteger() {
        Integer value = new Integer(1);
        AtomicInteger atomicValue = new AtomicInteger(value);

        atomicValue.getAndSet(2);
        atomicValue.getAndAdd(2);
        atomicValue.getAndIncrement();
        atomicValue.compareAndSet(2, 3);
    }

    /**
     * 
     */
    public static void handleAtomicReference() {
        String value = "123";
        AtomicReference<String> atomicReference = new AtomicReference<>(value);

        atomicReference.getAndSet("456");
        atomicReference.compareAndSet(value, "789");
    }

    public static void handleAtomicIntegerArray() {
        int[] array = new int[] { 1, 2, 3, 4 };

        AtomicIntegerArray atomicArray = new AtomicIntegerArray(array);

        atomicArray.getAndSet(1, 2);
        atomicArray.compareAndSet(1, 2, 2);
    }

    public static void handleAtomicIntegerFieldUpdater() {
        MyAtomic myAtomic = new MyAtomic();
        AtomicIntegerFieldUpdater<MyAtomic> filedUpdaer = AtomicIntegerFieldUpdater.newUpdater(MyAtomic.class, "value");

        filedUpdaer.compareAndSet(myAtomic, 1, 2);
        filedUpdaer.getAndDecrement(myAtomic);
    }
}
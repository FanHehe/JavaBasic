package Language;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import static java.lang.System.out;

public class MyReference {

    public static String value = "it's a string";

    public static void handleSoftReference() {
        ReferenceQueue<String> rq = new ReferenceQueue<>();

        SoftReference<String> ref = new SoftReference<>(value, rq);

        print(ref.get());
        System.out.println(rq.poll());
    }

    public static void handleWeakReference() {
        ReferenceQueue<String> rq = new ReferenceQueue<>();

        WeakReference<String> ref = new WeakReference<>(value, rq);

        print(ref.get());
        System.out.println(rq.poll());
    }

    public static void handlePhantomReference() {
        ReferenceQueue<String> rq = new ReferenceQueue<>();

        PhantomReference<String> ref = new PhantomReference<>(value, rq);

        print(ref.get());
        System.out.println(rq.poll());
    }

    public static void print(String message) {
        out.println(message);
    }

    public static void main(String[] args) {
        handleSoftReference();
        handleWeakReference();
        handlePhantomReference();
    }
}

package Language;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.RandomAccess;

public class MyPECS {


    public static void main(String[] args) {
        List<String> src = new ArrayList<>(10);
        List<CharSequence> dest = new ArrayList<>(10);

        src.add("1");
        src.add("2");
        src.add("3");
        src.add("4");
        src.add("5");

        copy(dest, src);

        for(CharSequence item: dest) {
            System.out.println(String.valueOf(item));
        }

    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        int srcSize = src.size();
        if (srcSize > dest.size())
            throw new IndexOutOfBoundsException("Source does not fit in dest");

        if (srcSize < 50 ||
            (src instanceof RandomAccess && dest instanceof RandomAccess)) {
            for (int i=0; i<srcSize; i++)
                dest.set(i, src.get(i));
        } else {
            ListIterator<? super T> di=dest.listIterator();
            ListIterator<? extends T> si=src.listIterator();
            for (int i=0; i<srcSize; i++) {
                di.next();
                di.set(si.next());
            }
        }
    }
}

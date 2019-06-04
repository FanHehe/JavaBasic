import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWrite {

    public int value = 0;

    public ReadWriteLock rwLock = null;

    ReadWrite() {
        this(new ReentrantReadWriteLock());
    }

    ReadWrite(ReadWriteLock lock) {
        this.rwLock = lock;
    }

    public int read() {
        this.rwLock.readLock().lock();

        int value = this.value;

        this.rwLock.readLock().unlock();

        return value;
    }

    public int write(int value) {

        this.rwLock.writeLock().lock();

        int oldValue = this.value;

        this.value += value;

        this.rwLock.writeLock().unlock();

        return value;
    }

    public static void main(String[] args) {
        ReadWrite rw = new ReadWrite();
        ExecutorService service = Executors.newFixedThreadPool(5);

        service.submit(()-> { while(true) { System.out.println("read1: " + rw.read()); }});
        service.submit(()-> { while(true) { System.out.println("read2: " + rw.read()); }});
        service.submit(()-> { while(true) { System.out.println("read3: " + rw.read()); }});
        service.submit(()-> { while(true) { System.out.println("read4: " + rw.read()); }});
        service.submit(()-> { while(true) { System.out.println("write1: " + rw.write(1)); }});
        service.submit(()-> { while(true) { System.out.println("write2: " + rw.write(2)); }});
        service.submit(()-> { while(true) { System.out.println("write3: " + rw.write(3)); }});
        service.submit(()-> { while(true) { System.out.println("write4: " + rw.write(4)); }});

    }
}

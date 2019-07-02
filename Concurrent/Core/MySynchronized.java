public class MySynchronized {

    public static synchronized void handleSync() {
        // Java对象头
        // 对象大致可以分为三个部分，分别是对象头、实例变量和填充字节
        //   - 对象头:
        //      - MarkWord: 存储对象自身的运行时数据
        //          - 32位JVM中的长度是32bit，在64位JVM中长度是64bit。
        //          - hashCode 锁状态 GC状态 分代年龄 是否偏向。
        //      - Klass Point: 对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例
        //   - 实例变量存储的是对象的属性信息，包括父类的属性信息，按照4字节对齐
        //   - 填充字符，因为虚拟机要求对象字节必须是8字节的整数倍，填充字符就是用于凑齐这个整数倍的
        // 偏向锁和轻量级锁都是在java6以后对锁机制进行优化时引进的
        // Synchronized关键字对应的是重量级锁，接下来对重量级锁在Hotspot JVM中的实现锁讲解。
        // https://blog.csdn.net/tongdanping/article/details/79647337
        // 基于进入和退出monitor对象来实现
        //  - synchronized(this) monitorentry & monitorexit
        //  - synchronized void a () ACC_SYNCHRONIZED
    }

    public static void handleLockUpgrade() {
        // 无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态（级别从低到高）

        // 偏向锁
        // 因为经过HotSpot的作者大量的研究发现，大多数时候是不存在锁竞争的。
        // 常常是一个线程多次获得同一个锁，因此如果每次都要竞争锁会增大很多没有必要付出的代价，为了降低获取锁的代价，才引入的偏向锁。
        // 当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向的锁的threadID。
        // 因为偏向锁不会主动释放锁，因此以后线程1再次获取锁的时候。
        // 需要比较当前线程的threadID和Java对象头中的threadID是否一致，如果一致（还是线程1获取锁对象），则无需使用CAS来加锁、解锁。
        //
        //
        // 轻量级锁
        // 基于锁记录
        //
        //
        // 重量级锁
        // 自旋的次数是有限制的，比如10次或者100次，如果自旋次数到了线程1还没有释放锁，或者线程1还在执行，线程2还在自旋等待。
        // 这时又有一个线程3过来竞争这个锁对象，那么这个时候轻量级锁就会膨胀为重量级锁。重量级锁把除了拥有锁的线程都阻塞，防止CPU空转。
    }
}

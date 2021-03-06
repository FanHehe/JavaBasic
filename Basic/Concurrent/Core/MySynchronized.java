public class MySynchronized {

    public static synchronized void handleSync() {
        // https://blog.csdn.net/tongdanping/article/details/79647337
        // Java对象头
        // 对象大致可以分为三个部分，分别是对象头、实例变量和填充字节
        //   - 对象头:
        //      - MarkWord: 存储对象自身的运行时数据(32位JVM中的长度是32bit，在64位JVM中长度是64bit)
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
        // monitor: https://blog.csdn.net/mulinsen77/article/details/88635558
        // https://blog.csdn.net/tongdanping/article/details/79647337
        // https://blog.csdn.net/lengxiao1993/article/details/81568130

        // 偏向锁： -XX:-UseBiasedLocking = false
        // 原理：
        //  - 当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向的锁的threadID。
        // 因为偏向锁不会主动释放锁，因此以后线程1再次获取锁的时候。
        // 需要比较当前线程的threadID和Java对象头中的threadID是否一致，如果一致（还是线程1获取锁对象），则无需使用CAS来加锁、解锁。
        //
        // 升级：
        //  - 当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向的锁的threadID，因为偏向锁不会主动释放锁。
        //  - 因此以后线程1再次获取锁的时候，需要比较当前线程的threadID和Java对象头中的threadID是否一致。
        //  - 如果一致（还是线程1获取锁对象），则无需使用CAS来加锁、解锁。
        //  - 如果不一致（其他线程，如线程2要竞争锁对象，而偏向锁不会主动释放因此还是存储的线程1的threadID），那么需要查看Java对象头中记录的线程1是否存活。
        //  - 如果没有存活，那么锁对象被重置为无锁状态，其它线程（线程2）可以竞争将其设置为偏向锁；如果存活，那么立刻查找该线程（线程1）的栈帧信息。
        //  - 如果还是需要继续持有这个锁对象，那么暂停当前线程1，撤销偏向锁，升级为轻量级锁，如果线程1 不再使用该锁对象，那么将锁对象状态设为无锁状态，重新偏向新的线程。
        //
        // 轻量级锁
        // 关键字：自旋等待.自旋这等待锁释放。
        //
        // 升级：
        //  线程1获取轻量级锁时会先把锁对象的对象头MarkWord复制一份到线程1的栈帧中创建的用于存储锁记录的空间（称为DisplacedMarkWord），然后使用CAS把对象头中的内容替换为线程1存储的锁记录（DisplacedMarkWord）的地址；
        //  如果在线程1复制对象头的同时（在线程1CAS之前），线程2也准备获取锁，复制了对象头到线程2的锁记录空间中，但是在线程2CAS的时候，发现线程1已经把对象头换了，线程2的CAS失败，那么线程2就尝试使用自旋锁来等待线程1释放锁。
        //  但是如果自旋的时间太长也不行，因为自旋是要消耗CPU的，因此自旋的次数是有限制的，比如10次或者100次，如果自旋次数到了线程1还没有释放锁，或者线程1还在执行，线程2还在自旋等待，这时又有一个线程3过来竞争这个锁对象，那么这个时候轻量级锁就会膨胀为重量级锁。重量级锁把除了拥有锁的线程都阻塞，防止CPU空转。
        //
        // 重量级锁
        // 自旋的次数是有限制的，比如10次或者100次，如果自旋次数到了线程1还没有释放锁，或者线程1还在执行，线程2还在自旋等待。
        // 这时又有一个线程3过来竞争这个锁对象，那么这个时候轻量级锁就会膨胀为重量级锁。重量级锁把除了拥有锁的线程都阻塞，防止CPU空转。
    }
}

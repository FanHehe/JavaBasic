public class MyHappenBefore {
    // https://zhuanlan.zhihu.com/p/29881777
    // JMM抽象内存模型：原子性、可见性、有序性。
    //  - 特点：
    //      - 线程之间的共享变量存储在主内存（Main Memory）中。
    //      - 每个线程都有一个私有的本地内存（Local Memory）
    //      - 从更低的层次来说，主内存就是硬件的内存，而为了获取更好的运行速度，虚拟机及硬件系统可能会让工作内存优先存储于寄存器和高速缓存中。
    //      - Java内存模型中的线程的工作内存（working memory）是cpu的寄存器和高速缓存的抽象描述。而JVM的静态内存储模型（JVM内存模型）只是一种对内存的物理划分而已，它只局限在内存，而且只局限在JVM的内存。
    //  - 原子性:
    //      - lock: 将主内存中的变量标识为一个线程独占的状态
    //      - unlock: 将主内存中的变量从锁定状态的变量释放出来
    //      - read: 把一个变量的值从主内存传输到线程的工作内存中
    //      - load: 作用于工作内存中的变量，它把read操作从主内存中得到的变量值放入工作内存中的变量副本
    //      - use: 把工作内存中一个变量的值传递给执行引擎
    //      - assign: 它把一个从执行引擎接收到的值赋给工作内存的变量
    //      - store: 把工作内存中一个变量的值传送给主内存
    //      - write: 作用于主内存的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中
    //      - synchronized 具有原子性
    //      - volatile 不具有原子性
    //  - 可见性:
    //      - 当一个线程修改了共享变量后，其他线程能够立即得知这个修改
    //      - synchronized 具有可见性
    //      - volatile 具有可见性
    //      - happen-before
    //              - Java内存模型中有八条可保证happen—before的规则。
    //              - 它们无需任何同步器协助就已经存在，可以在编码中直接使用。
    //              - 如果两个操作之间的关系不在此列，并且无法从下列规则推导出来的话，它们就没有顺序性保障, 虚拟机可以对它们进行随机地重排序。
    //          - 程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。
    //          - 监视器锁规则：对一个锁的解锁unlock，happens-before于随后对这个锁的加锁lock。
    //          - volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。
    //          - 线程启动规则：线程的start操作都现行发生于此线程中的所有操作。
    //          - 线程终止规则：线程中的所有操作都现行发行与对此现场的终止检测，包括Thread.join()、Thread.isAlive()等方法。
    //          - 线程中断规则：对线程interrupted()方法的调用先行于被中断线程的代码检测到中断时间的发生。
    //          - 对象finalize规则：一个对象的初始化完成（构造函数执行结束）先行于发生它的finalize()方法的开始。
    //          - 传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。
    //  - 有序性：
    //      - 指令重排
    //      - synchronized具有有序性
    //      - 在本线程内观察，所有的操作都是有序的；如果在一个线程观察另一个线程，所有的操作都是无序的。
    //      - + happen-before
    // CAP原则又称CAP定理：
    // 指的是在一个分布式系统中
    // Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性）
    // 这三个基本需求，最多只能同时满足其中的2个。https://juejin.im/post/5b26634b6fb9a00e765e75d1
    //
    // JVM内存分区：程序计数器，JVM栈，本地方法栈，堆，方法区。
}
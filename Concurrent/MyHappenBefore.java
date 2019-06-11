public class MyHappenBefore {
    // Java内存模型中有八条可保证happen—before的规则。
    // 它们无需任何同步器协助就已经存在，可以在编码中直接使用。
    // 如果两个操作之间的关系不在此列，并且无法从下列规则推导出来的话，它们就没有顺序性保障。
    // 虚拟机可以对它们进行随机地重排序。
    // Java 的内存模型：原子性、可见性、有序性

    // 与以下傻傻分不清
    // CAP原则又称CAP定理：
    // 指的是在一个分布式系统中
    // Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性）
    // 这三个基本需求，最多只能同时满足其中的2个。https://juejin.im/post/5b26634b6fb9a00e765e75d1

    // 1. 程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。
    // 2. 监视器锁规则：对一个锁的解锁unlock，happens-before于随后对这个锁的加锁lock。
    // 3. volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。
    // 4. 线程启动规则：线程中的所有操作都现行发生于对此线程的每一个动作。
    // 5. 线程终止规则：线程中的所有操作都现行发行与对此现场的终止检测，可以通过Thread.join()方法结束，Thread.isAlive()的返回值等手段检测到线程已经终止。
    // 6. 线程中断规则：对线程interrupted()方法的调用先行于被中断线程的代码检测到中断时间的发生。
    // 7. 对象finalize规则：一个对象的初始化完成（构造函数执行结束）先行于发生它的finalize()方法的开始。
    // 8. 传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。
}

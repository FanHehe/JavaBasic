package JVM;

public class GC {
    // JVM的内存管理结构
    //
    // 1. 程序计数区
    // 2. 虚拟机栈
    // 3. 本地方法栈
    // 4. 堆
    // 5. 方法区
    //     1. JVM加载的类信息
    //     2. 常量
    //     3. 静态变量
    //     4. 即时编译器编译后的代码等数据
    //
    // https://mritd.me/2016/03/22/Java-%E5%86%85%E5%AD%98%E4%B9%8B%E6%96%B9%E6%B3%95%E5%8C%BA%E5%92%8C%E8%BF%90%E8%A1%8C%E6%97%B6%E5%B8%B8%E9%87%8F%E6%B1%A0/

    // 堆的回收：
    //  - 是否可以回收：引用计数(循环引用)，可达性分析
    //  - 垃圾收集算番：标记清除 / 标记整理 / 分代回收
    //
    //  可达性分析：
    //  + GC Roots =
    //  + 虚拟机栈 + 本地方法栈中的变量
    //  + 方法区中类的静态属性和常量引用的对象

    //  引用类型：
    //  - 强引用：普通复制引用
    //  - 软引用：java.lang.ref.SoftReference (在内存溢出前，进行回收，如果还溢出，则OOM)
    //  - 弱引用：java.lang.ref.WeakReference (在下一场GC之前，就会被回收掉)
    //  - 虚引用：java.lang.ref.PhantomReference (一个对象是否有虚引用，对对象的生存时间不会构成影响)
    //
    //  finalize(): 挽救一个将被回收的对象的最后机会，仅会被调用一次。
    //
    //  垃圾收集算法：
    //  标记清除 + 复制算法 = 标记整理

    //  分代回收：
    //  - 新生代(复制算法)
    //      - Eden Space
    //      - Survivor Space = from Space + to Space
    //      - ratio = Eden : Survivor = 8 : 1
    //  - 老年代(标记整理)
    //      - 大对象直接进入老年代
    //      - 熬过一次GC，年龄加一，超过默认15岁进入老年代
    //  - 方法区(永久代 / 元空间)
    //      - 元空间：
    //          -
    //
    //  - 新对象优先从Eden区分配空间
    //      - 如果Eden空间够的话就结束
    //      - 如果Eden空间不够的话
    //          - 老年代的空间是否 > 新生代的大小
    //              - 是：则进行一次MinorGC，进行分配空间
    //                  - 如果成功：结束
    //                  - 如果分配失败，进行分配担保
    //              - 否：是否允许分配担保失败
    //                  - 是：进行MinorGC
    //                      - 成功：完成
    //                      - 失败：FullGC, MinorGC
    //                  - 否：进行FullGC, MinorGC 分配空间
    //
    //  https://blog.csdn.net/pf1234321/article/details/82288921
    //
    //  JAVA 8默认
    //  -server -Xmx2048m -Xms2048m -Xmn758m -Xss256k -XX:SurvivorRatio=6 -XX:ParallelGCThreads=8 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:/usr/share/tomcat/logs/gc.log
    //  JAVA10默认
    //  -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC
    //
}

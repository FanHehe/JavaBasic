package Basic.JVM;

// https://www.cnblogs.com/chenfangzhi/p/11869396.html

public class JVM {

    public static void handleMemory() {
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
        //  finalize():
        //
        //  垃圾收集算法：
        //  标记清除 + 复制算法 = 标记整理

        //  分代回收：
        //  - 新生代(复制算法)
        //      - Eden Space
        //      - Survivor Space = from Space + to Space
        //      - ratio = Eden : Survivor = 8 : 1
        //  - 老年代(标记整理+标记清除)
        //      - 大对象直接进入老年代
        //      - 熬过一次GC，年龄加一，超过默认15岁进入老年代
        //  - 方法区(永久代 / 元空间)
        //      - 元空间：
        //          - used: 加载的类的空间量
        //          - capacity: capacity是指那些被实际分配的Chunk大小之和
        //          - committed: committed是指那些被commit的Chunk大小之和
        //          - reserved: 元数据的空间保留（但不一定提交）的量。
        //          Metaspace由一个或多个虚拟空间组成，虚拟空间的分配单元是Chunk，其中Chunk使用列表进行维护。
        //          当使用一个classLoader加载一个类时，过程如下：
        //          1、当前classLoader是否有对应的Chunk且有足够的空间。
        //          2、查找空闲列表中的有没有空闲的Chunk。
        //          3、如果没有，就从当前虚拟空间中分配一个新的Chunk，这个时候会把对应的内存进行Commit，这个动作就是提交。
        //          4、如果当前虚拟空间不足，则预留(reserves)一个新的虚拟空间。
        //          因为有GC的存在，有些Chunk的数据可能会被回收，那么这些Chunk属于committe的一部分，但不属于capacity
        //          另外，这些被分配的Chunk，基本很难被100%用完，存在碎片内存的情况，这些Chunk实际被使用的内存之和即used的大小；
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
        //  java -XX:+PrintCommandLineFlags -version
        //  java -XX:+PrintFlagsInitial 可以查看所以的jvm默认参数
        //  https://blog.csdn.net/pf1234321/article/details/82288921
        //
        //  JAVA 8默认
        //  -server -Xms2048m -Xmx2048m -Xmn758m -Xss256k -XX:SurvivorRatio=6 -XX:ParallelGCThreads=8 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:/usr/share/tomcat/logs/gc.log
        //
        //  JAVA10默认
        //  -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC
    }

    public static void handleJava() {

        // JAVA_OPTS="$JAVA_OPTS -server -Xmx4906m -Xms4096m -Xmn1536m -Xss256k -XX:SurvivorRatio=6 -XX:ParallelGCThreads=8"
        // -server 一定要作为第一个参数，在多个CPU时性能佳
        // -Xms：初始Heap堆大小，使用的最小内存
        // -Xmx：java heap最大值，使用的最大内存
        // -Xmn：新生代的大小 3 / 8
        // -Xss256k: 每个线程的栈大小
        // -XX:SurvivorRatio=8 : Eden区与Survivor区的大小比值，设置为8,则两个Survivor区与一个Eden区的比值为2:8,一个Survivor区占整个年轻代的1/10
        // -XX:ParallelGCThreads
        // -XX:+HeapDumpOnOutOfMemoryError        OOM时导出堆到文件
        // -XX:HeapDumpPath=d:/a.dump        　　  导出OOM的路径
        // -XX:+PrintGCDetails           　　　　   打印GC详细信息
        // -XX:+PrintGCTimeStamps            　　　 打印CG发生的时间戳
        // -XX:+PrintHeapAtGC            　　　　　  每一次GC前和GC后，都打印堆信息
        // -XX:+TraceClassLoading            　　　  监控类的加载
        // -XX:+PrintClassHistogram        　　　　　 按下Ctrl+Break后，打印类的信息

        // 参数-XX:+UseParallelGC
        // Young区: (Parallel Scavenge (PSYoungGen))使用Parallel scavenge 回收算法
        // Old 区:  (Serial Old(PSOldGen)) 可以使用单线程的或者Parallel 垃圾回收算法，由 -XX:+UseParallelOldGC 来控制
    }

    public static void handleLinux() {
        // iostat -x
        // vm.swappiness = 0
        //
        // /etc/sysctl.conf
        // net.ipv4.tcp_tw_reuse=1: 表示开启重用。允许将TIME-WAIT sockets重新用于新的TCP连接，默认为0，表示关闭；
        // net.ipv4.tcp_tw_recycle=1:  表示开启TCP连接中TIME-WAIT sockets的快速回收，默认为0，表示关闭
        // net.ipv4.tcp_fin_timeout=30 修改系默认的 TIMEOUT 时间

        // 查看不同状态的连接数数量: netstat -n | awk '/^tcp/ {++y[$NF]} END {for(w in y) print w, y[w]}'
        // 查看每个ip跟服务器建立的连接数: netstat -nat|grep "tcp"|awk ' {print$5}'|awk -F : '{print$1}'|sort|uniq -c|sort -rn
    }

    public static void handleTool() {
        // jps: 查看Java进程列表
        // jps -ml
        //
        // jmap: 查看JVM堆的情况
        // jmap -dump:live,file=heap.dump 3163
        //
        // jstack: 查看JVM的堆栈情况，监测死锁等
        //
        // jstat 查看JVM gc信息，观察JVM的GC活动
        //
        // jinfo 查看设置的JVM参数和启动时的命令行参数，还可以动态修改JVM参数
    }
}

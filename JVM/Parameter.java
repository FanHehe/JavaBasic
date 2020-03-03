package JVM;

// https://www.cnblogs.com/chenfangzhi/p/11869396.html

public class Parameter {

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

package Concurrent.Lock;

public class MyDeadLock {
    public static void handleCondition() {
        // https://blog.csdn.net/abigale1011/article/details/6450845
        // 互斥条件：资源同一时间，仅能被一个进程占用。
        // 不剥夺条件：进程所获得的资源在未使用完毕之前，资源申请者不能强行地从资源占有者手中夺取资源，而只能由该资源的占有者进程自行释放。
        // 占有且申请条件：进程至少已经占有一个资源，但又申请新的资源
        // 循环等待条件：形成一个进程循环等待环。
    }
}

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class MyThreadPool {

    // 线程池的状态
    // Running、ShutDown、Stop、Tidying、Terminated。
    // 线程池的初始化状态是RUNNING，能够接收新任务，以及对已添加的任务进行处理。
    // 线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。
    // 线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。
    // 当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。
    // 线程池彻底终止，就变成TERMINATED状态。线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED

    public static final int value = 5;

    public static void main(String[] args) {
        handleFixedThreadPool();
        handleCachedThreadPool();
        handleSingleThreadExecutor();
        handlePrimaryThreadPool();
    }

    public static void handleFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(value);

        pool.submit(new Runnable() {
            @Override
            public void run () {
                System.out.println("hehe");
            }
        });
    }

    public static void handleSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.submit(new Runnable() {
            @Override
            public void run () {
                System.out.println("hehe");
            }
        });
    }

    public static void handleCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(new Runnable() {
            @Override
            public void run () {
                System.out.println("hehe");
            }
        });
    }

    public static void handlePrimaryThreadPool() {
        int core = 5;
        int maximum = 10;
        long keepAliveTime = 0L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        ExecutorService singleThreadPool = makeThreadPool(1, 1, keepAliveTime, unit, queue);
        ExecutorService fixedThreadPool = makeThreadPool(core, core, keepAliveTime, unit, queue);
        ExecutorService cachedThreadPool = makeThreadPool(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,  new SynchronousQueue<Runnable>());

    }

    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters and default thread factory and rejected execution handler.
     * It may be more convenient to use one of the {@link Executors} factory
     * methods instead of this general purpose constructor.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue} is null
     */
    public static ThreadPoolExecutor makeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}

public class MyThread {

    public enum ThreadState {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,

        /**
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,

        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         * {@link Object#wait() Object.wait}.
         */
        BLOCKED,

        /**
         * Thread state for a waiting thread.
         * A thread is in the waiting state due to calling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #join() Thread.join} with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         *
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         *
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * that object. A thread that has called <tt>Thread.join()</tt>
         * is waiting for a specified thread to terminate.
         */
        WAITING,

        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) Thread.join} with timeout</li>
         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATED;

        // Thread.State : NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
    }

    public static void handleThreadLocal() {

        threadLocal.set(value);

        System.out.println(threadLocal.get());

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    }

    class MyThreadPool {

        // Running:  线程池的初始化状态
        // ShutDown: 不接收新任务，但能处理已添加的任务
        // Stop:     不接收新任务，不处理已添加的任务，并且会中断正在处理的任务
        // Tidying:  当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态
        // Terminated: 线程池彻底终止，就变成TERMINATED状态

        public static void main(String[] args) {

            int core = 5;
            int maximum = 10;
            long keepAliveTime = 0L;
            TimeUnit unit = TimeUnit.MILLISECONDS;
            LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

            ExecutorService singleThreadPool = makeThreadPool(1, 1, keepAliveTime, unit, queue);
            ExecutorService fixedThreadPool  = makeThreadPool(core, core, keepAliveTime, unit, queue);
            ExecutorService cachedThreadPool = makeThreadPool(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,  new SynchronousQueue<Runnable>());
        }

        /**
         * @param corePoolSize
         *        - 当一个新任务提交的线程池执行时，如果当前正在运行的线程数小于核心线程数，
         *        - 即使线程池中有空闲的线程，仍创建一个新线程处理任务执行请求，即创建一个新的任务线程执行任务
         * @param maximumPoolSize
         *        - 最大线程数
         * @param keepAliveTime
         *        - 当线程数量大于核心线程数时，线程可以处于idle状态的最大时间
         * @param unit
         *        - 当线程数量大于核心线程数时，线程可以处于idle状态的最大时间的单位
         * @param workQueue
         *        - 用户储存排队等待被执行的队列，仅可使用execute提交的时候，才会使用本队列
         *            - new SynchronousQueue();    // 无缓冲的等待队列，无界
         *            - new ArrayBlockingQueue(5); // 基于数组的先进先出队列，有界
         *            - new LinkedBlockingQueue(); // 基于链表的先进先出队列，无界
         * @param threadFactory
         *        - 线程工厂，用于生成新的线程，实现即可欧ThreadFactory
         *        - 仅有一个接口newThread
         *        - 默认工厂:
         *            - 设置了优先级为Thread.NORM_PRIORITY
         *            - 规定了线程组
         *            - 声明为非守护进程
         *            - 重新规定了线程的名字
         * @param handler 当达到队列容量时的，新任务的处理方式
         *        - ThreadPoolExecutor.AbortPolicy 抛出异常
         *        - ThreadPoolExecutor.DiscardPolicy 什么都不做，忽略当前任务
         *        - ThreadPoolExecutor.CallerRunsPolicy 只要线程池未处于shutdown状态，则直接运行
         *        - ThreadPoolExecutor.DiscardOldestPolicy 只要线程池未处于shutdown状态则抛弃最老的任务，当前任务重新被提交
         * @throws IllegalArgumentException if one of the following holds:<br>
         *         {@code corePoolSize < 0}<br>
         *         {@code keepAliveTime < 0}<br>
         *         {@code maximumPoolSize <= 0}<br>
         *         {@code maximumPoolSize < corePoolSize}
         * @throws NullPointerException if {@code workQueue} or {@code threadFactory} or {@code handler} is null
         */
        public static ThreadPoolExecutor makeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public static void handle() {
            // 1. 当一个任务通过submit或者execute方法提交到线程池的时候，
            //      - 如果当前池中线程数（包括闲置线程）小于coolPoolSize，则创建一个线程执行该任务
            //
            // 2. 如果当前线程池中线程数已经达到coolPoolSize，则将任务放入等待队列
            //
            // 3. 如果任务不能入队，说明等待队列已满，若当前池中线程数小于maximumPoolSize，则创建一个临时线程（非核心线程）执行该任务
            //
            // 4. 如果当前池中线程数已经等于maximumPoolSize，此时无法执行该任务，根据拒绝执行策略处理
            //
            // 当池中线程数大于coolPoolSize
            //      超过keepAliveTime时间的闲置线程会被回收掉。
            //      回收的是非核心线程，核心线程一般是不会回收的。
            //      如果设置allowCoreThreadTimeOut(true)，则核心线程在闲置keepAliveTime时间后也会被回收。
            //      任务队列是一个阻塞队列，线程执行完任务后会去队列取任务来执行，如果队列为空，线程就会阻塞，直到取到任务。
        }
    }

}

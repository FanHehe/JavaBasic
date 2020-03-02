package Distributed;

// import org.apache.zookeeper.*;

public class MyZookeeper {
    public static void handleBase() {
        // https://segmentfault.com/a/1190000016349824
        // https://www.cnblogs.com/jaycekon/p/7553909.html
        // https://blog.csdn.net/wo541075754/article/details/65625481
        // ZooKeeper 是一个典型的分布式数据一致性解决方案。
        // 分布式应用程序可以基于ZooKeeper实现诸如
        //  - 数据发布/订阅
        //  - 负载均衡
        //  - 命名服务
        //  - 分布式协调/通知
        //  - 集群管理
        //  - Master 选举
        //  - 分布式锁
        //  - 分布式队列等功能。
        // 服务生产者和服务消费者的注册中心
        // Zookeeper中 Leader 选举算法采用了Zab协议。Zab核心思想是当多数 Server 写成功，则任务数据写成功
        // ZooKeeper 将数据保存在内存中，这也就保证了 高吞吐量和低延迟
        // ZooKeeper 底层其实只提供了两个功能：
        //  - 管理（存储、读取）用户程序提交的数据
        //  - 为用户程序提交数据节点监听服务
        // 会话 & sessionID
        // 数据节点一一ZNode，(SEQUENTIAL)
        //  - 持久节点
        //  - 临时节点 (生命周期和客户端会话绑定)
        // 特点
        //  - 顺序一致性： 从同一客户端发起的事务请求，最终将会严格地按照顺序被应用到 ZooKeeper 中去。
        //  - 原子性： 所有事务请求的处理结果在整个集群中所有机器上的应用情况是一致的，要么整个集群中所有的机器都成功应用了某一个事务，要么都没有应用。
        //  - 单一系统映像 ： 无论客户端连到哪一个 ZooKeeper 服务器上，其看到的服务端数据模型都是一致的。
        //  - 可靠性： 一旦一次更改请求被应用，更改的结果就会被持久化，直到被下一次更改覆盖。
        // Master/Slave 模式（主备模式）
        // 引入了Leader、Follower 和 Observer 三种角色
        //  - Leader 既可以为客户端提供写服务又能提供读服务
        //  - Follower 和 Observer 都只能提供读服务
        //  - Follower 和 Observer 唯一的区别在于 Observer 机器不参与Leader的选举过程，也不参与写操作的【“过半写成功”】策略
        // 因此 Observer 机器可以在不影响写性能的情况下提升集群的读性能
        // ZAB 协议 Paxos算法
        //  - 崩溃恢复：当选举产生了新的 Leader 服务器，同时集群中已经有过半的机器与该Leader服务器完成了状态同步之后，ZAB协议就会退出恢复模式
        //  - 消息广播：当集群中已经有过半的Follower服务器完成了和Leader服务器的状态同步，那么整个服务框架就可以进人消息广播模式了
    }

    public static void handleCreate() {
        // ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new TestCreateNode());
        // String ephemeralPath = zooKeeper.create("/zk-test-create-ephemeral-", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }
}

class MyRedis {

    void handleObject() {
        // typedef struct redisObject{
        //      unsigned type:4;
        //      unsigned encoding:4;
        //      void *ptr;
        //      int refcount;
        //      unsigned lru:22;
        // } robj;

        // type
        //  - REDIS_STRING: string
        //  - REDIS_LIST:   list
        //  - REDIS_HASH:   hash
        //  - REDIS_SET:    set
        //  - REDIS_ZSET:   zset
        // encoding

        // https://www.cnblogs.com/ysocean/p/9102811.html
    }

    void handleDataType() {
        // string
        //  - simple dynamic string, SDS
        //  struct sdshdr{
        //     int len;
        //     int free;
        //     char buf[];
        //  }
        // list
        //  - 双端链表，带字段记录长度，表头节点的 prev 指针和表尾节点的 next 指针都指向 NULL,对链表的访问都是以 NULL 结束，链表节点使用 void* 指针来保存节点值，可以保存各种不同类型的值
        //  - ziplist（压缩列表）（节省内存）：https://www.cnblogs.com/ysocean/p/9080942.html
        //      - 总字节数
        //      - 尾节点的起始位置，距离起始位置的偏移量
        //      - 节点个数
        //      - 固定以0xFF结尾
        //          - 每个节点，【前一个节点的长度，编码，content】
        //  -
        // set
        //  - 　整数集合（intset）：

        // zset
        //      typedef struct zskiplistNode {
        //          struct zskiplistLevel {
        //              unsigned int span;
        //              struct zskiplistNode *forward;
        //          } level[];
        //
        //          robj *obj;
        //          double score;
        //          struct zskiplistNode *backward;
        //        } zskiplistNode
        //
        // typedef struct zskiplist {
        //      int level;
        //      unsigned long length;
        //      structz skiplistNode *header, *tail;
        // } zskiplist;

        // hash
        //  - 字典：字典中的每一个键 key 都是唯一的，通过 key 可以对值来进行查找或修改。C 语言中没有内置这种数据结构的实现，所以字典依然是 Redis自己构建的。
        //  - 拉链法
        //  - 扩容：1倍，时机：服务器目前没有执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于1，服务器目前正在执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于5。
        //  - 负载因子： 哈希表已保存节点数量 / 哈希表大小。
    }

    void hanldeDuration() {

        // 同时: RDB与AOF同时开启  默认先加载AOF的配置文件
        // RDB(快照保存到硬盘)(数据完整性要求很严格的需求)
        //  原理:
        //      - fork一个进程，遍历hash table，利用copy on write，把整个db dump保存下来。
        //      - save, shutdown, slave 命令会触发这个操作。
        //      - 粒度比较大，如果save, shutdown, slave 之前crash了，则中间的操作没办法恢复
        //  开启: save 900 1 （15分钟变更一次）
        // AOF(每次执行的命令保存起来)
        //  原理:
        //      - 把写操作指令，持续的写到一个类似日志文件里。
        //      - 粒度较小，crash之后，只有crash之前没有来得及做日志的操作没办法恢复。
        //  - 每次有新命令追加到 AOF 文件时就执行一次 fsync ：非常慢，也非常安全:
        //  - appendfsync
        //      - no 从不
        //      - always 总是
        //      - everysec 每秒 fsync 一次：足够快（和使用 RDB 持久化差不多），并且在故障时只会丢失 1 秒钟的数据
        //  开启：appendonly yes(默认no,关闭)
        //  -

        // 分布式锁的开源Redisson框架的实现机制
        //  - 可重入

        //  作为缓存:
        //      - 缓存雪崩: 大量缓存同时失效，大量请求到数据库
        //          - 缓存失效时间分散开
        //          - 大多数系统设计者考虑用加锁（ 最多的解决方案）或者队列的方式保证来保证不会有大量的线程对数据库一次性进行读写，从而避免失效时大量的并发请求落到底层存储系统上。
        //      - 缓存穿透: 缓存穿透是指用户查询数据，在数据库没有，自然在缓存中也不会有。这样就导致用户查询的时候，在缓存中找不到，每次都要去数据库再查询一遍，然后返回空
        //          - 布隆过滤器
        //              - 引入几个Hash
        //              - 如果通过其中的一个Hash值我们得出某元素不在集合中，那么该元素肯定不在集合中。只有在所有的Hash函数告诉我们该元素在集合中时，才能确定该元素存在于集合中。这便是Bloom-Filter的基本思想
        //          - 如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。通过这个直接设置的默认值存放到缓存，这样第二次到缓冲中获取就有值了，而不会继续访问数据库，这种办法最简单粗暴
        //      - 缓存预热:
        //          - 定时刷新缓存
        //      - 缓存更新:
        //          - redis缓存存失效策略: 6种
        //          - 定期刷新
        //      - 缓存降级:
        //          - 系统可以根据一些关键数据进行自动降级，也可以配置开关实现人工降级
        //          - 当访问量剧增、服务出现问题（如响应时间慢或不响应）或非核心服务影响到核心流程的性能时，仍然需要保证服务还是可用的，即使是有损服务
        //          - 服务降级的目的，是为了防止Redis服务故障，导致数据库跟着一起发生雪崩问题。因此，对于不重要的缓存数据，可以采取服务降级策略，例如一个比较常见的做法就是，Redis出现问题，不去数据库查询，而是直接返回默认值给用户

        // Memcache与Redis的区别都有哪些？
        // 1)、存储方式 Memecache把数据全部存在内存之中，断电后会挂掉，数据不能超过内存大小。 Redis有部份存在硬盘上，redis可以持久化其数据
        // 2)、数据支持类型 memcached所有的值均是简单的字符串，redis作为其替代者，支持更为丰富的数据类型 ，提供list，set，zset，hash等数据结构的存储
        // 3)、使用底层模型不同 它们之间底层实现方式 以及与客户端之间通信的应用协议不一样。 Redis直接自己构建了VM 机制 ，因为一般的系统调用系统函数的话，会浪费一定的时间去移动和请求。
        // 4). value 值大小不同：Redis 最大可以达到 1gb；memcache 只有 1mb。
        // 5）redis的速度比memcached快很多
        // 6）Redis支持数据的备份，即master-slave模式的数据备份。

        // (一)纯内存操作
        // (二)单线程操作，避免了频繁的上下文切换
        // (三)采用了非阻塞I/O多路复用机制
        //
        // redis的过期策略以及内存淘汰机制
        // redis采用的是定期删除+惰性删除策略。
        //      - 定期删除，redis默认每个100ms检查，是否有过期的key,有过期key则删除。需要说明的是，redis不是每个100ms将所有的key检查一次，而是随机抽取进行检查(如果每隔100ms,全部key进行检查，redis岂不是卡死)
        //      - 在你获取某个key的时候，redis会检查一下，这个key如果设置了过期时间那么是否过期了？如果过期了此时就会删除
        //      - 如果定期删除没删除key。然后你也没即时去请求key，也就是说惰性删除也没生效。这样，redis的内存会越来越高。那么就应该采用内存淘汰机制
        //          - maxmemory-policy volatile-lru
        //              - volatile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
        //              - volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
        //              - volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
        //              - allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰
        //              - allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
        //              - no-enviction（驱逐）：禁止驱逐数据，新写入操作会报错

        // (1) Master 最好不要做任何持久化工作，如 RDB 内存快照和 AOF 日志文件
        // (2) 如果数据比较重要，某个 Slave 开启 AOF 备份数据，策略设置为每秒同步一次
        // (3) 为了主从复制的速度和连接的稳定性， Master 和 Slave 最好在同一个局域网内
        // (4) 尽量避免在压力很大的主库上增加从库
        // (5) 主从复制不要用图状结构，用单向链表结构更为稳定，即： Master <- Slave1 <- Slave2 <-
        //
        // 讲解下Redis线程模型
        // 文件事件处理器包括分别是套接字、 I/O 多路复用程序、 文件事件分派器（dispatcher）、 以及事件处理器。使用 I/O 多路复用程序来同时监听多个套接字， 并根据套接字目前执行的任务来为套接字关联不同的事件处理器。当被监听的套接字准备好执行连接应答（accept）、读取（read）、写入（write）、关闭（close）等操作时， 与操作相对应的文件事件就会产生， 这时文件事件处理器就会调用套接字之前关联好的事件处理器来处理这些事件。
        // I/O 多路复用程序负责监听多个套接字， 并向文件事件分派器传送那些产生了事件的套接字。
        // 工作原理：
        // 1)I/O 多路复用程序负责监听多个套接字， 并向文件事件分派器传送那些产生了事件的套接字。
        // 尽管多个文件事件可能会并发地出现， 但 I/O 多路复用程序总是会将所有产生事件的套接字都入队到一个队列里面， 然后通过这个队列， 以有序（sequentially）、同步（synchronously）、每次一个套接字的方式向文件事件分派器传送套接字： 当上一个套接字产生的事件被处理完毕之后（该套接字为事件所关联的事件处理器执行完毕）， I/O 多路复用程序才会继续向文件事件分派器传送下一个套接字。如果一个套接字又可读又可写的话， 那么服务器将先读套接字， 后写套接字.
        // https://blog.csdn.net/Butterfly_resting/article/details/89668661

        // 哨兵模式
        // 在配置文件目录下新建 sentinel.conf 文件，名字绝不能错，然后配置相应内容
        // sentinel monitor 被监控机器的名字(自己起名字) ip地址 端口号 得票数
        // redis-sentinel /etc/redis/sentinel.conf
        // https://www.cnblogs.com/ysocean/p/9143118.html
    }

    public static void hanldeClient() {

        // https://www.cnblogs.com/liyan492/p/9858548.html

        // spring-data-redis
        // jedis
        //  - 是Redis的Java实现客户端，提供了比较全面的Redis命令的支持
        //  - 使用阻塞的I/O，且其方法调用都是同步的，程序流需要等到sockets处理完I/O才能执行，不支持异步。Jedis客户端实例不是线程安全的，所以需要通过连接池来使用Jedis。
        // letture
        //  - 高级Redis客户端，用于线程安全同步，异步和响应使用，支持集群，Sentinel，管道和编码器
        //  - 基于Netty框架的事件驱动的通信层，其方法调用是异步的。Redisson的API是线程安全的，所以可以操作单个Redisson连接来完成各种操作
        // reddsion
        //  - 实现了分布式和可扩展的Java数据结构
        //  - 基于Netty框架的事件驱动的通信层，其方法调用是异步的。Lettuce的API是线程安全的，所以可以操作单个Lettuce连接来完成各种操作
    }

    public static void handleCluster() {

        // https://www.cnblogs.com/silent2012/p/10697896.html
        // https://blog.csdn.net/tianshi_rain/article/details/86612193
        //
        // bind 172.16.71.183 一定要写本机ip并且建立集群的时候要用这个ip建立
        // port 6379
        // cluster-enabled yes 启动集群
        // cluster-config-file nodes.conf 节点信息，自动生成
        // cluster-node-timeout 5000 超时时间
        // appendonly yes 持久化
        // **建议增加 **
        // dir /var/user/redis-5.0.3/6379/ 文件路径
        // pidfile /var/run/redis_6379.pid pid位置
        // daemonize yes 守护线程模式（后台启动）
        // requirepass “CSFW” 访问密码
        // masterauth “CSFW” 主机密码
        // redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
    }

}

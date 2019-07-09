package Distributed;

public class MyKafka {

    public void handleBase() {
        // 1. 为什么用MQ
        // 2. MQ的优缺点
        // 3. 消息的可靠性
        // 4. MQ的高可用
    }

    public void hanldeDiff() {
        // https://www.cnblogs.com/haolujun/p/9632835.html
    }

    public void handleKafka() {
        // 高吞吐量的分布式发布订阅消息系统
        // https://juejin.im/post/5cf7847cf265da1b6c5f64fb
        // http://www.jasongj.com/2015/03/10/KafkaColumn1
        // Core
        //  - Producer：消息的产生者
        //      - Producer在写入数据的时候永远的找leader，不会直接将数据写入follower
        //  - Broker：Broker是kafka实例, 每个服务器上有一个或多个kafka的实例
        //  - Topic：消息的主题，kafka的数据就保存在topic, 每个broker上都可以创建多个topic。
        //  - Partition：Topic的分区，每个topic可以有多个分区，分区的作用是做负载，提高kafka的吞吐量。
        //      - 负载均衡
        //          - 写入时指定partition，如果有指定，则写入对应的partition
        //          - 未指定partition，但设置了数据的key，则会根据key的值hash出一个partition
        //          - 如果既没指定partition，又没有设置key，则会轮询选出一个partition
        //      - 如果往不存在的topic写数据，kafka会自动创建topic, 分区和副本的数量根据默认配置都是1
        //      - Partition在服务器上的表现形式就是一个一个的文件夹
        //          - 每个partition的文件夹下面会有多组segment文件
        //          - 每组segment文件又包含.index文件、.log文件、.timeindex文件（早期版本中没有）三个文件。
        //          - log文件就实际是存储message的地方，而index和timeindex文件为索引文件，用于检索消息。
        //  - Replication:每一个分区都有多个副本，副本的作用是做备胎
        //  - Message：每一条发送的消息主体。
        //      - ACK应答机制
        //          - 0: 代表producer往集群发送数据不需要等到集群的返回，不确保消息发送成功
        //          - 1: 代表producer往集群发送数据只要leader应答就可以发送下一条，只确保leader发送成功
        //          - 2: 代表producer往集群发送数据需要所有的follower都完成从leader的同步才会发送下一条
        //      - kafka就是利用分段+索引的方式来解决查找效率
        //      - offset：offset是一个占8byte的有序id号，它可以唯一确定每条消息在parition内的位置
        //      - 消息大小：消息大小占用4byte，用于描述消息的大小
        //      - 消息体：消息体存放的是实际的消息数据（被压缩过），占用的空间根据具体的消息而不一样。
        //      - 存储策略：
        //          - 无论消息是否被消费，kafka都会保存所有的消息
        //          - 基于时间，默认配置是168小时（7天）。
        //          - 基于大小，默认配置是1073741824。
        //      - kafka读取特定消息的时间复杂度是O(1)
        //          - segment+有序offset+稀疏索引+二分查找+顺序查找等多种手段来高效的查找数据
        //          - 新的版本中消费者消费到的offset已经直接维护在kafk集群的__consumer_offsets这个topic中
        //  - Consumer：消费者，即消息的消费方
        //      - Kafka采用的是点对点的模式，消费者主动的去kafka集群拉取消息，与producer相同的是，消费者在拉取消息的时候也是找leader去拉取
        //  - Consumer Group：将多个消费者组成一个消费者组，在kafka的设计中同一个分区的数据只能被消费者组中的某一个消费者消费
        //      - 如果是消费者组的消费者多于partition的数量，也不会出现多个消费者消费同一个partition的数据。
        //      - 一个Topic中的每个partitions，只会被一个”订阅者”中的一个consumer消费，不过一个consumer可以同时消费多个partitions中的消息。
        //      - 建议消费者组的consumer的数量与partition的数量一致
        //  - Zookeeper：kafka集群依赖zo服务器中应该充分利用多线程来处理执行逻辑okeeper来保存集群的的元信息，来保证系统的可用性。
        //
        // Q1: Kafka的topic和分区内部是如何存储的，有什么特点
        //  - 消息知会发送到其中一个分区
        //  - offset可以确定一条在该partition下的唯一消息
        // Q2: 与传统的消息系统相比,Kafka的消费模型有什么优点?
        //  - Kafka采取拉取模型(poll)，由自己控制消费速度，以及消费的进度。
        //  - consumer和producer都是使用的上面的单线程模式，单线程Selector
        //  - 在kafka服务端采用的是多线程的Selector模型
        // Q3: Kafka如何实现分布式的数据存储与数据读取?
        //
        // Shell
        //  - kafka-server-start -daemon /usr/local/etc/kafka/server.properties
        //  - kafka-topics --zookeeper 127.0.0.1:2181 --list
        //  - kafka-console-producer --topic  fanhehe --broker-list 127.0.0.1:9092
        //  - kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic fanhehe
    }

    public void handleRabbitMq() {
        // 除了Qpid，RabbitMQ是唯一一个实现了AMQP标准的消息服务器；
        // 可靠性，RabbitMQ的持久化支持，保证了消息的稳定性。
        // 持久化
        //  - 投递消息的时候durable设置为true，消息持久化
        //  - 消息已经到达持久化交换器上
        //  - 消息已经到达持久化的队列N
        // Core
        //  - VHost
        //  - Channel
        //  - Exchange
        //      - Direct exchange:
        //          - routing key 完全匹配
        //      - Fanout exchange
        //          - 发给绑定到交换机的所有队列
        //      - Topic exchange
        //          - routing key 规则匹配
        //      - Headers exchange
        //          - 无视routing key，按Header匹配
        //  - Queue
        //  - RoutingKey
        //  - BindingKey
    }
}

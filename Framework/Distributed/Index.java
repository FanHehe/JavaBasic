package Distributed;

public class Index {

    // - zookeeper (分布式协调服务)
    // - hadoop hdfs（分布式存储系统）
    // - spark（分布式计算系统）
    // - storm（分布式流式计算系统）
    // - elasticsearch（分布式搜索系统）
    // - kafka（分布式发布订阅消息系统）
    // - spring-cloud (分布式完整解决方案)
    public static void main(String[] args) {
        // CAP:
        // 一致性(Consistency) : 在分布式系统中的所有数据备份，在同一时刻是否有同样的值
        // 可用性(Availability) : 非故障的节点在合理的时间内返回合理的响应, 在集群中一部分节点故障后，集群整体是否还能响应客户端的读写要求
        // 分区容忍性(Partition Tolerance) : 系统如果不能在一定时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择
        //
        // BASE(AP的一种方式):
        // Basically Available(基本可用): 分布式系统在出现故障时，允许损失部分可用性，即保证核心可用
        // SofState(软状态): 允许系统存在中间状态，而该中间状态不会影响系统整体可用性。这里的中间状态就是CAP理论中的数据不一致。
        // 最终一致性(Eventual Consistency): 系统中的所有数据副本经过一定时间后，最终能够达到一致的状态。
    }
}

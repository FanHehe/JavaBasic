package Framework.Database.Mysql;

public class sharding {

    public static void handleCore() {
        // - Sql解析
        //  - 分库表达式: order_${0..1}: 分为两个表：order_0，order_1
        //  - 分表的表达式: order_${0..1}: 分为两个表：order_0，order_1
        // - Sql路由
        //  - 就是根据分表策略，找到具体要curd的表
        //  - 对于携带分片键的SQL, 根据分片键的不同可以划分为
        //      - 单片路由(分片键的操作符是=)
        //      - 多片路由(分片键的操作符是IN)
        //      - 范围路由(分片键的操作符是BETWEEN)
        //  - 不携带分片键的SQL则采用广播路由
        // - Sql改写
        //  - 将逻辑SQL改写为可以在真实数据库中正确执行的SQL。 它包括正确性改写和优化改写两部分
        // - Sql执行
        //  - ShardingSphere采用一套自动化的执行引擎，负责将路由和改写完成之后的真实SQL安全且高效发送到底层数据源执行。 它不是简单地将SQL通过JDBC直接发送至数据源执行；也并非直接将执行请求放入线程池去并发执行。它更关注平衡数据源连接创建以及内存占用所产生的消耗，以及最大限度地合理利用并发等问题。 执行引擎的目标是自动化的平衡资源控制与执行效率
        // - Sql归并
        //  -
    }

    public static void handleClassify() {
        // 水平分表
        //  - 避免单表行数过多，数据均摊拆分到更多表中，按照特定方式分表
        // 垂直分表
        //  - 字段过多，拆成多个表
        //
        //  proxy 层
        //
    }

    public static void handleShardingSphere() {
        // ShardingAlgorithm(分片算法)
        //  - PreciseShardingAlgorithm(精确分片)
        //      - 单一键
        //      - = or IN
        //      - 需要配合StandardShardingStrategy使用
        //  - RangeShardingAlgorithm(范围分片)
        //      - 单一键
        //      - BETWEEN AND
        //      - 需要配合StandardShardingStrategy使用
        //  - HintShardingAlgorithm
        //      - HintShardingStrategy
        //  - ComplexKeysShardingAlgorithm
        //      - 多键
        //      - ComplexShardingStrategy
        // ShardingStrategy(分片策略)
        //  - StandardShardingStrategy
        //      - 仅支持单一键
        //      - 支持 = IN BETWEEN OR
        //      - ShardingAlgorithm
        //          - PreciseShardingAlgorithm
        //          - RangeShardingAlgorithm
        //  - ComplexShardingStrategy
        //      - 复合分片
        //      - 提供对 =/IN/BetweenAnd的支持
        //      - 多建
        //  - NoneShardingStrategy
        //      - 不分片
        //  - HintShardingStrategy
        //      - Hint分片
        //  - InlineShardingStrategy
        //      - 行分片
        //      - 使用Groovy表达式
        //      - 单键分片
        //      - t_user_${user_id % 10}
        //
        //
        // Sharding jdbc是基于JDBC协议实现的
        // SpringShardingDataSource、ShardingConnection、ShardingPreparedStatement
        //
    }

    public static void handleProblem() {
        // 1.）分布式全局唯一id
        // 2.）分片规则和策略
        // 3.）跨分片技术问题
        // 4.）跨分片事物问题
        // https://www.cnblogs.com/qdhxhz/p/11651163.html
    }
}

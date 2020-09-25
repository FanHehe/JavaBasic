# 数据库

## 引擎

1. myisam
2. innodb
3. tokudb
    1. myisam
        .frm 用于存储表的定义
        .MYD 用于存放数据
        .MYI 用于存放表索引
    2. inodb
        .frm
        .ibd
## java组件

1. jpa
    JpaRepository & JpaSpecificationExecutor
2. mybatis
    sql
3. hibernate
    hql & sql & criteria

## 数据库连接池

1. Druid
2. Hikari

## 常用工具

1. binlog2sql

2. Percona Toolkit

## 主从 & 读写分离

### 主从

#### 主服务器

server-id=1

log-bin=mysql-bin

CREATE USER 'repl'@'123.57.44.85' IDENTIFIED BY 'slavepass'

GRANT REPLICATION SLAVE ON *.* TO 'repl'@'123.57.44.85'

flush privileges;

show master status;

#### 从服务器

server-id=2

CHANGE MASTER TO MASTER_HOST='182.92.172.80', MASTER_USER='rep1', MASTER_PASSWORD='slavepass', MASTER_LOG_FILE='mysql-bin.000003', MASTER_LOG_POS=73;

start slave;

stop slave;

show slave status;

### 读写分离

1. spring 是支持多数据源的，多个 datasource 放在一个 HashMapTargetDataSource中

2. 通过 dertermineCurrentLookupKey 获取 key 来觉定要使用哪个数据源

3. 在一个请求内都使用LocalThead存储当前的dataSource

4. @Override protected abstract Object determineCurrentLookupKey();

5. 使用sharding-sphere


## 分表分库

1. mycat
2. sharding-jdbc

    每种策略都对应一到两种分片算法（不分片策略NoneShardingStrategy除外）

    策略(ShardingStrategy->doSharding):
        NoneShardingStrategy
            - 无分片策略
        InlineShardingStrategy
            - 使用Groovy的Inline表达式，提供对SQL语句中的=和IN的分片操作支持
        StandardShardingStrategy
            - StandardShardingStrategy只支持单分片键，提供PreciseShardingAlgorithm（精准分片 = in）和RangeShardingAlgorithm（范围分片 between ）两个分片算法
        HintShardingStrategy
            -
        ComplexShardingStrategy
            - ComplexShardingStrategy支持多分片键
            - https://blog.csdn.net/womenyiqilalala/article/details/106115560
    算法（ShardingAlgorithm->doSharding）
        RangeShardingAlgorithm
        PreciseShardingAlgorithm
        HintShardingAlgorithm
        ComplexKeysShardingAlgorithm

## 版本特性

> https://www.cnblogs.com/gered/p/11445219.html

5.5
    1. InnoDB 作为默认存储引擎
    2. 支持半同步复制
        2.1 而半同步复制则是主库需要有至少一个半同步从库，当一次写入操作进行之后
        2.2 至少在主库和至少一个半同步从库上都完成了写入之后，用户才会收到已成功的信息
        2.3 半同步复制在这一程度上提高了数据的安全性
5.6
    1. 表中可以设置多个Timestamp属性
    2. InnoDB 支持全文索引, 倒排索引的设计
    3. 多线程复制
    4. 加入全局事务ID
    5. 优化器性能提升，引入了ICP，MRR，BKA等特性，针对子查询进行了优化
5.7
    1. 增强的多线程复制
    2. 多源复制, 即将多个主库的数据归并到一个从库的实例上
    3. JSON数据类型操作
    4. innodb_buffer_pool_size参数动态修改
    5. 建议使用mysqld --initialize完成实例初始化
8
    0. 高性能：官宣比5.7快2倍
    1. 增强账号管理的功能，提供角色这一概念，即能组合权限，批量授权给某一用户
    2. 增强的InnoDB
        2.1 自增id会写入到redo log中，这一改动使得数据库重启之后的自增值能恢复到重启前的状态
        2.2 增加了死锁检测开关innodb_deadlock_detect，可以在高并发系统中动态调整这一特性，提升性能
    3. 增加了->>操作符，使用这一操作符等同于对JSON_EXTRACT的结果进行
    4. GROUP BY语句不再隐式排序

案例分析

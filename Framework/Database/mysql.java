package Framework.Database;

class mysql {

    public static void handleExplain() {
        // select_type: SELECT 查询的类型

        //     SIMPLE, 表示此查询不包含 UNION 查询或子查询
        //     PRIMARY, 表示此查询是最外层的查询
        //     UNION, 表示此查询是 UNION 的第二或随后的查询
        //     DEPENDENT UNION, UNION 中的第二个或后面的查询语句, 取决于外面的查询
        //     UNION RESULT, UNION 的结果
        //     SUBQUERY, 子查询中的第一个 SELECT
        //     DEPENDENT SUBQUERY: 子查询中的第一个 SELECT, 取决于外面的查询. 即子查询依赖于外层查询的结果.

        // type:

        //     system: 表中只有一条数据. 这个类型是特殊的 const 类型.
        //     const: 针对主键或唯一索引的等值查询扫描, 最多只返回一行数据. const 查询速度非常快, 因为它仅仅读取一次即可.
        //     eq_ref: 此类型通常出现在多表的 join 查询, 表示对于前表的每一个结果, 都只能匹配到后表的一行结果. 并且查询的比较操作通常是 =, 查询效率较高
        //     ref: 使用了最左前缀 规则索引的查询，或者是此类型通常出现在多表的 join 查询, 针对于非唯一或非主键索引
        //     range: 表示使用索引范围查询, 通过索引字段范围获取表中部分数据记录. 这个类型通常出现在 =, <>, >, >=, <, <=, IS NULL, <=>, BETWEEN, IN() 操作中. 当 type 是 range 时, 那么 EXPLAIN 输出的 ref 字段为 NULL, 并且 key_len 字段是此次查询中使用到的索引的最长的那个.
        //     index: 表示全索引扫描(full index scan), 和 ALL 类型类似, 只不过 ALL 类型是全表扫描, 而 index 类型则仅仅扫描所有的索引, 而不扫描数据. index 类型通常出现在: 所要查询的数据直接在索引树中就可以获取到, 而不需要扫描数据. 当是这种情况时, Extra 字段 会显示 Using index.
        //     ALL: 表示全表扫描, 这个类型的查询是性能最差的查询之一. 通常来说, 我们的查询不应该出现 ALL 类型的查询, 因为这样的查询在数据量大的情况下, 对数据库的性能是巨大的灾难. 如一个查询是 ALL 类型查询, 那么一般来说可以对相应的字段添加索引来避免.

        //     ALL < index < range ~ index_merge < ref < eq_ref < const < system

        // key

        // extra: 额外的信息
        //     Using filesort
        //     Using index : "覆盖索引扫描", 表示查询在索引树中就可查找所需数据, 不用扫描表数据文件, 往往说明性能不错
        //     Using temporary
        //     No tables used
        //     Using Where
    }

    public static void hanldeFanshi() {
        // 第一范式：1NF是对属性的原子性约束，要求字段具有原子性，不可再分解；(只要是关系型数据库都满足1NF)
        // 第二范式：2NF是在满足第一范式的前提下，非主键字段不能出现部分依赖主键；解决：消除复合主键就可避免出现部分以来，可增加单列关键字。
        // 第三范式：3NF是在满足第二范式的前提下，非主键字段不能出现传递依赖，比如某个字段a依赖于主键，而一些字段依赖字段a，这就是传递依赖。解决：将一个实体信息的数据放在一个表内实现
    }

    public static void handleCompare() {
        // 1）InnoDB支持事务，MyISAM不支持。
        // 2）MyISAM适合查询以及插入为主的应用，InnoDB适合频繁修改以及涉及到安全性较高的应用。
        // 3）InnoDB支持外键，MyISAM不支持。
        // 4）从MySQL5.5.5以后，InnoDB是默认引擎。
        // 5）MyISAM支持全文类型索引，而InnoDB不支持全文索引。
        // 6）InnoDB中不保存表的总行数，select count(*) from table时，InnoDB需要扫描整个表计算有多少行，但MyISAM只需简单读出保存好的总行数即可。注：当count(*)语句包含where条件时MyISAM也需扫描整个表。
        // 7）对于自增长的字段，InnoDB中必须包含只有该字段的索引，但是在MyISAM表中可以和其他字段一起建立联合索引。
        // 8）清空整个表时，InnoDB是一行一行的删除，效率非常慢。MyISAM则会重建表。MyisAM使用delete语句删除后并不会立刻清理磁盘空间，需要定时清理，命令：OPTIMIZE table dept;
        // 9）InnoDB支持行锁（某些情况下还是锁整表，如 update table set a=1 where user like ‘%lee%’）
        // 10）Myisam创建表生成三个文件：.frm 数据表结构 、 .myd 数据文件 、 .myi 索引文件，Innodb只生成一个 .frm文件，数据存放在ibdata1.log
        // 现在一般都选用InnoDB，主要是MyISAM的全表锁，读写串行问题，并发效率锁表，效率低，MyISAM对于读写密集型应用一般是不会去选用的。
        // 应用场景：

        // MyISAM不支持事务处理等高级功能，但它提供高速存储和检索，以及全文搜索能力。如果应用中需要执行大量的SELECT查询，那么MyISAM是更好的选择。
        // InnoDB用于需要事务处理的应用程序，包括ACID事务支持。如果应用中需要执行大量的INSERT或UPDATE操作，则应该使用InnoDB，这样可以提高多用户并发操作的性能。
    }

    public static void handleMVCC() {

        //
        // DB_TRX_ID：数据行的版本号
        // DB_ROLL_PT：删除版本号
        // 在每一行数据中额外保存两个隐藏的列：当前行创建时的版本号和删除时的版本号（可能为空，其实还有一列称为回滚指针，用于事务回滚，不在本文范畴）。
        // 这里的版本号并不是实际的时间值，而是系统版本号。
        // 每开始新的事务，系统版本号都会自动递增。事务开始时刻的系统版本号会作为事务的版本号，用来和查询每行记录的版本号进行比较。
        // 每个事务又有自己的版本号，这样事务内执行CRUD操作时，就通过版本号的比较来达到数据版本控制的目的。
        //
        // Innodb引擎的undo日志是记录在表空间中单独的回滚段中。当mysql做update和delete操作的时候，实际的后台都是先把旧记录“删”了，如果是update和insert再把新记录“插入”进去。
        // 这里的删不是真的删除，而是标识它被删除了。而插入也不一定是真的插入，很多情况下是原地覆盖原来的记录。
        //
        // UNDO LOG
        // 事务未提交之前，Undo保存了未提交之前的版本数据，Undo中的数据可作为数据旧版本快照供其他并发事务进行快照读取
        // 事务1修改user表中记录，mysql会将user表中的这条记录备份到Undo buffer
        // 如果rollback，mysql会重新将Undo buffer中的这条记录写回到user表中
        // 同时如果多个事务来读取这条记录，会从Undo buffer中读取这条记录（称为这条记录的快照）


    }


    public static void handleLog() {
        // undo-log
        // undo意为取消，以撤销操作为目的，返回指定摸个状态的操作, undo log指事务开始之前，在操作任何数据之前，首先将需要操作的数据备份到一个地方
        // 事务未提交之前，Undo保存了未提交之前的版本数据，Undo中的数据可作为数据旧版本快照供其他并发事务进行快照读取
        // redo-log
        // Redo(重做)，以恢复操作为目的，重现操作，Redo Log 指事务操作的任何数据，将最新的数据备份到一个地方（Redo Log）
        //
        // Redo buffer持久化Redo log的策略，innodb_flush_log_at_trx_commit
        // 取值0，每秒提交Redo buffer–> Redo log OS cache --> flush cache to disk[可能丢失一秒内的事务数据]
        // 取值1（默认值），每次事务提交执行Redo buffer --> Redo log OS cache --> flush cache to disk[最安全，性能最差]
        // 取值2，每次事务提交执行Redo buffer --> Redo log OS cache 再每隔一秒–> flush cache to disk

        //
    }

}

# Hibernate

## 参考
> HQL数据查询基础：https://www.jianshu.com/p/9586275c30b7
> HQL数据查询基础：https://www.cnblogs.com/quchengfeng/p/4111749.html
> 面试题：https://juejin.im/post/5aa2661bf265da238a300b80
> 超全面 hibernate 复习总结笔记：https://juejin.im/entry/58ef8822570c35005620201c

## 用法

### 引入

```gradle
compile "org.hibernate:hibernate-core:5.2.9.Final"
```

### 核心配置

```xml
<!-- hibernate.cfg.xml -->
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <!-- SessionFactory，相当于之前学习连接池配置 -->
    <session-factory>
        <!-- 1 基本4项 -->
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql:///db01</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">1234</property>

        <!-- 2 与本地线程绑定 -->
        <property name="hibernate.current_session_context_class">thread</property>

         <!-- 3 方言：为不同的数据库，不同的版本，生成sql语句（DQL查询语句）提供依据
            * mysql 字符串 varchar
            * orcale 字符串 varchar2
        -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>

        <!-- 4 sql语句 -->
        <!-- 显示sql语句 -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>
    </session-factory>
</hibernate-configuration>
```

### 实体

```java

// 省略 get set 方法
public class User {
    private Integer uid;
    private String username;
    private String password;
}
```

## 映射实体配置

### XML

```xml
<!-- user.hbm.xml -->
<!DOCTYPE hibernate-mapping PUBLIC
    "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <class name="...User" table="t_user">
        <!-- 主键 -->
        <id name="uid">
            <generator class="native"></generator>
        </id>

        <!-- 普通属性 -->
        <property name="username"></property>
        <property name="password"></property>

    </class>
</hibernate-mapping>
<!-- 核心配置文件中加上映射文件位置 -->
<!-- <mapping resource=".../user.hbm.xml"/> -->
```

### 注解

```java
// name 对应表名称
@Entity
@Table(name = "user")
public class User {
    // 主键
    @Id
    @GeneratedValue
    private Integer uid;
    private String username;
    private String password;
// 省略 get set 方法
}

// 在核心配置文件中加上映射文件位置
// <mapping class="...User" />
```

## 测试

```java
public class HelloWorld {
    @Test
    public void hello() {
        // username, password
        User user = new User("123456", "123");
        // 1. 加载配置文件, hibernate.properties
        Configuration configure = new Configuration().configure();
        // 2. 获得 session factory对象, hibernate.cfg.xml
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 3. 创建 session 加载指定配置文件
        // 3.1 获取和当前线程绑定的会话: factory.getCurrentSession();
        //  3.1.1 <property name="hibernate.current_session_context_class">thread</property>
        Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction transaction = session.beginTransaction();
        // 5.保存并提交事务
        session.save(user);
        transaction.commit();
        // 6.释放资源
        session.close();
        sessionFactory.close();
    }
}
```

### 核心配置

- Configuration
- SessionFactory
- Session
- Transaction
- Query(hql)
    ```java
    // 区分大小写
    // https://www.jianshu.com/p/9586275c30b7
    // https://www.cnblogs.com/quchengfeng/p/4111749.html
    String hql = "from User";
    String hql = "from User";
    String hql = "from user where userId in (:id1, :id2)";
    Query query = session
        .createQuery(hql)
        .setParameter("id1", id2)
        .setParameter("id2", id2);
    List list = query.list();
    // list() 查询所有
    // uniqueResult() 获得一个结果。如果没有查询到返回null，如果查询多条抛异常。
    // setFirstResult(int) 分页，开始索引数startIndex
    // setMaxResults(int) 分页，每页显示个数 pageSize
    ```
- Criteria[kraɪ'tɪriə]
    QBC (query by criteria）(直接使用PO对象)
    ```java
    // https://www.cnblogs.com/xtdxs/p/6575824.html
    Criteria criteria = session.createCriteria(User.class);

    criteria.add(Restrictions.eq("username", "tom"));

    // Restrictions.gt(propertyName, value) 大于
    // Restrictions.ge(propertyName, value) 大于等于
    // Restrictions.lt(propertyName, value) 小于
    // Restrictions.le(propertyName, value) 小于等于
    // Restrictions.like(propertyName, value) 模糊查询，注意：模糊查询值需要使用 % _
    ```
-

### 用法

```java
class HibernateUtils {
    // 线程安全
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration().configure();

        sessionFactory = configuration.buildSessionFactory();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                sessionFactory.close();
            }
        });
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void main(Stirng[] args) {
        Session session = getSession();
        session.close();
    }
}
```

<!-- 瞬时态(transiant),持久态(persistent),游离态(detached) -->

# 瞬时态 -> (save/saveOrUpdate)-> 持久态
# 持久态 -> (delete) -> 成瞬时态
# 持久态 -> (close/clear/evict) -> 游离态
# 游离态 -> (update/saveOrUpdate/lock) -> 持久态

> lazy: https://www.cnblogs.com/zanglitao/p/3818098.html


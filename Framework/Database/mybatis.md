# mybatis

## 参考

## 用法

> 技巧: http://www.javacoder.cn
> 实战: https://blog.csdn.net/hellozpc/article/details/80878563
> 文档: http://www.mybatis.org/mybatis-3/zh/configuration.html
> 面试题: https://blog.csdn.net/a745233700/article/details/80977133

### 基本

```java
String resource = "mybatis-config.xml";
// 读取配置文件
InputStream is = Resources.getResourceAsStream(resource);
// 构建SqlSessionFactory
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
// 获取sqlSession
SqlSession sqlSession = sqlSessionFactory.openSession();
```

### 基本增删改查

```java
// mybatis
// namespace + mapperId
this.sqlSession.insert("UserDao.insertUser", user);
this.sqlSession.update("UserDao.insertUser", user);
this.sqlSession.delete("UserDao.insertUser", user);

this.sqlSession.selectOne("UserDao.queryUserById", id);
this.sqlSession.selectList("UserDao.queryUserAll");
```

```java
// mybatis spring
```

```java
// tk.mybatis
```

### 事务



## 问题

### 如何解决db内是下划线，而POJO是驼峰

1. 在sql语句中使用别名：select user_nick as userNick from ...
2. ResultMap.mapper具体的配置的时候
3. <setting name="mapUnderscoreToCamelCase" value="true"/>


> 在spring boot中 mybatis.configuration.map-underscore-to-camel-case=true

### #{}和${}

```java

// 1. #是将传入的值当做字符串的形式，eg:select id,name,age from student where id =#{id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from student where id ='1'.

// 2. $是将传入的数据直接显示生成sql语句，eg:select id,name,age from student where id =${id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from student where id = 1.

// 3. 使用#可以很大程度上防止sql注入。(语句的拼接  #{xxx},使用的是PreparedStatement,会有类型转换，比较安全 简单的说就是#{}是经过预编译的，是安全的，${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在SQL注入。）

// 4 但是如果使用在order by 中就需要使用 $.

// 5. #与$的区别最大在于：#{} 传入值时，sql解析时，参数是带引号的，而${}传入值，sql解析时，参数是不带引号的。
```


### 核心配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
      PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <!-- 根标签 -->
<configuration>
    <properties>
        <property name="driver"
            value="com.mysql.jdbc.Driver"/>
        <property name="url"
            value="jdbc:mysql://localhost:3306/db?useUnicode=true&amp;characterEncoding=utf-8&amp;allowMultiQueries=true"/>
        <property name="username"
            value="root"/>
        <property name="password"
                value="123456"/>
    </properties>

   <!-- 环境，可以配置多个，default：指定采用哪个环境 -->
   <environments default="development">
      <environment id="development">
         <!-- 事务管理器，JDBC类型的事务管理器 -->
         <transactionManager type="JDBC" />
         <!-- 数据源，池类型的数据源 -->
         <dataSource type="POOLED">
            <property name="url" value="${url}" />
            <property name="driver" value="${driver}" />
            <property name="username" value="${username}" />
            <property name="password" value="${password}" />
         </dataSource>
      </environment>
   </environments>
</configuration>
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

### 缓存

#### 一级缓存

session基本，打开session后，执行相同的sql(同语句，同参数) 则会被命中（默认开启，无法关闭）。

#### 二级缓存

mybatis的二级缓存的作用域是Mapper的Namespace，同一个namespace中查询sql可以从缓存中命中。

```xml
<!-- 开启二级缓存 -->
<!-- 开启二级缓存，必须序列化： -->

<mapper namespace="com.zpc.mybatis.dao">
    <cache
        eviction = "FIFO / LRU / SOFT / WEAK"
        flushInterval = "6000 毫秒"
        size = "512"
        readOnly = "true"
        />

    <!-- eviction: 默认LRU-->
</mapper>

<!-- 全局总开关 -->
<settings>
    <!--开启驼峰匹配-->
    <setting name="mapUnderscoreToCamelCase" value="true"/>
    <!--开启二级缓存,全局总开关，这里关闭，mapper中开启了也没用-->
    <setting name="cacheEnabled" value="false"/>
</settings>
```

### 插件

```java

// 生命周期

Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
ParameterHandler (getParameterObject, setParameters)
ResultSetHandler (handleResultSets, handleOutputParameters)
StatementHandler (prepare, parameterize, batch, update, query)

// ExamplePlugin.java
@Intercepts({@Signature(
  type= Executor.class,
  method = "update",
  args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {}
}
```

### 配置

```xml
<!-- mybatis-config.xml -->
<plugins>
  <plugin interceptor="org.mybatis.example.ExamplePlugin">
    <property name="someProperty" value="100"/>
  </plugin>
</plugins>
```

### 动态sql

#### if 、choose when otherwise、where set、foreach

```xml
<select id="queryUserList" resultType="com.zpc.mybatis.pojo.User">
    select * from tb_user WHERE sex=1
    <if test="name!=null and name.trim()!=''">
      and name like '%${name}%'
    </if>
</select>

```

#### choose when otherwise

```xml
<select id="queryUserListByNameOrAge" resultType="com.zpc.mybatis.pojo.User">
    select * from tb_user WHERE sex=1
    <!--
    1.一旦有条件成立的when，后续的when则不会执行
    2.当所有的when都不执行时,才会执行otherwise
    -->
    <choose>
        <when test="name!=null and name.trim()!=''">
            and name like '%${name}%'
        </when>
        <when test="age!=null">
            and age = #{age}
        </when>
        <otherwise>
            and name='鹏程'
        </otherwise>
    </choose>
</select>
```
#### where

```xml
<select id="queryUserListByNameAndAge" resultType="com.zpc.mybatis.pojo.User">
    select * from tb_user
    <!--如果多出一个and，会自动去除，如果缺少and或者多出多个and则会报错-->
    <where>
        <if test="name!=null and name.trim()!=''">
            and name like '%${name}%'
        </if>
        <if test="age!=null">
            and age = #{age}
        </if>
    </where>
</select>
```

#### set

```xml
<update id="updateUser" parameterType="com.zpc.mybatis.pojo.User">
    UPDATE tb_user
    <trim prefix="set" suffixOverrides=",">
        <if test="userName!=null">user_name = #{userName},</if>
        <if test="password!=null">password = #{password},</if>
        <if test="name!=null">name = #{name},</if>
        <if test="age!=null">age = #{age},</if>
        <if test="sex!=null">sex = #{sex},</if>
        <if test="birthday!=null">birthday = #{birthday},</if>
        updated = now(),
    </trim>
    WHERE
    (id = #{id});
</update>
```

#### foreach

```xml
<select id="queryUserListByIds" resultType="com.zpc.mybatis.pojo.User">
    select * from tb_user where id in
    <foreach collection="ids" item="id" open="(" close=")" separator=",">
        #{id}
    </foreach>
</select>
```

### 高级查询

#### one-one

> association

##### 嵌套查询和分段查询

```xml
<resultMap id="OrderUserResultMap" type="com.zpc.mybatis.pojo.Order" autoMapping="true">
     <id column="id" property="id"/>
     <!--association:完成子对象的映射-->
     <!--property:子对象在父对象中的属性名-->
     <!--javaType:子对象的java类型-->
     <!--autoMapping:完成子对象的自动映射，若开启驼峰，则按驼峰匹配-->
     <association property="user" javaType="com.zpc.mybatis.pojo.User" autoMapping="true">
         <id column="user_id" property="id"/>
     </association>
 </resultMap>

 <select id="queryOrderWithUserByOrderNumber" resultMap="OrderUserResultMap">
   select * from tb_order o left join tb_user u on o.user_id=u.id where o.order_number = #{number}
</select>
```

#### one-many

```xml
<resultMap id="OrderUserDetailResultMap" type="com.zpc.mybatis.pojo.Order" autoMapping="true">
    <id column="id" property="id"/>
    <!--collection:定义子对象集合映射-->
    <!--association:完成子对象的映射-->
    <!--property:子对象在父对象中的属性名-->
    <!--javaType:子对象的java类型-->
    <!--autoMapping:完成子对象的自动映射，若开启驼峰，则按驼峰匹配-->

    <!-- 映射一对一的User -->
    <association property="user" javaType="com.zpc.mybatis.pojo.User" autoMapping="true">
        <id column="user_id" property="id"/>
    </association>

    <!-- 映射一对多的detailList -->
    <collection property="detailList" javaType="List" ofType="com.zpc.mybatis.pojo.OrderDetail" autoMapping="true">
        <id column="id" property="id"/>
    </collection>

</resultMap>

<!-- 使用链接 避免出现one-many的N + 1次查询 -->
<select id="queryOrderWithUserAndDetailByOrderNumber" resultMap="OrderUserDetailResultMap">
   select * from tb_order o
   left join tb_user u on o.user_id=u.id
   left join tb_orderdetail od on o.id=od.order_id
   where o.order_number = #{number}
</select>

```

#### many-one

#### many-many

```xml
<resultMap id="OrderUserDetailItemResultMap" type="com.zpc.mybatis.pojo.Order" autoMapping="true">
    <id column="id" property="id"/>


    <!-- 映射一对一的user -->
    <association property="user" javaType="com.zpc.mybatis.pojo.User" autoMapping="true">
        <id column="user_id" property="id"/>
    </association>

    <!-- 映射一对多的detailList -->
    <collection property="detailList" javaType="List" ofType="com.zpc.mybatis.pojo.OrderDetail" autoMapping="true">
        <id column="detail_id" property="id"/>
        <!-- 映射其中的item -->
        <association property="item" javaType="com.zpc.mybatis.pojo.Item" autoMapping="true">
            <id column="item_id" property="id"/>
        </association>
    </collection>
</resultMap>

 <select id="queryOrderWithUserAndDetailItemByOrderNumber" resultMap="OrderUserDetailItemResultMap">
   select * ,od.id as detail_id from tb_order o
   left join tb_user u on o.user_id=u.id
   left join tb_orderdetail od on o.id=od.order_id
   left join tb_item i on od.item_id=i.id
   where o.order_number = #{number}
</select>
```

### resultMap

#### 继承

使用extends属性

### resultType

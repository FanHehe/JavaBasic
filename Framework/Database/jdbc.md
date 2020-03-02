## JDBC

### 参考

> 面试题：https://www.cnblogs.com/kevinf/p/3705148.html
> 连接池：https://cloud.tencent.com/developer/article/1438233
> DBUtil：https://blog.csdn.net/langao_q/article/details/81052042

### 用法
```java

class DBUtil {
    static {
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * 获取连接池
     * @param  schema       连接schema
     * @param  user         用户名
     * @param  password     密码
     * @return              连接对象
     * @throws SQLException 异常
     */
    public static Connection getConnection (String schema, String user, String password) throws SQLException {
        return DriverManager.getConnection(scheme, user, password);
    }

    /**
     * 支持批量更新,批量删除
     * 普通的不带参的查询SQL
     * @throws SQLException SQLException
     */
    public static void testCreateStatement() throws SQLException {

        String select = "select * from user";
        String insert = "insert into user (nick) values (" + name + ")";
        String update = "update user set nick = 'name'";
        String delete = "delete from user where nick = 'name'"

        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        // select
        ResultSet rs = statement.executeQuery(select);

        while(rs.next()) {
            rs.getInt("id");
            rs.getString("nick");
        }

        // execute 可执行任何sql语句
        // insert / update / delete
        boolean success = statement.execute(update);
        boolean success = statement.execute(insert);
        boolean success = statement.execute(detele);

        // executeUpdate 用户执行DLL or CUD，返回影响行数
        int rows = statement.executeUpdate(update);
        int rows = statement.executeUpdate(insert);
        int rows = statement.executeUpdate(detele);
    }

    /**
     * 支持批量更新,批量删除
     * 安全性好，有效防止Sql注入等问题
     * 可变参数的SQL,编译一次,执行多次,效率高
     * @throws SQLException  SQLException
     */
    public static void testPreparedStatement() throws SQLException {
        Connection connection = getConnection();

        String select = "select * from user where id = ?";

        PreparedStatement ps = connection.prepareStatement(select);

        ps.setInt(1, 2);

        ResultSet rs = ps.executeQuery();
    }

    /**
     * 继承自PreparedStatement, 支持带参数的SQL操作;
     * 支持调用存储过程,提供了对输出和输入/输出参数(INOUT)的支持;
     * @throws SQLException [description]
     */
    public static void testCallableStatement() throws SQLException {

    }
}
```

### 连接池

- 自定义连接池

- c3p0连接池
    - https://cloud.tencent.com/developer/article/1438233
- druid

### 面试题

> https://www.cnblogs.com/kevinf/p/3705148.html

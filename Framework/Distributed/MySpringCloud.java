package Distributed;

public class MySpringCloud {

    public static void handleRaft() {
        // https://www.cnblogs.com/xybaby/p/10124083.html
    }

    public static void handlePaxos() {
        // https://my.oschina.net/u/150175/blog/2992187
    }

    public static void handleComponent() {
        // https://juejin.im/post/5be13b83f265da6116393fc7
        // https://blog.csdn.net/weixin_43650254/article/details/95381194
        // - 服务注册与发现
        //  - Eureka
        //      - Eureka 是微服务架构中的注册中心
        //      - Eureka Client组件，负责将这个服务的信息注册到Eureka Server中
        //      - Eureka Server，注册中心，里面有一个注册表，保存了各个服务所在的机器和端口号
        //      - https://blog.csdn.net/u013679744/article/details/79222103
        //      - https://my.oschina.net/u/150175/blog/2992187
        //  - Consul
        //      - 采用Raft算法保证服务的一致性，且支持健康检查
        //      - Consul采用主从模式的设计，使得集群的数量可以大规模扩展，集群间通过RPC的方式调用(HTTP和DNS)
        //  - Zookeeper
        //      - Zookeeper是由Google开源的在Java语言上实现的分布式协调服务，是Hadoop和Hbase的重要组件，提供了数据/发布订阅、负载均衡、分布式同步等功能
        //      - ZooKeeper Atomic Broadcast protocol（Zab协议）是专门设计给Zookeeper用于实现分布式系统数据的一致性，是在Paxos算法基础上发展而来。
        // Feign
        //  - @FeignClient("Service-A")
        //  - public class AService {
        //  -   @RequestMapping(value="/reduce/{id}", method=HttpMethod.PUT)
        //  -   public Result invoke(...) {}
        //  - }
        //  - 封装发送请求的细节
        // Ribbon
        //  - 负载均衡
        //  - Round Robin轮询算法
        // Hystrix
        //  - 隔离：不同服务使用不同的线程池，互不影响
        //  - 限流：
        //  - 熔断：服务不可用，则直接返回，不会阻塞到超时，隔N次/N个单位时间再重试，避免很多请求一直阻塞到超时。
        //  - 降级：callback
        // Zuul
        //  - 负责网络路由
        //  - 网关作用
        //  - 根据请求中的一些特征，将请求转发给后端的各个服务
        //  - 有一个网关之后，比如可以做统一的降级、限流、认证授权、安全，等等。
    }


}

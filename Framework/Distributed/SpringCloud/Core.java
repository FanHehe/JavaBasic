package Framework.Distributed.SpringCloud;

public class Core {
    public static void eureka() {
        // Eureka 是弱数据一致性，选择了 CAP 中的 AP。
        // Eureka 采用 Peer to Peer 模式进行数据复制。
        //  - 复制算法是依赖增量复制 + 全量复制实现的
        //  - 初收到心跳包的eureka-server的isReplication为false，因此会把节点信息往其他的eureka-server结点传递
        //  - 当传递到下一个结点的时候isReplication已经为true，表示该结点信息由其他eureka-server结点复制过来，这时候下一个结点就不会继续往下传递。这主要是为了避免造成死循环
        // Eureka 通过 lastDirtyTimestamp 来解决复制冲突。
        // Eureka 通过心跳机制实现数据修复。
        //  - client
        //      - registerWithEureka: false (关闭eureka的客户端行为:注册服务)
        //      - fetchRegistry: false（关闭eureka的客户端行为:订阅服务）
        //  - server
        //      - enable-self-preservation: false (关闭自我保护机制，防止失效的服务也被一直访问)
        //      - eviction-interval-timer-in-ms: 10000 (每隔10s扫描服务列表，该配置可以修改检查失效服务的时间)
        //
        // - region & zone
        //  - 服务注册和发现都会按先指定的Region，然后根据指定的Region寻找第一个Zone
        //  - Zone相当于组成Region的基本单元
        //  - region、availability-zones、service-url.zone、prefer-same-zone-eureka
        //
        // 集群
        // - 通过service-url互相绑定请求路径即可
        //
        // 心跳
        //  - eureka.instance.lease-expiration-duration-in-seconds=90 第一个配置用来定义服务失效时间，默认为90秒
        //  - eureka.instance.lease-renewal-interval-in-seconds=30 服务续约的间隔时间
        //  - eureka.client.registry-fetch-interval-seconds=30 服务消费者获取服务注册中心上面的服务提供者列表默认30秒更新一次
        //  - eureka.client.fetch-registry=true 服务消费端要确保具有获取服务提供者的能力
        //  - eureka.server.enable-self-preservation=false Eureka Server在运行期间会去统计心跳失败比例在15分钟之内是否低于85%，如果低于85%，Eureka Server会将这些实例保护起来，触发保护机制
        //  - eviction-interval-timer-in-ms: 5000 每隔10s扫描服务列表，该配置可以修改检查失效服务的时间，每隔10s检查失效服务，并移除列表
        //  - registry-fetch-interval-seconds: 30 示eureka client间隔多久去拉取服务器注册信息,默认为30秒
        //  - lease-renewal-interval-in-seconds 心跳间隔时间, 默认是30秒
        //  - lease-expiration-duration-in-seconds: 最后一次心跳时间后leaseExpirationDurationInSeconds秒就认为是下线了，默认是90秒
    }

    public static void feign() {
        // new feign.Retry.Default(long period, long maxPeriod, int maxAttempts)
        // feign.hystrix.enabled=true
        // feign.okhttp.enabled=true
        // feign.httpclient.connection-timeout=2000
        // feign.httpclient.follow-redirects=true
        // feign.httpclient.disable-ssl-validation=true
    }

    public static void hystrix() {

    }

    public static void ribbon() {
        // OkToRetryOnAllOperations: true #对所有请求都进行重试
        // MaxAutoRetriesNextServer: 2 #切换实例的重试次数
        // MaxAutoRetries: 1 #对当前实例的重试次数
        // NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule       # 随机
        // # NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule # 轮询
        // # NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RetryRule      # 重试
        // # NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule # 响应时间权重
        // # NFLoadBalancerRuleClassName: com.netflix.loadbalancer.BestAvailableRule        # 最空闲连接策略
    }
}

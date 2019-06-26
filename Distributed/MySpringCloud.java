package Distributed;

public class MySpringCloud {
    public static void base() {
        // https://juejin.im/post/5be13b83f265da6116393fc7
        // Eureka
        //  - Eureka是微服务架构中的注册中心
        //  - Eureka Client组件，负责将这个服务的信息注册到Eureka Server中
        //  - Eureka Server，注册中心，里面有一个注册表，保存了各个服务所在的机器和端口号
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

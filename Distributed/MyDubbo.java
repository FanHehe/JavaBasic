package Distributed;

public class MyDubbo {
    public static void base() {
        // Registry：服务注册，我们一般会采取Zookeeper 作为我们的注册中心
        // Provider：服务提供者（生产者），提供具体的服务实现
        // Consumer：消费者，从注册中心中订阅服务
        // Monitor：监控中心，RPC调用次数和调用时间监控
    }
}

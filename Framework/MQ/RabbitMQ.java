class RabbitMQ {
    void handleBase() {
        // 1.解耦，系统A在代码中直接调用系统B和系统C的代码，如果将来D系统接入，系统A还需要修改代码，过于麻烦！

        // 2.异步，将消息写入消息队列，非必要的业务逻辑以异步的方式运行，加快响应速度

        // 3.削峰，并发量大的时候，所有的请求直接怼到数据库，造成数据库连接异常
    }

    void handleKey() {
        // Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
        // Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
        //  - direct(默认) 完全匹配routingKey
        //  - fanout: 不管消息的ROUTING_KEY设置为什么，Exchange都会将消息转发给所有绑定的Queue
        //  - topic: 主题交换器，工作方式类似于组播，Exchange会将消息转发和ROUTING_KEY匹配模式相同的所有队列，比如，ROUTING_KEY为user.stock的Message会转发给绑定匹配模式为 * .stock,user.stock， * . * 和#.user.stock.#的队列。（ * 表是匹配一个任意词组，#表示匹配0个或多个词组）
        //  - headers: headers 消息体的header匹配（ignore）
        // Queue:消息的载体,每个消息都会被投到一个或多个队列。
        // Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
        // Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
        // vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
        // Producer:消息生产者,就是投递消息的程序.
        // Consumer:消息消费者,就是接受消息的程序.
        // Channel:消息通道,在客户端的每个连接里,可建立多个channel.
        //
        // RabbitMQ使用ProtoBuf序列化消息,它可作为RabbitMQ的Message的数据格式进行传输,由于是结构化的数据
    }

    void handleMessage() {
        // 如何确保消息正确地发送至RabbitMQ？
        // RabbitMQ使用发送方确认模式，确保消息正确地发送到RabbitMQ。
        // 发送方确认模式：将信道设置成confirm模式（发送方确认模式），则所有在信道上发布的消息都会被指派一个唯一的ID。
        // 一旦消息被投递到目的队列后，或者消息被写入磁盘后（可持久化的消息），信道会发送一个确认给生产者（包含消息唯一ID）。
        // 如果RabbitMQ发生内部错误从而导致消息丢失，会发送一条nack（not acknowledged，未确认）消息。
        // 发送方确认模式是异步的，生产者应用程序在等待确认的同时，可以继续发送消息。当确认消息到达生产者应用程序，生产者应用程序的回调方法就会被触发来处理确认消息。
        //
        //
        // 消息重复
        // 存在订单号即可
        //
        // 消息丢失：
        //  一：生产者丢失
        //  1. 生产者提供消息：发送消息前，开启事物(channel.txSelect())
        //  2. 或者使用confrim方式，所有在该信道上面发布的消息都将会被指派一个唯一的ID(从1开始)，一旦消息被投递到所有匹配的队列之后，rabbitMQ就会发送一个Ack给生产者(包含消息的唯一ID)，这就使得生产者知道消息已经正确到达目的队列了.如果rabiitMQ没能处理该消息，则会发送一个Nack消息给你，你可以进行重试操作
        //  二：消息队列丢失
        //  3. 将queue的持久化标识durable设置为true,则代表是一个持久的队列
        //  4. 发送消息的时候将deliveryMode=2
        //  三：消费者丢失
        //  5. 启用手动确认模式可以解决这个问题
        //      ①自动确认模式
        //      ②手动确认模式
        //      ③不确认模式
        // 如果要完全100%保证写入RabbitMQ的数据必须落地磁盘，不会丢失，需要依靠其他的机制，即RabbitMQ不能100%确认消息不丢失。
        // 当Mandatory参数设为true时，如果目的不可达，会发送消息给生产者，生产者通过一个回调函数来获取该信息。
        // https://www.iteye.com/blog/uule-2439190
    }

    void hanldeMany() {
        // 单一模式：即单机情况不做集群，就单独运行一个rabbitmq而已
        // 普通模式：默认模式，以两个节点（rabbit01、rabbit02）为例来进行说明,rabbit01和rabbit02两个节点仅有相同的元数据，即队列的结构.
    }
}

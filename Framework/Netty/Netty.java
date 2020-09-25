package Framework.Netty;

class Netty {

    // java.nio: https://blog.csdn.net/u014634338/article/details/82865622
    // io.netty:


    public static void compareBuffer() {
        // java.nio.Buffer
        // io.netty.buffer.ByteBuf

        // java.nio.Buffer
        //
        //  - position
        //  - limit
        //  - capacity
        //  - mark
        //  - mark <= position <= limit <= capacity
        //
        //  - 新建对象
        //      - ByteBuffer.allocate()
        //  - 初始情况:
        //      - mark = -1
        //      - position = 0
        //      - limit = capacity = 最大容量
        //  - flip() {
        //      limit = position
        //      position = 0
        //      mark = -1
        //  }
        //  - get()
        //      - position++;
        //  - put()
        //      - position++;
        //  - put() -> get() => (先执行flip()再读)
        //  - get() -> put() => (先执行compact()再写)

        // io.netty.buffer.ByteBuf
        //  - maxCapacity
        //  - readerIndex
        //  - writerIndex
        //  - 新建对象
        //      - Unpooled.buffer()
        //      - Unpooled.directBuffer()
        //      - UnPooled.compositeBuffer()
        //
        //  - 0 <= readerIndex <= writerIndex
        //  - discardReadBytes() : 删除废弃字节
        //  - https://blog.csdn.net/thinking_fioa/article/details/80795673

        // ByteBuf提供读访问索引(readerIndex)和写访问索引(writerIndex)来控制字节数组。ByteBuf API具有以下优点:

        // 1. 允许用户自定义缓冲区类型扩展
        // 2. 通过内置的复合缓冲区类型实现透明的零拷贝
        // 3. 容量可按需增长
        // 4. 读写这两种模式之间不需要调用类似于JDK的ByteBuffer的flip()方法进行切换
        // 5. 读和写使用不同的索引
        // 6. 支持引用计数
        // 7. 支持池化
    }

    public static void compareChannel() {
        // java.nio.channels.Channel
        // io.netty.channel.Channel

        // java.nio.channels.Channel
        // FileChannel
        // SocketChannel
        // ServerSockectChannel
        // SocketChannel
        // - read() != -1
        // - write()
        // - read(Buffer buf)
        // - write(Buffer buf)


        // io.netty.channel.Channel
        //  - NioServerSocketChannel
        //  - NioSocketChannel

        // id 标识唯一身份信息
        // 可能存在的parent Channel
        // 管道 pepiline
        // 用于数据读写的unsafe内部类
        // 关联上相伴终生的NioEventLoop
        // Channel通过ChannelPipeline中的多个Handler处理器,Channel使用它处理IO数据
        // Channel中的所有Io操作都是异步的,一经调用就马上返回,于是Netty基于Jdk原生的Future进行了封装, ChannelFuture, 读写操作会返回这个对象,实现自动通知IO操作已完成
        // Channel是可以有parent的

    }

    public static void compareSelector() {
        // java.nio.channels.Selector
        //  - open()
        //  - keys()
        //  - select()
        //  - selectNow()
        //  - selectedKeys()
        //      - SelectionKey
        //          - selector()
        //          - channel()
        //          - interestOps()
        //          - isReadable()
        //          - isWritable()
        //          - isConnectable()
        //          - isAcceptable()
        //          - attach(Object)
        //          - attachment()
        //
        //
        // io.netty:
        //  使用Bootstap/EventLoop 封装了select的细节
    }

    public static void dp() {
        // reactor：基于NIO技术，可读可写时通知应用；
        // proactor：基于AIO技术，读完成时通知应用，写操作应用通知内核。
    }

    public static void module() {
        // 1. netty单线程模型
        // Reactor 单线程模型，是指所有的 I/O 操作都在同一个 NIO 线程上面完成的，此时NIO线程职责包括：接收新建连接请求、读写操作等。
        //
        // 2. Reactor多线程模型
        // Rector 多线程模型与单线程模型最大的区别就是有一组 NIO 线程来处理连接读写操作，一个NIO线程处理Accept。
        //
        // 3. Reactor主从多线程模型
        // 服务端用于接收客户端连接的不再是一个单独的 NIO 线程，而是一个独立的 NIO 线程池
        // Acceptor 接收到客户端 TCP连接请求并处理完成后，将新创建的 SocketChannel注册到I/O线程池（sub reactor 线 程 池）的某个I/O线程上。
        // 由它负责SocketChannel 的读写和编解码工作。
        // Acceptor 线程池仅仅用于客户端的登录、握手和安全认证，一旦链路建立成功，就将链路注册到后端 subReactor 线程池的 I/O 线程上，由 I/O 线程负责后续的 I/O 操作。
        //
        // https://www.cnblogs.com/duanxz/p/3696849.html
    }

}

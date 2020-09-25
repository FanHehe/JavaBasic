package Basic;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.channels.FileChannel;
import java.nio.channels.AsynchronousFileChannel;

import java.util.concurrent.Future;

public class MyIO {

    public static void hanldeSerializable() {
        // Serializable 和 Externalizable
        // 完全负责自己的序列化则使用Externalizable
        //  - readExternal(ObjectInput in)
        //  - writeExternal(ObjectOutput out)
        //      - 实现Externalizable接口的类，不会像实现Serializable接口那样，会自动将数据保存。
        //      - 实现Externalizable接口的类，必须实现writeExternal()和readExternal()接口！
        //      - 实现Externalizable接口的类，必须定义不带参数的构造函数！否则，程序无法正常编译！
        //      - writeExternal() 和 readExternal() 的方法都是public的，不是非常安全！
    }

    public static void handleBIO() {
        // java.io.*
        // 基本的面向字节流和字符流的阻塞IO
        // - 字符流
        //  - Reader / Writer
        //      - FileReader / FileWriter
        // - 字节流
        //  - InputStream / OutputStream
        //      - FileInputStream / FileOutputStream
        //      - FilterInputStream / FilterOutputStream
        //          - BufferedInputStream / BufferedOutputStream
        //          - DataInputStream / DataOutputStream
        //      - ObjectInputStream / ObjectOutputStream
        //      - PipedInputStream / PipedOutputStream

        // 文件读写
        try {

            int c;
            FileInputStream fis = new FileInputStream("./README.md");
            FileOutputStream fos = new FileOutputStream("./README.md.2");
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            while((c = bis.read()) != -1) {
                bos.write(c);
            }

            bis.close();
            bos.close();
            fis.close();
            fos.close();

            File file = new File("./README.md.2");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 内存读写
        // ByteArrayInputStream / ByteArrayOutputStream
        // new ByteArrayInputStream(byte[] buf) or ByteArrayOutputStream(int size)
        //

        // 管道读写
        // PipedInputStream pis = new PipedInputStream();
        // PipedOutputStream pos = new PipedOutputStream();
        // pis.connect(pos) or pos.connect(pis); // 二选一，不能重复连接，会报错

        // 对基本数据和对象进行序列化操作支持
        // ObjectInputStream 和 ObjectOutputStream
        //  - write
        //  - writeBoolean
        //  - writeByte
        //  - writeBytes
        //  - writeChar
        //  - writeDouble
        //  - writeObject
        //  - ...
    }

    public static void handleNIO() {
        // https://blog.csdn.net/anxpp/article/details/51512200
        // https://blog.csdn.net/forezp/article/details/88414741
        // - 关键字：
        //   - Buffer
        //     - Buffer: 缓冲区实际上是一个数组，并提供了对数据结构化访问以及维护读写位置等信息
        //     - ByteBuffe、CharBuffer、 ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。
        //     - 他们实现了相同的接口：Buffer。
        //     - Buffer属性:
        //      - capacity: 缓冲区数组的总长度
        //      - position: 下一个要操作的数据元素的位置
        //      - limit: 缓冲区数组中不可操作的下一个元素的位置：limit<=capacity
        //      - mark: 用于记录当前position的前一个位置或者默认是-1
        //     - Buffer方法:
        //      - flip: 缓冲区中数据写入Channel，position设回0，并将limit设成之前的position的值
        //      - clear: position将被设回0，limit设置成capacity
        //      - mark: 标记Buffer中的一个特定的position
        //      - reset: 恢复到这个position
        //      - rewind: 方法将position设回0，limit保持不变，所以你可以重读Buffer中的所有数据
        //   - Channel
        //     - 我们对数据的读取和写入要通过Channel，它就像水管一样，是一个通道。通道不同于流的地方就是通道是双向的，可以用于读、写和同时读写操作。
        //      - SelectableChannel用户网络读写
        //      - FileChannel：用于文件操作
        //      - ServerSocketChannel
        //      - SocketChannel都是SelectableChannel的子类。
        //     - 方法
        //      - configureBlocking(true/false)
        //   - Selector(多路复用器)
        //     - Selector是Java  NIO 编程的基础。
        //     - Selector提供选择已经就绪的任务的能力
        //       - Selector会不断轮询注册在其上的Channel
        //       - 如果某个Channel上面发生读或者写事件，这个Channel就处于就绪状态
        //       - 会被Selector轮询出来，然后通过SelectionKey可以获取就绪Channel的集合，进行后续的I/O操作
        //       - 一个Selector可以同时轮询多个Channel，因为JDK使用了epoll()代替传统的select实现
        //       - 所以没有最大连接句柄1024/2048的限制。所以，只需要一个线程负责Selector的轮询，就可以接入成千上万的客户端
        // - select
        //     - 时间复杂度O(n)
        //     - /proc/sys/fs/file-max: 2048
        // -poll
        //     - 时间复杂度O(n)
        //     - 它没有最大连接数的限制
        // -epoll
        //     - 时间复杂度O(1)
        //     - 它没有最大连接数的限制

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile("README.md","rw");

            FileChannel channel = file.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int read = channel.read(buf);

            while (read != -1) {
                buf.flip();

                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }

                // 如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先写些数据，那么使用compact()方法。
                // compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。
                //
                buf.compact(); // or clear()
                read = channel.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleAIO() {
        // NIO 2.0引入了新的异步通道的概念
        // 并提供了异步文件通道和异步套接字通道的实现。
        // 异步的套接字通道时真正的异步非阻塞I/O，对应于UNIX网络编程中的事件驱动I/O（AIO）
        // 他不需要过多的Selector对注册的通道进行轮询即可实现异步读写，从而简化了NIO的编程模型
        //  - AsynchronousFileChannel
        //  - AsynchronousSocketChannel
        //  - AsynchronousServerSocketChannel
        try {

            Path path = Paths.get("README.md");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);

            Future future = channel.read(buffer, 0);

            buffer.flip();
            decoder.decode(buffer, charBuffer, false);
            charBuffer.flip();

            String data = new String(charBuffer.array(), 0, charBuffer.limit());

            System.out.println("read number:" + future.get());
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleIoPattern() {
        // https://www.jianshu.com/p/b7723c489e1c
        // Reactor
        // Proactor
    }

    public static void main(String[] args) {
        handleAIO();
        handleBIO();
        handleNIO();
    }
}

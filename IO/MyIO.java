package IO;

import java.io.*;
import java.nio.*;

public class MyIO {

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
    }

    public static void handleNIO() {
        // https://blog.csdn.net/anxpp/article/details/51512200
        // - 关键字：
        //   - Buffer
        //     - Buffer: 缓冲区实际上是一个数组，并提供了对数据结构化访问以及维护读写位置等信息
        //     - ByteBuffe、CharBuffer、 ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。他们实现了相同的接口：Buffe。
        //   - Channel
        //     - 我们对数据的读取和写入要通过Channel，它就像水管一样，是一个通道。通道不同于流的地方就是通道是双向的，可以用于读、写和同时读写操作。
        //     - SelectableChannel用户网络读写, FileChannel：用于文件操作
        //     - ServerSocketChannel, SocketChannel都是SelectableChannel的子类。
        //   - Selector(多路复用器)
        //     - Selector是Java  NIO 编程的基础。
        //     - Selector提供选择已经就绪的任务的能力
        //       - Selector会不断轮询注册在其上的Channel
        //       - 如果某个Channel上面发生读或者写事件，这个Channel就处于就绪状态
        //       - 会被Selector轮询出来，然后通过SelectionKey可以获取就绪Channel的集合，进行后续的I/O操作
        //       - 一个Selector可以同时轮询多个Channel，因为JDK使用了epoll()代替传统的select实现
        //       - 所以没有最大连接句柄1024/2048的限制。所以，只需要一个线程负责Selector的轮询，就可以接入成千上万的客户端
    }

    public static void handleAIO() {
        // NIO 2.0引入了新的异步通道的概念
        // 并提供了异步文件通道和异步套接字通道的实现。
        // 异步的套接字通道时真正的异步非阻塞I/O，对应于UNIX网络编程中的事件驱动I/O（AIO）
        // 他不需要过多的Selector对注册的通道进行轮询即可实现异步读写，从而简化了NIO的编程模型
    }

    public static void main(String[] args) {
        handleAIO();
        handleBIO();
        handleNIO();
    }
}

public class Nginx {

    public static void handleBase() {

        // https://www.cnblogs.com/gucb/p/11237802.html

        // 工作原理:
        // 异步，非阻塞，使用了epoll 和大量的底层代码优化。

        // 如果一个server采用一个进程负责一个request的方式，那么进程数就是并发数。正常情况下，会有很多进程一直在等待中。

        // 而nginx采用一个master进程，多个woker进程的模式。

        // master进程主要负责收集、分发请求。每当一个请求过来时，master就拉起一个worker进程负责处理这个请求。
        // 同时master进程也负责监控woker的状态，保证高可靠性
        // woker进程一般设置为跟cpu核心数一致。nginx的woker进程在同一时间可以处理的请求数只受内存限制，可以处理多个请求。
        // Nginx 的异步非阻塞工作方式正把当中的等待时间利用起来了。在需要等待的时候，这些进程就空闲出来待命了，因此表现为少数几个进程就解决了大量的并发问题。
        //
        // 每进来一个request，会有一个worker进程去处理。但不是全程的处理
        // 处理到什么程度呢?处理到可能发生阻塞的地方，比如向上游(后端)服务器转发request
        // 并等待请求返回。那么，这个处理的worker很聪明，他会在发送完请求后
        // 注册一个事件：“如果upstream返回了，告诉我一声，我再接着干”。于是他就休息去了
        // 此时，如果再有request 进来，他就可以很快再按这种方式处理。而一旦上游服务器返回了，就会触发这个事件，worker才会来接手，这个request才会接着往下走
        //
        // 惊群：同一个时刻只能有唯一一个worker子进程监听web端口，此时新连接事件只能唤醒唯一正在监听端口的worker子进程。采用锁，互斥量实现！！
        // 就是同一时刻只允许一个nginx worker在自己的epoll中处理监听句柄。它的负载均衡也很简单，当达到最大connection的7/8时，本worker不会去试图拿accept锁，也不会去处理新连接，这样其他nginx worker进程就更有机会去处理监听句柄，建立新连接了。而且，由于timeout的设定，使得没有拿到锁的worker进程，去拿锁的频繁更高。
        //
        // 禁止access_log: access_log off;
    }


    public static void hanldeLoadBalance() {
        // 负载均衡的几种常用方式？
        //
        // 1、轮询（默认）
        // 2、权重
        //
        // upstream backserver {
        //  server 192.168.0.14 weight=3;
        //  server 192.168.0.15 weight=7;
        // }
        //
        // 3、ip_hash
        //
        // upstream backserver {
        // ip_hash;
        //  server 192.168.0.14;
        //  server 192.168.0.15;
        // }
        //
        // 4、least_conn
        // // upstream backserver {
        // least_conn;
        //  server 192.168.0.14;
        //  server 192.168.0.15;
        // }
    }

    public static void handleServer() {

    }

    public static void handleLoaction() {
        // https://www.jianshu.com/p/403bab8fc34d

        // location
        // location [ = | ~ | ~ * | ^~ ] /uri/ { … }
        //  1. 以 = 开头，表示精确匹配
        //  2. 以 ^~ 开头，表示uri以某个常规字符串开头，理解为匹配 url路径即可
        //      2.1 nginx不对url做编码，因此请求为/static/20%/aa，可以被规则^~ /static/ /aa匹配到（注意是空格）
        //  3. ~ 开头，表示区分大小写的正则匹配
        //  4. ~* 开头，表示不区分大小写的正则匹配
        //  5. !~ 和!~* 分别为区分大小写不匹配及不区分大小写不匹配 的正则
        //  6. / 通用匹配，任何请求都会匹配到。
        //  7. 多个location配置的情况下匹配顺序为：
        //      7.1 首先匹配= ；
        //      7.2 其次匹配^~；
        //      7.3 再其次是按文件中顺序的正则匹配；
        //      7.4 最后是交给 / 通用匹配；
        //      7.5当有匹配成功时候，停止匹配，按当前匹配规则处理请求。

        // 防盗链：valid_referers
        // location ~* \.(gif|jpg|swf)$ {
        //   valid_referers none blocked start.igrow.cn sta.igrow.cn;
        //   if ($invalid_referer) {
        //     rewrite ^/ http://$host/logo.png;
        //   }
        // }
    }

    public static void handleRewrite() {
        // last – 停止处理后续rewrite指令集，然后对当前重写的新URI在rewrite指令集上重新查找。
        // break –  停止处理后续rewrite指令集，并不在重新查找
        // redirect – 返回临时重定向的HTTP状态302
        // permanent – 返回永久重定向的HTTP状态301
    }

    public static void hanldeLimitStream() {
        // ngx_http_limit_conn_module
        //
    }
}

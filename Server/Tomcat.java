public class Tomcat {

    public static void handleDescription() {
        // 1. 实现了servlet协议的Web服务器
        // 2.
    }

    public static void handleFunctions() {

    }

    public static void handleClassLoader() {
        // https://www.cnblogs.com/aspirant/p/8991830.html
    }

    public static void handleStruction() {
        // /bin - Tomcat 脚本存放目录（如启动、关闭脚本）。 *.sh 文件用于 Unix 系统； *.bat 文件用于 Windows 系统。
        // /conf - Tomcat 配置文件目录。
        // /logs - Tomcat 默认日志目录。
        // /webapps - webapp 运行的目录。
    }

    public static void hanldeDeploy() {
        // 先匹配端口，再匹配

        // 一、多域名，同一个端口
        //
        // 同一个Server -> Service -> Engine 下配置两个name不同的Host, name 就是域名
        //
        // 二、不同域名，多端口访问
        //
        // 同一个Server下，配置两个Service
        //

        // Sever.port 发送shutDown字符串, 关闭当前tomcat实例
        // Connector.http.port http 协议监听
        // Connector.AJP.port  用于和其他http服务器建立集群

        // A、扫描虚拟主机指定的xmlBase下的XML配置文件
        // B、扫描虚拟主机指定的appBase下的WAR文件
        // C、扫描虚拟主机指定的appBase下的应用目录

        // 1. 项目直接放入 webapps 目录中
        // 2. conf/server.xml add <Context path="/WebProject" docBase="D:/WebProject" reloadable="true" />
        // 3. conf\Catalina\localhost 目录,  项目名.xml 文件, 在 那个新建的 xml 文件中，增加下面配置语句: <Context  docBase="D:/WebProject" reloadable="true" />
    }

    public static void handleServerConfig() {
        // <Server
        //     port = "8005"
        //     shutdown = "SHUTDOWN">
        //     <Service
        //         name="Catalina">
        //
        //         <Connector
        //             port = "80"
        //             maxThreads = "4000"
        //             protocol = "HTTP/1.1"
        //             maxHttpHeaderSize = "12000"
        //             minSpareThreads="1000"
        //             maxSpareThreads="2000"
        //             enableLookups="false"
        //             redirectPort="443"
        //             acceptCount="2000"
        //             minProcessors="30"
        //             maxProcessors="200"
        //             connectionTimeout="20000"
        //             disableUploadTimeout="true" />
        //
        //         // Connector用于接受请求并将请求封装成Request和Response，然后交给Container进行处理，Container处理完之后在交给Connector返回给客户端
        //         <Connector port="8009" protocol="AJP/1.3" redirectPort="443" />
        //
        //         <Connector
        //             port = "443"
        //             secure = "true"
        //             scheme = "https"
        //             maxThreads = "150"
        //             SSLEnabled = "true"
        //             clientAuth = "false"
        //             protocol = "HTTP/1.1"
        //             keystorePass = "Enn2jYTK"
        //             SSLProtocol = "TLSv1+TLSv1.1+TLSv1.2"
        //             keystoreFile = "/usr/share/tomcat/conf/cert/cert-1542122127955__.willapps.com.jks"
        //             SSLCipherSuite = "ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4"
        //             ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_256_CBC_SHA256"/>
        //         <Engine
        //             defaultHost = "localhost">
        //             <Host
        //                 name="localhost"
        //                 appBase="webapps"
        //                 unpackWARs="true"
        //                 autoDeploy="true">
        //                 <Context />
        //                 <Context
        //                     path = "path"
        //                     docBase = "docBase"
        //                     reloadable = "true" />
        //                 <Valve
        //                     directory = "logs"
        //                     pattern = "%h %l %u %t &quot;%r&quot; %s %b"
        //                     prefix = "localhost_access_log." suffix = ".txt"
        //                     className = "org.apache.catalina.valves.AccessLogValve" />
        //             </Host>
        //         </Engine>
        //     </Service>
        // </Server>
        // https://blog.csdn.net/jsj13263690918/article/details/80368757
    }

    public static void handleProject() {
        // |-- webapp                     # 站点根目录, (每个 war 包可以简单视为 webapp 的压缩包。)
        // |-- META-INF                   # META-INF 目录
        // |   |-- MANIFEST.MF            # 配置清单文件
        // |-- WEB-INF                    # WEB-INF 目录
        // |   |-- classes                # class文件目录
        // |   |   |-- *.class            # 程序需要的 class 文件
        // |   |   |-- *.xml              # 程序需要的 xml 文件
        // |   |-- lib                    # 库文件夹
        // |   |   |-- *.jar              # 程序需要的 jar 包
        // |   |-- web.xml                # Web应用程序的部署描述文件
        // |-- <userdir>                  # 自定义的目录
        // |-- <userfiles>                # 自定义的资源文件
    }

    public static void main(String[] args) {
        hanldeDeploy();
        handleProject();
        handleStruction();
    }

    public static void handlePreview() {

    }

    public static void handleHttps() {
        // <Connector protocol="org.apache.coyote.http11.Http11Protocol"
        //    port="443"
        //    SSLEnabled="true"
        //    maxThreads="150"
        //    scheme="https"
        //    secure="true"
        //    keystoreFile="/usr/share/tomcat/conf/cert/cert-2019-11-14/3082649__willapps.com.pfx"
        //    keystorePass="Rxh5dsHp"
        //     keystoreType="PKCS12"
        //    SSLProtocol="TLSv1+TLSv1.1+TLSv1.2"
        //    SSLCipherSuite="ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4"
        //    clientAuth="false"
        //    ciphers="TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_256_CBC_SHA256"/>

    }
}

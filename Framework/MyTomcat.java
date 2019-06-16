package Framework;

public class MyTomcat {

    public static void handleStruction() {
        // /bin - Tomcat 脚本存放目录（如启动、关闭脚本）。 *.sh 文件用于 Unix 系统； *.bat 文件用于 Windows 系统。
        // /conf - Tomcat 配置文件目录。
        // /logs - Tomcat 默认日志目录。
        // /webapps - webapp 运行的目录。
    }

    public static void hanldeDeploy() {
        // A、扫描虚拟主机指定的xmlBase下的XML配置文件

        // B、扫描虚拟主机指定的appBase下的WAR文件

        // C、扫描虚拟主机指定的appBase下的应用目录

        // 1. 项目直接放入 webapps 目录中
        //
        // 2. conf/server.xml add <Context path="/WebProject" docBase="D:/WebProject" reloadable="true" />
        //
        // 3. conf\Catalina\localhost 目录,  项目名.xml 文件, 在 那个新建的 xml 文件中，增加下面配置语句: <Context  docBase="D:/WebProject" reloadable="true" />
    }

    public static void handleServerConfig() {
        // <Server port="8005" shutdown="SHUTDOWN">
        //     <Service name="Catalina">
        //         <Connector />
        //         <Connector />
        //         <Engine defaultHost="localhost">
        //             <Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="true">
        //                 <Context />
        //                 <Context docBase="docBase" path = "path" reloadable="true"/>
        //             </Host>
        //         </Engine>
        //     </Service>
        // </Server>
        // https://www.cnblogs.com/kismetv/p/7228274.html
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
        handleStruction();
        handleProject();
        hanldeDeploy();
    }
}

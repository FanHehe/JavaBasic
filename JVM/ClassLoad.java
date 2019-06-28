package JVM;

public class ClassLoad {
    public static void handleLifeCycle() {
        // 类的生命周期：(↓)代表必须有固定的顺序
        // 加载 loading           (↓)
        //  - new getstatic pustatic, invokestatic
        //  - reflect
        //  - 初始化一个类的时候，父类未被初始化时
        //  - 程序入口main
        //  - invoke.MethodHandler 动态代理类
        // 连接 Linking
        //  - 验证 verification   (↓)
        //  - 准备 perparation    (↓)
        //  - 解析 Resolution
        // 初始化 Initialization  (↓)
        //  - 执行字节码
        //  - clinit
        // 使用 Using
        // 卸载 Unloading         (↓)
    }

    public static void hanldeClassLoader() {
        // 双亲委派
        //  - Bootstra CLassLoader
        //  - Extension ClassLoader
        //  - Application ClassLoader
        // 破坏双亲委派
        //  - Java 1.2时代之前，双亲委派还未出现
        //  - 基础类要回调用户的代码：JNDI 解决方法：ThreadContext ClassLoader
        //  - 代码热替换
    }
}

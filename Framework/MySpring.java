package Framework;

public class MySpring {
    public static void handleIoc() {
        // 依赖注入
        // 控制权由对象本身转向容器；由容器根据配置文件去创建实例并创建各个实例之间的依赖关系
        // bean工厂；在Spring中，bean工厂创建的各个实例称作bean
        // - BeanWrapper
        // - BeanFactory
    }

    public static void handleAop() {
        // 切面编程
        //  - 通知(Advice)
        //      - Before()
        //      - After()
        //      - After-returning()
        //      - After-throwing()
        //      - Around
        //  - 连接点(Join Point)
        //  - 切点(PointCut)
        //      - 切点指示器
        //          - arg()
        //          - @args()
        //          - execute()
        //          - bean()
        //          - this()
        //          - target
        //          - @target()
        //          - within()
        //          - @within()
        //          - @annotation
        //          - 带@指被xxx注解修饰
        //          - 只有execution指示器是实际执行匹配的，其他指示器都是限制匹配的。
        //      - 切点表达式
        //          - || && ! 或者 and or not 替换
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(..))
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(..)) and within(com.fanhehe.cmd.*)
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(int)) and args(uid)
        //  - 切面(Aspect)
        //      - 通知 + 切点 = 切面
        //  - 引入(Introduction)
        //  - 织入(Weaving)
        //      - 编译时
        //      - 类加载时
        //      - 运行时
        //          - JDK invokeHandler
        //          - CGLib 基于类继承的动态代理
        // SpringAOP
        //  - 基于代理的经典SpringAOP
        //  - 纯POJO AOP
        //  - @AspectJ注解驱动的切面
        //  - 注入式AspectJAOP
        // Spring仅支持方法级别的连接点，其他的则可以使用AspectJ进行补充扩展。
    }

    public static void handleModules() {
        // core: 核心模块
        // context: 扩展了BeanFactory的概念，增加了对国际化、事件传播，以及验证等的支持，此外还提供了许多企业服务及对模版框架集成的支持
        // web: 建立于Context模块之上，提供了一个适合于Web应用的上下文。另外，这个模块还提供了一些面向服务支持，也提供了Spring和其它Web框架的集成
        // mvc: 是一个全功能的构建 Web 应用程序的 MVC 实现，容纳了大量视图技术，如 JSP、Velocity、POI等
        // aop: 为Spring容器管理的对象提供了对面向切面编程的支持；
        // dao: 该层封装了对数据库的访问，并且处理了其抛出的错误消息，同时还基于AOP模块提供了事务管理；
        // orm: Spring支持多种ORM框架，简化了数据库操作。
    }

    public static void handleInject() {
        // 1. 基于构造器注入
        // <bean id="bean" class="..." >
        //     <constructor-arg >
        //         <ref bean="..." />
        //     </constructor>
        //     <constructor-arg index="1" type="int" value="id"/>
        //     <constructor-arg index="0" type="String" value="name"/>
        // </bean>
        //
        // 2. setter方法注入
        //
        // private Service service;
        //
        // @Resource
        // public void setService(UserService service) {
        //     this.service = service;
        // }
        //
        // 3. 接口注入
        //
        // 4. 注解方式注入
        //
        // @Autowired默认按类型装配，@Resource默认按名称装配，当找不到与名称匹配的bean时，才会按类型装配
    }

    public static void handleBeanThreadSafe() {
        // Spring框架并没有对单例bean进行任何多线程的封装处理。关于单例bean的线程安全和并发问题需要开发者自行去搞定。
        // 但实际上，大部分的Spring bean并没有可变的状态(比如Serview类和DAO类)，所以在某种程度上说Spring的单例bean是线程安全的
        // 但比如 View Model 对象的bean有多种状态的话，就需要自行保证线程安全。
    }

    public static void hanldeBeanScope() {
        // scope
        //  - singleton
        //  - prototype
        //  - request
        //  - session
        //  - global session: 基于portlet的web
        //  singleton表示该bean全局只有一个实例，Spring中bean的scope默认也是singleton
        //  prototype表示该bean在每次被注入的时候，都要重新创建一个实例，这种情况适用于有状态的Bean
    }

    public static void hanldeTransaction() {
        // - ACID
        // - Spring事务管理接口
        // - Spring并不直接管理事务，而是提供了多种事务管理器
        //  - PlatformTransactionManager: Spring事务管理器的接口
        //      - getTransaction(TransactionDefinition definition)
        //      - commit(TransactionStatus status)
        //      - rollback(TransactionStatus status)
        //  - TransactionStatus： 事务运行状态
        //      - 用来记录事务的状态
        //      - isNewTransaction(); // 是否是新的事物
        //      - hasSavepoint(); // 是否有恢复点
        //      - setRollbackOnly();  // 设置为只回滚
        //      - isRollbackOnly(); // 是否为只回滚
        //      - isCompleted; // 是否已完成
        //  - TransactionDefinition： 事务定义信息
        //      - 传播行为: 用来描述某个事务传播行为修饰的方法被嵌套进另一个方法时如何进行传播ff
        //          - https://segmentfault.com/a/1190000013341344
        //          - .PROPAGATION_REQUIRED: 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
        //          - .PROPAGATION_REQUIRES_NEW: 创建一个新的事务，如果当前存在事务，则把当前事务挂起
        //          - .PROPAGATION_SUPPORTS: 果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行
        //          - .PROPAGATION_MANDATORY: 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常
        //          - .PROPAGATION_NOT_SUPPORTED: 以非事务方式运行，如果当前存在事务，则把当前事务挂起
        //          - .PROPAGATION_NEVER: 以非事务方式运行，如果当前存在事务，则抛出异常
        //          - .PROPAGATION_NESTED: 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行
        //              如果当前没有事务，则该取值等价于.PROPAGATION_REQUIRED, (SavePoint)
        //      - 事务隔离级别
        //          - 串行化
        //          - 重复读
        //              - 幻读: 幻读的重点在于新增或者删除
        //              - 如果使用锁机制来实现这两种隔离级别，在可重复读中，该sql第一次读取到数据后，就将这些数据加锁
        //                  - 其它事务无法修改这些数据，就可以实现可重复读了。但这种方法却无法锁住insert的数据
        //                  - 所以当事务A先前读取了数据，或者修改了全部数据，事务B还是可以insert数据提交，
        //                  - 这时事务A就会 发现莫名其妙多了一条之前没有的数据，这就是幻读，不能通过行锁来避免。
        //          - 已提交读
        //              - 重复读: 发生了在一个事务内两次读到的数据是不一样的情况, 重点在于修改
        //          - 未提交读
        //              - 脏读
        //      - 超时
        //          - 一个事务允许执行的最长时间, 超时未完成自动回滚
        //      - 只读
        //          - 如果确定只对事务性资源进行只读操作，那么我们可以将事务标志为只读的，以提高事务处理的性能
        //      - 回滚规则
        //          - 这些规则定义了哪些异常会导致事务回滚而哪些不会
    }

    public static void hanldeWorkflow() {

        // Picture/spring.png
        // <-- request
        // - DispatcherServlet
        // - HandleMapping(返回执行链)
        // - HandleAdapter(返回ModelAndView)
        // - ViewResolver(返回view)
        // - 视图渲染
        // --> responses
    }
    public static void handleComponents() {
        // https://blog.csdn.net/hu_zhiting/article/details/73648939
        // SpringMVC中的Servlet一共有三个层次
        //  - HttpServletBean
        //      - extends HttpServlet
        //  - FrameworkServlet
        //      - init WebApplicationContext
        //  - DispatcherServlet
        //      - 初始化了自身的9个组件
        //      - HandlerMapping: 负责将请求交个指定Handler进行处理。
        //      - HandlerAdapter: Servlet的处理方法是以request和response为参数的方法，这个适配器使处理灵活
        //      - HandlerExceptionResolver: 此组件的作用是根据异常设置ModelAndView，之后再交给render方法进行渲染
        //      - ViewResolver: 决定使用哪个模板 + 用什么规则填入参数
        //      - RequestToViewNameTranslator:
        //      - LocaleResolver: 国际化资源主题,解析出zh-cn等等
        //      - ThemeResolver: 一个主题对应一个properties文件，里面存放着跟当前主题相关的所有资源、如图片、css样式等
        //      - MultipartResolver: 用于处理上传请求。处理方法是将普通的request包装成MultipartHttpServletRequest
        //      - FlashMapManager: 用来管理FlashMap的，FlashMap主要用在redirect中传递参数
    }
}

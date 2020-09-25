package Framework;

public class MySpring {
    public static void handleIoc() {
        // 依赖注入
        // 控制权由对象本身转向容器
        //  由容器根据配置文件去创建实例并创建各个实例之间的依赖关系
        // bean工厂 在Spring中，bean工厂创建的各个实例称作bean
        // - BeanWrapper
        // - BeanFactory
        // - BeanDefination
        //
        // beanDefinition -> DefaultListableBeanFactory 解析过后的 BeanDefinition 在IoC容器中的注册
        //
        // DefaultSingletonBeanRegistry.singletonObjects
        // DefaultSingletonBeanRegistry.singletonFactories
        // DefaultSingletonBeanRegistry.earlySingletonObjects
    }

    public static void handleAop() {
        // https://www.cnblogs.com/liaojie970/p/7883687.html
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
        //          - 只有execution指示器是实际执行匹配的，其他指示器都是限制匹配的
        //      - 切点表达式
        //          - || && ! 或者 and or not 替换
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(..))
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(..)) and within(com.fanhehe.cmd.*)
        //          - execution(* com.fanhehe.cmd.service.HomeService.handleIndex(int)) and args(uid)
        //          - execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
        //  - 切面(Aspect)
        //      - 通知 + 切点 = 切面
        //  - 引入(Introduction)
        //  - 织入(Weaving)
        //      - 编译时
        //      - 类加载时
        //      - 运行时
        //          - JDK InvocationHandler
        //
        // public class SubjectProxy implements java.lang.reflect.InvocationHandler {
        //
        //     private Subject subject;
        //
        //     SubjectProxy(Subject subject){
        //         this.subject = subject;
        //     }
        //
        //     @Override
        //     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //         //进行method过滤，如果是其他方法就不调用
        //         if (method.getName().equals("doSomething")){
        //             System.out.println("做某些事前的准备");
        //             Object object = method.invoke(subject,args);
        //             System.out.println("做某些事后期收尾");
        //             return object;
        //         }
        //         return "调用失败";
        //     }
        //
        //     public Subject getProxy() {
        //         return (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), this);
        //     }
        //
        // }
        //          - CGLib 基于类继承的动态代理
        // SpringAOP
        //
        //  - 基于代理的经典SpringAOP
        //  - 纯POJO AOP
        //  - @AspectJ注解驱动的切面
        //  - 注入式AspectJ AOP
        //
        // https://segmentfault.com/a/1190000015830477
        //
        // Spring仅支持方法级别的连接点，其他的则可以使用AspectJ进行补充扩展
        // AnnotationAwareAspectJAutoProxyCreator 实现了InstantiationAwareBeanPostProcessor (EnableAspectJAutoProxy)
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
        // @Qualifier
        // @Autowired默认按类型装配，@Resource默认按名称装配，当找不到与名称匹配的bean时，才会按类型装配(byType byName)
        // AutowiredAnnotationBeanPostProcessor
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
        //      - 传播行为: 用来描述某个事务传播行为修饰的方法被嵌套进另一个方法时如何进行传播
        //          - https://segmentfault.com/a/1190000013341344
        //          - Propagation
        //              - REQUIRED: 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
        //              - REQUIRES_NEW: 创建一个新的事务，如果当前存在事务，则把当前事务挂起
        //              - SUPPORTS: 果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行
        //              - MANDATORY: 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常
        //              - NOT_SUPPORTED: 以非事务方式运行，如果当前存在事务，则把当前事务挂起
        //              - NEVER: 以非事务方式运行，如果当前存在事务，则抛出异常
        //              - NESTED: 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行，如果当前没有事务，则该取值等价于.PROPAGATION_REQUIRED, (SavePoint)
        //
        //      - 超时
        //          - 一个事务允许执行的最长时间, 超时未完成自动回滚
        //      - 只读
        //          - 如果确定只对事务性资源进行只读操作，那么我们可以将事务标志为只读的，以提高事务处理的性能
        //      - 回滚规则
        //          - 这些规则定义了哪些异常会导致事务回滚而哪些不会
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

    public static void ImportBeanAliasBeansLabel() {
        // 请求的完整路径
        // https://www.zhenchao.org/2017/07/23/spring/spring-mvc-src-request-sequence/
    }

    public static void typesOfWebApplicationContext() {
        // 1. XmlWebApplicationContext
        // 2. AnnotationConfigWebApplicationContext
        //  2.1 https://www.cnblogs.com/kaituorensheng/p/8024199.html
        // 一和二在同一层，均继承自 AbstractRefreshableWebApplicationContext
        // 继承链，包含六层：
        // XmlWebApplicationContext
        //  --> AbstractRefreshableWebApplicationContext
        //  --> AbstractRefreshableConfigApplicationContext
        //  --> AbstractRefreshableApplicationContext.beanFactory
        //  --> AbstractApplicationContext.refresh()
        //  --> DefaultResourceLoader
    }

    public static void WACRefresh() {
        // https://blog.csdn.net/yangliuhbhd/article/details/80790761
        // 0. prepareRefresh(); 标记refresh中
        // 1. 获取BeanFactory，来自DefaultListableBeanFactory (obtainFreshBeanFactory)
        //      -
        // 2. prepareBeanFactory(beanFactory) 配置beanFactory
        //      - 子类自定义些东东去处理
        //      - 比如：ignoreDependencyInterface，因为某些依赖是由processor设置的，比如BeanFactoryAware等，
        //      - 因此，使用ignoreDependencyInterface取消bean去找这些依赖bean
        // 3. BeanFactory构造好之后执行postProcessBeanFactory(beanFactory)
        //      - 由子类(AbstractRefreshableWebApplicationContext)实现
        //      - 添加新的ServletContextAwareProcessor处理器，如果bean实现了ServletContextAware，ServletConfigAware，会自动进行装配
        // 4. invokeBeanFactoryPostProcessors(beanFactory);
        //      - 理解不懂下面的话:
        //      - BeanFactory的后置处理器，在BeanFactory初始化之后执行的；
        //      - 从getBeanFactoryPostProcessors() 获取BeanFactoryPostProcessors，执行
        //      - 下面两个重要接口:
        //          - BeanFactoryPostProcessor
        //              - postProcessBeanFactory
        //          - BeanDefinitionRegistryPostProcessor
        //              - postProcessBeanDefinitionRegistry
        //          - 增强beanFactory
        //      - https://blog.csdn.net/cgj296645438/article/details/80131162
        //      - 两个来源
        //          - 1. AbstractApplicationContext.getBeanFactoryPostProcessors 自己手动加进去的
        //          - 2. beanFactory.getBeansOfType(BeanDefinitionRegistryPostProcessor.class, true, false)
        // 5. registerBeanPostProcessors(beanFactory);
        //      - 注册BeanPostProcessors
        //      - 将beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false); 获取到的BeanPostProcessor
        //      - 都注册到beanFactory上，通过getBean实例化。
        //      - https://blog.csdn.net/cgj296645438/article/details/80165129
        //      - 需要注意的是，这里只是注册，相当于整理所有的BeanPostProcessor，
        //      原来所有的BeanPostProcessor只是再容器用以bean的形式存在着，我们不知道应该怎么去执行，以怎样的顺序去执行。
        //      整理后我们把所有的BeanPostProcessor都提出来，按执行循序放到list中，供后续调用。
        // ...
        // 6. finishBeanFactoryInitialization()
        //  - 初始化所有非懒加载、非抽象、非单例的Bean
        //  - 冻结beanFactory配置
        //
        // 7. finishRefresh()
        //  - 初始化Lifecycle处理器，调用onRefresh方法，广播ContextRefreshedEvent
        //  - 为此beanFactory初始化生命周期处理器
    }

    public static void BeanFactory() {
        // spring中使用的是DefaultListableBeanFactory + DefaultSingletonBeanRegistry
        // 而 getBean 方法实现在org.springframework.beans.factory.support.AbstractBeanFactory中。
        // getBean 统一使用doGetBean去加载
    }

    public static void BeanLifeCycleInBeanFactory() {

        // 在IoC容器启动之后，并不会马上就实例化相应的bean
        // 此时容器仅仅拥有所有对象的BeanDefinition
        // BeanDefinition：是容器依赖某些工具加载的XML配置信息进行解析和分析，并将分析后的信息编组为相应的BeanDefinition。
        // 只有当getBean()调用时才是有可能触发Bean实例化阶段的活动

        // BeanFactory中bean生命周期图
        //
        // 1. start(getBean("bean") 获取bean), 触发时机
        //
        // 2. 如果容器实现了org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor 接口
        //      - 则在实例化Bean之前调用接口的postProcessBeforeInstantiation()方法
        //
        // 3. 实例化Bean
        //
        // 4. 如果容器实现了org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor 接口
        //      - 则实例化Bean之后调用接口的postProcessAfterInstantiation
        //
        // 5. 如果容器实现了org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor 接口
        //      - 调用InstantiationAwareBeanPostProcessor.postPropertyValues()方法
        //
        // 6. 设置对象属性
        //
        // 7. 检查Aware接口，设置相关依赖
        //  - BeanNameAware
        //  - BeanFactoryAware
        //  - BeanClassLoaderAware
        //  - ...
        //
        // 8. 如果BeanFactory装配了BeanPostProcessor，则执行postProcessBeforeInitialization的前置处理
        //      - spring使用org.springframework.context.support.ApplicationContextAwareProcessor 作为 BeanPostProcessor
        //      - 我们自己配置bean的后置处理器的话，不需要id，IoC容器自动识别是一个BeanPostProcessor
        //      - 默认前置，设置各种Aware
        //          - ResourceLoaderAware
        //          - MessageSourceAware
        //          - ApplicationEventPublisherAware
        //          - ApplicationContextAware
        //          - BeanFactoryAw
        //          - 其实都aware的都是WAC
        //          - (后置，直接返回bean)
        //
        // 9. 如果Bean实现了InitializingBean接口，则将调用接口的afterPropertiesSet()方法
        //
        // 10. 检查是否有init-method，如果有则调用
        //
        // 11. 如果BeanFactory装配了BeanPostProcessor，则执行postProcessAfterInitialization的前置处理
        //      - org.springframework.context.support.ApplicationContextAwareProcessor直接返回Bean
        //      - 对于scope = "prototype" 的Bean:
        //          - 则将Bean交给调用者，由调用者负责Bean的后续生命管理。
        //          - 如果scope="singleton",那么此实例将会被放入缓存池中，由spring容器负责Bean的生命管理
        //      - 对于scope = "singleton" 的Bean(默认情况):
        //          - 当容器关闭时，将触发Spring对Bean后续生命周期管理工作，如果Bean实现DisposableBean接口，
        //          - 那么将会调用destory()方法，释放资源，记录日志文件
        //
        // 12. 使用中
        //
        // 13. 如果bean实现了DisposableBean则那么将会调用destory()方法，释放资源，记录日志文件
        //
        // 14. 如果Bean中通过destory-method属性定义了销毁方法，则将执行这个方法
    }

    public static void makeRequest() {
        // DispatcherServlet.doDispatch
        // 0. handlerMappings
        //      - DefaultAnnotationHandlerMapping
        //      - SimpleUrlHandlerMapping
        //      - AbstractUrlHandlerMapping.handlerMap
        // 1. handlerAdapters
        //      - AnnotationMethodHandlerAdapter
        //      - HttpRequestHandleAdapter
        //
        // 2. 根据HttpServletRequest获取 HandlerExecutionChain
        //      - Object handler
        //          - HandlerMethod（封装了 Controller 中的方法）
        //          - Controller对象
        //          - HttpRequestHandler 对象或 Servlet 对象
        //      - HandlerInterceptor[] interceptors
        //          - preHandle
        //          - postHandle
        //          - afterCompletion
        // 3. 获取 HandlerAdapter
    }

    public static void BeanScope() {
        // 定义于：org.springframework.web.context;
        // - String SCOPE_REQUEST = "request";
        // - String SCOPE_SESSION = "session";
        // - String SCOPE_GLOBAL_SESSION = "globalSession";
        // - String SCOPE_APPLICATION = "application";
    }

    public static void start() {
        // # Spring MVC 流程

        // 简单来说：对于web.xml中的配置项, tomcat的处理优先级为: listener > filter > servlet

        // 对于一个简单的Spring项目，它的web.xml形如:

        // ```xml

        // <listener>
        //     <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        // </listener>
        // <servlet>
        //     <servlet-name>application</servlet-name>
        //     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        //     <load-on-startup>1</load-on-startup>
        // </servlet>
        // <servlet-mapping>
        //     <servlet-name>application</servlet-name>
        //     <url-pattern>/</url-pattern>
        // </servlet-mapping>
        // ```

        // ### ContextLoaderListener
        // 调用ContextLoaderListener.contextInitialized()方法，设置WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE为ContextLoader.createWebApplicationContext()。

        // ### Filter
        // init -> doFilter(req, res, filterchain) -> destory.
        // ### DispatcherServlet

        // 每当请求来临时，tomcat会根据servlet-mapping将请求交给符合url-pattern的servlet进行处理，此时tomcat会负责调用Servlet.service方法。
        // 因为仅配置了一个url-pattern为/的servlet-mapping，因此所有的请求都会由DispatcherServlet处理，而配置了load-on-startup属性，则决定了服务器启动时，会自动执行Servlet的init方法。
        // 因此DispatcherServlet类，作为Spring的入口类，既是App启动的入口(DispatcherServlet.init)，同样也是在接受到请求时，处理请求的入口(DispatcherServlet.service)。
        // 所以spring的启动过程，也就是new DispatcherServlet() 和 DispatcherServlet.init()的过程。
    }

    public static void springBootAuto() {
        // @SpringBootApplication
        // @EnableAutoConfiguration
        //  - @AutoConfigurationPackage
        //      - @Import({Registrar.class}) // 加载代码
        //  - @Import({AutoConfigurationImportSelector.class}) // 加载依赖
        //      - selectImports
        //          - META-INF/spring.factories
        //          - SpringFactoriesLoader加载
        //          - 每个META-INF/spring.factories指定的EnableAutoConfiguration=指定类
        //          - 类上会有ConditionalOnBean or ConditionalOnClass，条件加载
        //          - EnableConfigrationProperties指定配置类 and ConfigrationProperties注解配置类，指定前缀
        //          - EnableAutoConfiguration的类则通过构造函数注入的方式将ConfigrationProperties注入进去

        //
        // https://www.cnblogs.com/hjwublog/p/10332042.html#_labelTop
    }
}

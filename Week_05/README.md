学习笔记

周四作业：  
1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。  
代码：homework01 -> aop

2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。  
代码：homework01 -> springbean


周六作业：  
1.（选做）总结一下，单例的各种写法，比较它们的优劣。  
代码：homework02 -> singleton  
1)饿汉式：在类加载的时候，instance静态实例就已经创建并初始化，创建过程线程安全，但是不支持延迟加载。  
2)懒汉式：懒汉式相对于饿汉式的优势是支持延迟加载，在instance实例需要用到的时候才初始化，不过缺点也很明显，给getInstance()方法加了synchronized，导致并发度很低。  
3)双重检测：饿汉式不支持延迟加载，懒汉式有性能问题，双重检测综合这两点进行了改造，只要 instance 被创建之后，即便再调用 getInstance() 方法也不会再进入到加锁逻辑中了，既支持延迟加载，又不会出现性能问题。  
4)静态内部类：类似饿汉式，但是又做到了延迟加载，定义一个内部类，当外部类被加载的时候，并不会创建内部类的实例对象，只有当调用getInstance()方法时，内部类才会被加载创建instance实例，既保证了线程安全，又做到了延迟加载。  
5)枚举类：通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。  

2.（选做）maven/spring 的 profile 机制，都有什么用法？  
maven profile可以在编译阶段，通过mvn命令加参数 -PprofileId 来手工激活使用对应的profile，方便在不同的环境使用不同的配置。  
spring profile可以让spring根据环境的不同来创建合适的bean。  

3.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。  
hibernate是全自动的，可以通过对象与数据库的映射来自动生成sql，而mybatis是半自动的，支持编写动态sql，相比hibernate更灵活；  
hibernate的sql是程序调用到的时候才生成的，不直观，且不易于优化sql，而mybatis是手动编写的，更直观，对DBA较友好；  
hibernate配置要比mybatis复杂得多，学习成本也比mybatis高。但也正因为mybatis使用简单，才导致它要比hibernate关心很多技术细节；  
hibernate数据库可移植性较好，而mybatis依赖于sql，可移植性较差。  

4.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。  
代码：homework02 -> starter  
      mystarter-spring-boot-starter  
      
5.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。  
代码：homework02 -> mybatis  
用MyBatis-generator生成实体类和数据库操作类，自定义TypeHandler，转换List和字符串。  

6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：  
1）使用 JDBC 原生接口，实现数据库的增删改查操作。  
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。  
3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。  
代码：homework02 -> jdbc -> JdbcDemo01、JdbcDemo02、JdbcDemo03

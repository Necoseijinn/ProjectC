package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy;

public class ReadMe {
    /***
     * 多重代理在实现上是有区别的
     *
     * 1，JDK动态代理可以直接用生成的代理对象再去生成动态代理对象
     *    理由很简单，因为JDK动态代理需要的仅仅只是接口。
     *
     * 2，Cglib动态代理不能直接用生成的代理对象再去生成动态代理对象
     *    理由是Cglib动态代理需要根据传入的类去生成代理对象
     *    而第一层动态代理对象的类结构已经发生了变化
     *    Cglib会为生成的代理对象类添加一个方法叫做 newInstance
     *    而如果用动态代理对象类再去生成一个代理对象就相当于有两个 newInstance 方法
     *    所以会报如下的错误
     *       Caused by: java.lang.ClassFormatError: Duplicate method name "newInstance" with signature "..........
     *
     *       at java.lang.ClassLoader.defineClass1(Native Method)
     *
     *       at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
     *    所以要想实现Cglib多重动态代理就需要依赖于别的手段
     *    思路就是构建一个动态代理的调用链（类似于过滤器链）
     *    开始方法正向调用，结束方法逆向调用。
     *    参考Spring的多重动态代理：
     *       Spring 创建代理的代码位于 ：org.springframework.aop.framework.CglibAopProxy#getProxy
     *       Spring AOP 拦截器类：org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor
     *    解释：
     *       Cglib的多重动态代理不是直接代理套代理
     *       而是生成一个动态代理对象
     *       该代理对象拥有多个切面链，并依次拦截执行切面方法。
     */
}

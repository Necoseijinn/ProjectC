package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

    /***
     * chain
     * 每一个 MyInterceptor 对象都维护着一个 chain 对象
     * 代表该动态代理对象的层数，也就是多重代理的层次。
     * 实际使用代理链的形式来实现。
     */
    private Chain chain;

    /** 初始化 chain 属性 **/
    public MyInterceptor(Chain chain){
        this.chain=chain;
    }

    /***
     *
     * 重点方法
     * Cglib的多重动态代理不是直接代理套代理
     * 而是生成一个动态代理对象
     * 该代理对象拥有多个切面链，并依次拦截执行切面方法。
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return chain.proceed(method,objects);
    }
}

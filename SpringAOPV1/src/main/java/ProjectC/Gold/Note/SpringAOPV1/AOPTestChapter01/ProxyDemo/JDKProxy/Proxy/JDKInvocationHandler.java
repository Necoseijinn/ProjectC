package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.ProxyDemo.JDKProxy.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 *
 * 动态代理类的执行器
 * 当动态生成的代理对象调用方法时，会先调用执行器的invoke方法，并把接口的方法对象以及方法参数传过去。
 * 然后在执行器的invoke方法中就可以自定义前置后置处理。
 * 而被代理对象也会被传入此执行器中(构造函数传入或者set都行)，用于最终调用方法对象invoke时候传入invoke的对象。
 * 比如
 *    method.invoke(target,args)
 *
 * @param <T>
 */
public class JDKInvocationHandler<T> implements InvocationHandler {

    /**
     * 被代理对象
     **/
    private T t;

    /**
     * 利用构造函数传入被代理对象
     **/
    public JDKInvocationHandler(T t) {
        this.t = t;
    }

    /***
     *
     * 生成代理对象
     * 需要的参数有 类的加载器（传入类的类加载器即可），用于生成代理类的被代理对象所实现的接口，以及一个执行器。
     * 类的加载器只需要能加载自己同样层级类即可
     * 接口是被代理对象所实现的接口，理由是作为通用入口以及方法的动态绑定。
     * 而执行器由于本身这个类就是执行器，所以只需要传入this即可。
     *
     * @param claz
     * @return
     */
    public Object getProxy(Class claz) {

        /***
         *
         * 通过 Proxy.newProxyInstance(....)方法来动态生成一个代理对象。
         *
         */
        Object proxy = Proxy.newProxyInstance(claz.getClassLoader(), claz.getInterfaces(), this);

        return proxy;
    }

    /***
     *
     * 动态代理对象在执行对应的方法时候，会执行执行器的invoke方法，并把接口方法对象以及调用方法时传入的参数传过来。
     *
     * 实际执行业务方法的是
     *    Object obj = method.invoke(t,args);
     *
     * 具体原理参考 ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ForTest.StaticProxyTest
     * 动态生成的代理对象的类信息参考 ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ForTest.JDKProxyExplain
     *
     * 在这一步之前和之后可以做一些别的处理。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /** 返回的对象（目标方法处理结果） **/
        Object obj = null;

        try {

            /** 前置方法或者说目标方法执行前执行的方法 **/
            before(t, method);

            /** 执行被代理类的方法 **/
            obj = method.invoke(t, args);

            /** 返回方法或者说目标方法执行完后执行的方法 **/
            afterReturn(t, method);

        } catch (Throwable throwable) {

            /** 异常处理方法或者说目标方法抛出异常后执行的方法 **/
            afterThrowing(t, method);

        } finally {

            /** 后置方法或者说代理方法结束后执行的方法 **/
            after(t, method);

        }

        return obj;
    }

    private void afterReturn(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSimpleName() + "的" + method.getName() + "方法的返回处理方法]");
    }

    private void afterThrowing(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSimpleName() + "的" + method.getName() + "方法的异常处理方法]");
    }

    private void after(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSimpleName() + "的" + method.getName() + "方法的后置处理方法]");
    }

    private void before(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSimpleName() + "的" + method.getName() + "方法的前置处理方法]");
    }
}

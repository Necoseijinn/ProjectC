package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.transform.impl.InterceptFieldCallback;

import java.lang.reflect.Method;

/***
 *
 * Cglib动态代理的控制器，包装了获取代理对象的方法。
 * 主要实现接口：MethodInterceptor
 * Cglib不同于JDK动态代理，被代理对象无须实现接口。
 *
 * JDK动态代理利用多态下动态绑定调用子类方法
 * Cglib动态代理利用子类对象（代理对象）调用super.methodName(..)来调用父类被代理对象的方法
 *
 */
public class CglibProxy implements MethodInterceptor {

    /***
     *
     * 获取代理对象的包装方法
     *
     * @param clz
     * @return
     */
    public Object getProxy(Class clz) {

        /** 主要核心对象Enhancer **/
        Enhancer enhancer = new Enhancer();

        /** 设置被代理对象的类，用于动态生成代理对象 **/
        enhancer.setSuperclass(clz);

        /** 设置回调的控制器，本类就是控制器所以把自己传进去。 **/
        enhancer.setCallback(this);

        /** 返回动态生成的代理对象 **/
        return enhancer.create();

        /** 也可以使用Enhancer的静态方法来完成动态代理对象的创建 **/
        /** return Enhancer.create(clz,this); **/
    }

    /***
     *
     * 控制器的核心方法
     * 使用 proxy.invokeSuper(obj, args); 来完成对目标方法的执行
     *
     *
     * @param obj 此obj为动态生成的代理对象
     * @param method 此方法为被代理对象的目标方法
     * @param args 方法被调用时传入的参数
     * @param proxy 在代理对象中生成的MethodProxy的实例，带有invokeSuper方法，详细原理参考
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        /** 返回的对象（目标方法处理结果） **/
        Object object = null;

        try {

            /** 前置方法 **/
            before(obj, method);

            /** 通过下面的语句完成对被代理对象方法的调用 **/
            object = proxy.invokeSuper(obj, args);

            /** 返回方法 **/
            afterReturn(obj, method);

        } catch (Throwable throwable) {

            /** 异常处理方法 **/
            afterThrowing(obj, method);

        } finally {

            /** 后置方法 **/
            after(obj, method);
        }

        return object;
    }

    private void afterReturn(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSuperclass().getSimpleName() + "的" + method.getName() + "方法的返回处理方法]");
    }

    private void afterThrowing(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSuperclass().getSimpleName() + "的" + method.getName() + "方法的异常处理方法]");
    }

    private void after(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSuperclass().getSimpleName() + "的" + method.getName() + "方法的后置处理方法]");
    }

    private void before(Object obj, Method method) {
        System.out.println("[" + obj.getClass().getSuperclass().getSimpleName() + "的" + method.getName() + "方法的前置处理方法]");
    }
}

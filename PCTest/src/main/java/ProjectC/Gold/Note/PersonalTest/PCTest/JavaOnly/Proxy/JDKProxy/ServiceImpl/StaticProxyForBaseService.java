package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;

/***
 *
 * 静态代理对象类
 * 能代理实现了 BaseService 接口的所有类对象
 * 原理就是包装了被代理对象
 * 通过传入被代理对象，在代理对象执行方法时，可以执行自己的逻辑顺带执行被代理对象的同一方法。
 *
 */
public class StaticProxyForBaseService implements BaseService {

    /***
     * 用于接收被代理对象
     * 只能接收 BaseService 接口的实现类
     * 因为这个代理对象类实现的就是 BaseService
     * 静态代理其实本质就是个包装类
     */
    private BaseService baseService;

    /***
     *
     * 利用构造函数接受被代理对象
     *
     * @param baseService
     */
    public StaticProxyForBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public void add(String subject) {

        System.out.println("=========Get database connection=========");
        baseService.add(subject);
        System.out.println("=========Close database connection=========");
    }

    @Override
    public String get() {
        System.out.println("=========Get database connection=========");
        String s = baseService.get();
        System.out.println("=========Close database connection=========");
        return s;
    }
}

package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.AOPTest;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Core.Target;
import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Proxy.ProxyFactory;

public class AOPTestChapter01MultipleProxyByCglib {

    public static void main(String[] args) {
        /** 被代理对象 **/
        Target target = new Target();
        /** 获取生成Proxy的工厂 **/
        ProxyFactory proxyFactory = ProxyFactory.getProxyFactory();
        /** 获取多重动态代理对象 **/
        Target proxy = (Target)proxyFactory.getProxy(target, 3);
        /** 调用代理对象的对应方法进行测试 **/
        proxy.show();
        /** 测试多次调用 **/
        proxy.show();
    }
}

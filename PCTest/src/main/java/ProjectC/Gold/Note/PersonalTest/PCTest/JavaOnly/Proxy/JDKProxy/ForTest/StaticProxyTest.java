package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ForTest;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl.ProductService;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl.StaticProxyForBaseService;

public class StaticProxyTest {
    public static void main(String[] args) {

        /** 被代理对象 **/
        ProductService productService = new ProductService();

        /** 生成一个代理对象并把被代理对象传入代理对象（包装类行为） **/
        StaticProxyForBaseService staticProxyForBaseService = new StaticProxyForBaseService(productService);

        /** 调用代理对象的add方法，回去执行代理对象自己的逻辑顺带执行被代理对象的add方法 **/
        staticProxyForBaseService.add("Nokia880");
    }
}

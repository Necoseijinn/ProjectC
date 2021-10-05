package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;

/***
 *
 * 被代理对象类
 * 实现了BaseService接口
 *
 */
public class ProductService implements BaseService {

    private int i = 1;

    @Override
    public void add(String subject) {
        System.out.println("A product named " + subject + " has been added to the database");
    }

    @Override
    public String get() {
        return "Nokia9" + (i++);
    }
}

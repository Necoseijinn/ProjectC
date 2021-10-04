package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;

/***
 *
 * 被代理对象类
 * 实现了BaseService接口
 *
 */
public class CategoryServie implements BaseService {

    @Override
    public void add(String subject) {
        System.out.println("A category named "+subject+" has been added to the database");
    }

    @Override
    public String get() {
        System.out.println("We only have Nokia Line.");
        return "Nokia Line";
    }
}

package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.ProxyDemo.JDKProxy.ServiceImpl;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.ProxyDemo.JDKProxy.Service.BaseService;

/***
 *
 * 被代理对象类
 * 实现了BaseService接口
 *
 */
public class CategoryServie implements BaseService {

    @Override
    public void add(String subject) {
        System.out.println("A category named " + subject + " has been added to the database");
    }

    @Override
    public String get() {
        System.out.println("We only have Nokia Line.");
        return "Nokia Line";
    }
}

package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Service;

/***
 * 被代理对象类
 */
public class ProductService {
    public void add(String product) {
        System.out.println("ProductService have add a product named " + product + " into the database.");
    }
}

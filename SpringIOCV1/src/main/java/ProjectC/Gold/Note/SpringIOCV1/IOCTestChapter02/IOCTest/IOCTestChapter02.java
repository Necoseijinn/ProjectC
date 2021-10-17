package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.CarBook;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.Cooker;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.Dinner;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/***
 *
 * test01
 * 需求：正确的为各种属性赋值
 *      测试使用null值，引用类型赋值（引用其他bean、引用内部bean）集合类型赋值（List、Map、Properties）、
 * 		util名称空间创建集合类型的bean级联属性赋值
 *
 * test02
 * 需求：通过继承实现bean配置信息的重用
 *      通过abstract属性创建一个模板ban
 *      bean之间的依赖
 *
 * test03
 * 需求：配置通过静态工厂方法创建的bean
 *      实例工厂方法创建的bean、FactoryBean
 */
public class IOCTestChapter02 {

    private ApplicationContext applicationContext;

    @Test
    public void test01() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter02.xml");
        CarBook carBook = (CarBook) applicationContext.getBean("carBook1");
        System.out.println(carBook);
        Product product2 = (Product) applicationContext.getBean("product2");
        System.out.println(product2);
    }

    @Test
    public void test02() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter02.xml");
        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");
        Dinner dinner = applicationContext.getBean(Dinner.class);
        Cooker cooker = applicationContext.getBean(Cooker.class);
        System.out.println(cooker);
    }

    @Test
    public void test03() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter02.xml");
        Product product1190 = (Product) applicationContext.getBean("product1190");
        System.out.println(new Date().toString() + " 以下是由静态工厂【PhoneStaticFactory】生产的实例：");
        System.out.println(product1190);
        Product product2590 = (Product) applicationContext.getBean("product2590");
        System.out.println(new Date().toString() + " 以下是由实例工厂【PhoneInstanceFactory】生产的实例：");
        System.out.println(product2590);
        Product product3990 = (Product) applicationContext.getBean("product3990");
        System.out.println(new Date().toString() + " 以下是由FactoryBean【FactoryBeanForPhone】生产的实例：");
        System.out.println(product3990);
    }
}

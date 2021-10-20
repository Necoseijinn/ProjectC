package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo.Product;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.Car;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Controller.Chapter05Servlet;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/***
 *
 * test01
 * 需求：
 *      泛型依赖注入
 *      Spring 4.x中可以为子类注入子类对应的泛型类型的成员变量的引用。
 *
 * test02
 * 需求：
 *      利用<import>来导入别的Springxml配置文件
 *      可以实现多个容器组成一个容器
 *      此处测试获取iocTestChapter01.xml的ID为product的Bean
 *
 */
@ContextConfiguration(value = "classpath:iocTestChapter05.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class IOCTestChapter05 {

    @Autowired
    private Chapter05Servlet chapter05Servlet;

    @Autowired
    @Qualifier(value = "car01")
    private Car car;

    @Autowired
    private List<BaseService> baseServices;

    @Test
    public void test01() {
        chapter05Servlet.get();
        System.out.println(new Date().toString() + " 这个输出主要说明用父类类型可以获取子类类型的组件 ： " + baseServices);
    }

    @Test
    public void test02() {
        System.out.println(new Date().toString() + " 利用import导入Chapter01的Bean\"product\" : " + car);
    }
}

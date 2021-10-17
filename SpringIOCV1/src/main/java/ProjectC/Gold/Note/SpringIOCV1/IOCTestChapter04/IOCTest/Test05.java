package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller.ProductServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/***
 * 需要引入spring-test依赖
 * 使用@ContextConfiguration(locations = "classpath:iocTestChapter04.xml")来指定顺带启动的IOC容器
 * 使用@RunWith(value = SpringJUnit4ClassRunner.class)指定用哪种驱动来运行单元测试
 *      此处使用的是SpringJUnit4ClassRunner.class也就是Spring提供的JUnit4驱动
 */
@ContextConfiguration(locations = "classpath:iocTestChapter04.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class Test05 {

    @Autowired
    private ProductServlet productServlet;

    @Test
    public void test05() {
        productServlet.get();
    }
}

package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller.ProductControllerForTest04;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller.ProductServlet;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Service.ProductService;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.TestExclude.BookExclude;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.TestInclude.BookInclude;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 *
 * 本章介绍利用注解添加组件到IOC容器的方法
 * 注解添加组件固然是方便，但也有局限。
 * 注解只能添加到自己项目中，不能添加到导入的包中。
 * 导入的包的类要把其添加到IOC容器中就需要用到配置文件的方式。
 * 全采用配置文件会导致配置文件过于臃肿，所以也不可取。
 * 最理想就是注解和配置文件相结合使用。
 *
 * test01
 * 需求：
 *      使用不同类型注解把不同类型组件添加到IOC容器中
 *
 * test02
 * 需求：
 *      设置注解添加组件并指定组件的ID
 *      详情查看以下的类
 *          ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Service.ProductService
 *
 * test03
 * 需求：
 *      使用<context:include-filter>来指定扫描包时只扫描哪些组件
 *          要想实现只扫描哪些组件必须要指定<context:component-scan>的use-default-filters属性为false
 *      使用<context:exclude-filter>来指定扫描包时候要排除的组件
 *      以上两个标签均为<context:component-scan>的子标签
 *
 * test04
 * 需求：
 *      演示AutoWired实现根据类型自动装配组件
 *          给组件类中的属性添加AutoWired注解之后
 *          IOC容器会在添加该组件的时候先在容器中寻找匹配的组件赋值给添加了该注解的属性
 *          如果找不到就赋值默认值（null）
 *          Spring在IOC容器中寻找匹配组件的过程：
 *              1.1 找到一个 ===> 赋值
 *              1.2 没找到 ===> 抛异常
 *              1.3 找到多个
 *                  1.31 找到多个之后会先按照属性的变量名去匹配IOC容器中的id，匹配到同id就赋值。
 *                  1.32 没匹配上id ===> 抛异常
 *          AutoWired默认是使用变量名去匹配IOC容器中的id的。
 *          如果想要指定一个id去匹配IOC容器中的id，需要添加一个注解 --- Qualifier
 *              指定Qualifier的value属性作为拿去匹配的id
 *
 *          AutoWired标注的属性默认是必须要在IOC容器中找得到匹配组件的，如果找不到就抛异常，
 *          我们可以通过指定AutoWired的required属性为false来实现找不到匹配组件也不会抛异常。
 *          详情参考
 *              ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller.ProductServlet
 *
 *          此外AutoWired以及Qualifier还可以标注在方法以及方法的参数上
 *          具体参照以下的类
 *              ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller.ProductControllerForTest04
 *
 *          除了AutoWired，Spring还支持Resource注解来实现同样的功能
 *          二者区别就是Resource是java的注解
 *          而AutoWired是Spring自定义的注解
 *          如果使用AutoWired那么当框架切换的时候就不行了。
 *          如果使用Resource注解，当换一个IOC容器框架也还能使用，前提是该框架支持Resource注解。
 *          也就是说相比AutoWired，Resource兼容性更强，只不过目前IOC容器也就Spring一家独大，所以目前AutoWired就行。
 *          还有一种叫Inject注解是EJB框架使用，不考虑。
 *
 *          当AutoWired注解标注在数组、List、Map上时。
 *             1，数组
 *                 Spring会把所有匹配的bean进行自动装配（根据数组类型）
 *             2，List
 *                 Spring会读取该集合的类型信息，然后自动装配所有与之兼容的bean。
 *             3，Map
 *                 若该Map的键值为String，那么Spring将自动装配与值类型兼容的bean作为值，并以bean的id值作为键。
 *          详情参考以下的类：
 *              ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Pojo.Category
 *
 * test05
 * 需求：
 *      利用Spring的单元测试
 *      作用：可以在单元测试启动的时候顺便启动IOC容器
 *      详情参考以下类：
 *
 *
 *
 */
public class IOCTestChapter04 {

    ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("iocTestChapter04.xml");

    @Test
    public void test01() {
        ProductServlet productServlet01 = (ProductServlet) applicationContext.getBean("productServlet");
        ProductServlet productServlet02 = (ProductServlet) applicationContext.getBean("productServlet");
        System.out.println("通过注解添加的组件默认是单例模式的吗？ " + (productServlet01 == productServlet02));
    }

    @Test
    public void test02() {
        ProductService productService01 = (ProductService) applicationContext.getBean("forTest02ProductService");
        ProductService productService02 = (ProductService) applicationContext.getBean("forTest02ProductService");
        System.out.println("通过指定注解属性添加的多例和自定义ID的组件: 是否是同一实例" + (productService01 == productService02));
    }

    @Test
    public void test03() {
        BookInclude bookInclude = applicationContext.getBean(BookInclude.class);
        try {
            BookExclude bookExclude = applicationContext.getBean(BookExclude.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04() {
        ProductServlet productServlet = applicationContext.getBean(ProductServlet.class);
        productServlet.get();
        System.out.println("===========================================");
        ProductControllerForTest04 productControllerForTest04 = applicationContext.getBean(ProductControllerForTest04.class);
        productControllerForTest04.get();
    }
}

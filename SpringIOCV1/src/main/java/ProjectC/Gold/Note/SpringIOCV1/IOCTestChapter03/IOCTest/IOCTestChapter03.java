package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.Boss;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.Person;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.UserName;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.Worker;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

/***
 *
 * test01
 * 需求：
 *      创建带有生命周期方法的bean
 *          生命周期：bean的创建到销毁
 * 		    ioc容器中注册的bean：
 * 		    1）单例bean
 * 		        （容器启动的途中）构造器被调用 ---〉 bean定义的初始化方法 ---〉 （容器关闭的途中）bean定义的销毁方法
 * 		    2）多例bean
 * 		        （获取bean的时候）构造器被调用 ---〉 bean定义的初始化方法 ---〉 （GC并且被判定为垃圾的时候）bean定义的销毁方法
 * 		        注意容器关闭并不会调用多例bean定义的销毁方法
 *
 * 		    我们可以自定义bean的初始化方法和销毁方法来做一些bean的初始化操作和销毁操作
 * 		    初始化方法和销毁方法必须满足下面的要求
 * 		        The method must have no arguments,but may throw any exception
 *          简单来说就是初始化和销毁方法不能有参数
 *
 * test02
 * 需求：
 *      演示bean的后置处理器:BeanPostProcessor
 *      Spring提供了一个接口 BeanPostProcessor 会在容器启动时候的某一阶段把实现了该接口的bean特殊处理
 *      该bean被容器识别后，到了bean的创建阶段会作为过滤器一样的存在。
 *      拦截所有bean的初始化方法
 *      BeanPostProcessor接口有两个方法
 *          postProcessBeforeInitialization
 *              在bean初始化方法被调用之前执行
 *          postProcessAfterInitialization
 *              在bean初始化方法被调用之后执行
 *      注意无论bean配置或没配置初始化方法都会执行拦截器的前后方法。
 *
 * test03
 * 需求：
 *      演示引用外部属性文件（需要依赖context名称空间）
 *      举个使用例子
 *          在使用数据库连接池的时候需要配置数据库连接池的属性
 *          配置很多的情况下如果有一处需要更改找起来就很麻烦
 *          我们可以把所有配置相关的属性写在一个properties文件中
 *          并把该文件引入xml中，用key去引用对应的属性，这样即便需要更改我们也只需要更改properties文件就行了。
 *      此演示会演示引用和不引用外部属性文件的差别。
 *      详情请看iocTestChapter03.xml
 *
 * test04
 * 需求：
 *      演示 autowire ---〉 自动装配
 *      自动装配前提条件
 *          1，自动装配的对象属性拥有 setter 方法或者拥有包含该属性的构造器
 *          2，容器中存在与属性对应类型的bean（不存在会注入null）
 *      此处只演示xml配置的情况，注解的autowire请参考
 *
 * test05
 * 需求：
 *      演示 SpEL ===>　Spring Expression Language　Spring表达式语言
 *      SpEL的主要功能：
 *          1，使用字面量
 *          2，引用其他bean
 * 		    3，引用其他bean的某个属性值
 * 		    4，调用非静态方法以及调用静态方法
 * 		    5，使用运算符
 *
 */
public class IOCTestChapter03 {

    /***
     * ConfigurableApplicationContext是ApplicationCont的一个子接口
     * 提供了更丰富的方法包括了close方法（关闭容器）
     * 此处为了演示在容器关闭时会调用单例bean的销毁方法使用了该接口
     */
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void test01() {

        /***
         * 此处由于Boss是单例bean所以自始至终Boss的构造函数初始化函数和销毁函数只会被调用一次。
         * 而Worker是多例bean，每次获取都会调用一次Worker的构造函数和初始化函数。
         * 注意，Worker是多例bean，容器关闭的时候不会调用其销毁方法
         */

        /** 容器启动 **/
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");
        System.out.println(new Date().toString() + " 容器初始化完成");
        Boss boss = applicationContext.getBean(Boss.class);
        Worker worker1 = applicationContext.getBean(Worker.class);
        Worker worker2 = applicationContext.getBean(Worker.class);
        /** 容器关闭 **/
        applicationContext.close();
        System.out.println(new Date().toString() + " 容器关闭了");

    }

    @Test
    public void test02() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");
    }

    @Test
    public void test03() throws SQLException {

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");
        System.out.println(new Date().toString() + " 容器初始化完成");

        DataSource dataSource1 = (DataSource) applicationContext.getBean("dataSource1");
        System.out.println(dataSource1.getConnection());

        System.out.println("===================分割线=====================");

        DataSource dataSource2 = (DataSource) applicationContext.getBean("dataSource2");
        System.out.println(dataSource2.getConnection());

        System.out.println("===================分割线=====================");

        UserName userName = applicationContext.getBean(UserName.class);
        System.out.println(userName);
    }

    @Test
    public void test04() {

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");
        System.out.println(new Date().toString() + " 容器初始化完成");
        Person person01 = (Person) applicationContext.getBean("person01");
        System.out.println(new Date().toString() + " Person01 : " + person01);
        Person person02 = (Person) applicationContext.getBean("person02");
        System.out.println(new Date().toString() + " Person02 : " + person02);
        Person person03 = (Person) applicationContext.getBean("person03");
        System.out.println(new Date().toString() + " Person03 : " + person03);
    }

    @Test
    public void test05() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");
        System.out.println(new Date().toString() + " 容器初始化完成");
        Person person04 = (Person) applicationContext.getBean("person04");
        System.out.println(new Date().toString() + " 使用SpEL装配的Person04 : " + person04);
    }

}

package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.IOCTest;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo.*;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/***
 *
 * test01
 * 介绍ClassPathXmlApplicationContext
 * 注册在容器中的对象（bean）在容器创建完成的时候就创建好了
 * 注册在容器的bean对象默认是单例的，可以通过 bean 标签的 scope 属性来设置非单例
 * scope对应值
 *    singleton 单例
 *    prototype 多例
 *    不写       默认单例
 * bean的获取可以通过 getBean(..) 方法获取，传入的参数可以是bean的名称也可以是bean的类
 *
 * test02
 * 容器中如果没有这个组件，那么在尝试获取对应bean实例的时候就会报错
 *    org.springframework.beans.factory.NoSuchBeanDefinitionException
 *    No bean named 'person03' is defined
 *
 * test03
 * 容器是通过setter方法给属性赋值的
 * 如果不存在setter方法就不会赋值，还会报错（解开iocTestChapter01.xml的cat的bean下的注释可以看报错）
 *    org.springframework.beans.factory.BeanCreationException
 *    Bean property 'name' is not writable or has an invalid setter method.
 *
 * test04
 * 如果容器中存在多个相同类但不同id的bean
 * 用id去getBean的时候没有问题
 * 但是用类去getBean的时候会报错
 *    org.springframework.beans.factory.NoUniqueBeanDefinitionException:
 *    No qualifying bean of type 'ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo.Bird' available:
 *    expected single matching bean but found 2: bird1,bird2
 * 使用id与使用类去获取bean的区别
 * 二者虽为同一方法的重载但是有很大区别
 *    1，当存在多个同类不同id的bean时，只有用id获取bean不回报错。
 *    2，当用id获取bean时，需要强转，而用类获取bean时不需要强转。
 *
 * 测试使用bean的命名空间给bean进行简要赋值（前提是必须有setter方法）
 *
 * test05
 * 没有setter方法的时候可以通过构造器对bean的属性进行赋值。
 *
 */
public class IOCTestChapter01 {

    /**
     * ApplicationContext（IOC容器的接口）
     **/
    private ApplicationContext applicationContext;

    @Test
    public void test01() {

        /***
         * ClassPathXmlApplicationContext
         * ClassPathXml 意思就是classpath下的xml
         * ApplicationContext就是IOC容器
         * 总的意思就是根据classpath下的xml的信息去生成ApplicationContextIOC容器
         * 这里的classpath是java运行时环境，意味着必须到编译后的路径去找，而不是编译前。
         * 具体编译后在哪个地方看各个编辑器或者maven框架
         * 此处为target文件夹下的classes文件夹
         */
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter01.xml");

        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");

        /***
         * 由于Product类的bean设置了 scope="prototype" 是多例的
         * 所以不会在容器创建的时候就创建
         * 而是每次getBean的时候创建一个新的
         *
         * 此处通过传入bean的id来获取bean
         */
        Product product = (Product) applicationContext.getBean("product");

        /***
         * 由于Category类的bean对象是单例的
         * 所以category1跟category2是同一个对象
         *
         * 此处通过传入要获取bean的类来获取bean
         */
        Category category1 = (Category) applicationContext.getBean(Category.class);
        Category category2 = (Category) applicationContext.getBean(Category.class);
        System.out.println(new Date().toString() + " 【Category】的bean由于是单例的，每次获取都是同样的对象。category1=category2 ? : " + (category1 == category2));

        System.out.println(new Date() + " 以下是product实例的信息，由于只配置了基本数据类型的属性，复杂类型的属性没有配置所以就没有值。");
        System.out.println(product);

    }

    @Test
    public void test02() {

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter01.xml");

        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");

        /***
         * 获取容器中不存在的bean实例会报错
         */
        try {
            applicationContext.getBean("NotExist");
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test03() {

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter01.xml");

        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");

        /***
         * 由于Cat类的bean设置了 scope="prototype" 多例模式
         * 所以不会在IOC容器创建的时候就创建
         * 所以不会在容器创建的时候报错
         *
         * 当尝试获取Cat类的bean对象时
         * 容器会尝试创建Cat的bean对象，这时候由于没有setter方法所以就会报错。
         */
        try {
            Cat cat = (Cat) applicationContext.getBean("cat");
        } catch (BeanCreationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04() {
        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter01.xml");
        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");
        Bird bird1 = (Bird) applicationContext.getBean("bird1");
        Bird bird2 = (Bird) applicationContext.getBean("bird2");
        System.out.println("两个类相同id不同的bean，通过id获取这两个bean，查看是否是同一个实例。bird1=bird2 ? : " + (bird1 == bird2));
        System.out.println("下面尝试使用Bird类去获取bean，会报错");
        try {
            Bird bird3 = applicationContext.getBean(Bird.class);
        } catch (NoUniqueBeanDefinitionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05() {

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter01.xml");
        System.out.println(new Date().toString() + " 【IOC容器已经创建完成】往上翻查看已经生成的bean实例，非单例的bean不会在容器创建的时候创建");
        System.out.println("下面尝试获取通过有参构造器创建的bean对象Dog：");
        Dog dog = applicationContext.getBean(Dog.class);
        System.out.println(dog);
    }

}

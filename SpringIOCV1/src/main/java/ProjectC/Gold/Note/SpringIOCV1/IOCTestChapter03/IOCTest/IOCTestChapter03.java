package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.IOCTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
 */
public class IOCTestChapter03 {

    private ApplicationContext applicationContext;

    @Test
    public void test01(){

        applicationContext = new ClassPathXmlApplicationContext("iocTestChapter03.xml");


    }

}

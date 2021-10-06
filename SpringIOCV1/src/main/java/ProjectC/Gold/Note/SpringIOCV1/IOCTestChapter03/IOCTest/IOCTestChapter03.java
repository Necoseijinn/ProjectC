package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.IOCTest;

/***
 *
 * test01
 * 需求：
 *      创建带有生命周期方法的bean
 *          生命周期：bean的创建到销毁
 * 		    ioc容器中注册的bean：
 * 		    	1，单例bean，容器启动的时候就会创建好，容器关闭也会销毁创建的bean
 * 		    	2，多实例bean，获取的时候才创建
 * 		    我们可以为bean自定义一些生命周期方法；Spring在创建或者销毁的时候就会调用指定的方法
 * 		    自定义初始化方法和销毁方法； The method must have no arguments,but may throw any exception
 *
 */
public class IOCTestChapter03 {
}

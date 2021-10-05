package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ForTest;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 *
 * 演示动态代理原理之一的动态绑定
 * 子类实例被父类接收，或者说多态的情况下，调用方法会去调用子类的实现或者覆盖方法。
 *
 */
public class JDKProxyExplain {
    public static void main(String[] args) {

        /** 多态的形式 **/
        BaseService baseService = new ProductService();

        System.out.println("===============================================");
        baseService.add("子类实例被父类或者接口接收并调用被子类实现或者被覆盖的方法");
        System.out.println("===============================================");

        /** 获取父类或者接口的add方法 **/
        Method baseServiceAdd = null;
        try {
            baseServiceAdd = BaseService.class.getMethod("add", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /** 获取子类或者实现类的add方法 **/
        Method productServiceAdd = null;
        try {
            productServiceAdd = ProductService.class.getMethod("add", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("BaseService add method is equals to ProductService add method? " + (baseServiceAdd == productServiceAdd));
        System.out.println("===============================================");
        System.out.println("baseServiceAdd : " + baseServiceAdd);
        System.out.println("===============================================");
        System.out.println("productServiceAdd : " + productServiceAdd);
        System.out.println("===============================================");

        try {
            productServiceAdd.invoke(baseService, "子类实例调用自己的add方法");
            System.out.println("===============================================");
            baseServiceAdd.invoke(baseService, "当子类实例被父类或者接口接收后，调用父类或者接口的add方法");
            System.out.println("===============================================");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ForTest;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Proxy.JDKInvocationHandler;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.Service.BaseService;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.JDKProxy.ServiceImpl.ProductService;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/***
 *
 * 此类用于测试动态代理
 * 以及生成动态代理对象的class文件到硬盘上
 *
 */
public class ProxyTest {

    public static void main(String[] args) {

        /** 被代理对象 **/
        ProductService productService = new ProductService();
        /** 执行器（内含动态生成代理对象的方法） **/
        JDKInvocationHandler<BaseService> baseServiceJDKInvocationHandler = new JDKInvocationHandler<BaseService>(productService);

        /***
         *
         * 生成动态代理对象
         * 需要一个被代理对象的类加载器和被代理对象类所实现的接口以及一个执行器
         * 详情可以进到方法里看
         *
         */
        BaseService proxy = (BaseService) baseServiceJDKInvocationHandler.getProxy(productService.getClass());

        /** 代理对象调用add方法，会进行亲后处理以及调用被代理类的add方法 **/
        proxy.add("Nokia1100");

        /** 生成JDK动态做成的代理对象类文件 **/
        //saveGeneratedJDKProxyFiles();
    }

    /***
     *
     * 生成JDK动态做成的代理对象类文件
     *
     */
    private static void saveGeneratedJDKProxyFiles() {

        /***
         *
         * 重头戏
         * 利用 ProxyGenerator.generateProxyClass(...) 静态方法获取动态生成的代理类文件的字节数组
         * 需要传入被代理对象类的名字以及被代理对象类所实现的接口
         *
         */
        byte[] bytes = ProxyGenerator.generateProxyClass(ProductService.class.getName(), ProductService.class.getInterfaces());

        /***
         *
         * 利用 FileOutputStream 输出字节数组到一个文件
         *
         */
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("src\\main\\webapp\\ProxyClass\\JDKProxy\\ProductService.class");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

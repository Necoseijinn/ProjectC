package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.ForTest;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Proxy.CglibProxy;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Service.ProductService;
import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Field;
import java.util.Properties;

/***
 *
 * 此类用于测试和演示动态代理以及生成动态代理对象的类。
 *
 */
public class TestCglibProxy {
    public static void main(String[] args) {

        /***
         * 用于生成动态代理类的方法
         * 原理参照方法的注释
         */
        //saveGeneratedCGlibProxyFiles(System.getProperty("user.dir"));

        /** new一个Cglib动态代理专用的控制器 **/
        CglibProxy cglibProxy = new CglibProxy();

        /** 获取Cglib动态生成的代理对象 **/
        ProductService proxy = (ProductService) cglibProxy.getProxy(ProductService.class);

        /** 通过代理对象调用指定方法 **/
        proxy.add("Nokia970");

    }

    /***
     *
     * 生成Cglib做成的动态代理类的Class文件
     * 原理就是通过设置系统配置，如下：
     *    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);
     *    System.setProperty("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
     *    通过设置添加一个键值对到系统配置中，让Cglib知道要输出生成的代理对象的类文件到指定文件夹。
     *
     * @param dir
     */
    private static void saveGeneratedCGlibProxyFiles(String dir) {

        try {

            /***
             * 添加键值对到系统properties对象中
             */
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
            System.setProperty("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");

            /***
             * 也可以使用下面的方法添加键值对到系统properties对象中
             */
            /**
             Field field = System.class.getDeclaredField("props");
             field.setAccessible(true);
             Properties props = (Properties) field.get(null);
             props.put(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);
             props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");**/

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}

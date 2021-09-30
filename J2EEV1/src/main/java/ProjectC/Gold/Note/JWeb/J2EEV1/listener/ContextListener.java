package ProjectC.Gold.Note.JWeb.J2EEV1.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/***
 * 此监听器实现了 ServletContextListener 接口
 * 代表此监听器用于监听Context的生成与销毁（基本就是web容器或者说服务器启动时生成，关闭时销毁）
 * Context即ApplicationContext 是web容器级别的作用域（最大的）
 * 对应JSP文件中的application隐式对象。
 * 对应Servlet中的request.getServletContext()对象。
 */
public class ContextListener implements ServletContextListener {

    /***
     *
     * 在Web容器启动时会生成Context容器，监听器会捕捉到Context的创建并执行下面的方法。
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(new Date().toString() + " Context has been created");
    }

    /***
     *
     * 在Web容器关闭时会销毁Context容器，监听器会捕捉到Context的销毁并执行下面的方法。
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(new Date().toString() + " Context has been created");
    }
}

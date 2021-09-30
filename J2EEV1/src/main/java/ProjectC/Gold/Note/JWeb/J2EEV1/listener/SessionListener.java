package ProjectC.Gold.Note.JWeb.J2EEV1.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/***
 * 此监听器实现了 HttpSessionListener 接口
 * 代表此监听器用于监听Session的生成与销毁
 * Session的介绍请参考　ProjectC.Gold.Note.JWeb.J2EEV1.servlet.WithCookieSessionDemo
 * 对应JSP文件中的session隐式对象。
 * 对应Servlet中的request.getSession()对象。
 */
public class SessionListener implements HttpSessionListener {

    /***
     *
     * Session的介绍请参考　ProjectC.Gold.Note.JWeb.J2EEV1.servlet.WithCookieSessionDemo
     * 简单来说就是当一个Cookie中不带SessionID的请求在访问了jsp页面或者在Servlet的处理中调用了getSession()或者getSession(true)方法
     * 就会生成Session对象并在response中把该SessionID添加到Cookie中一起返回
     *
     * 此处利用Session生成时会执行下面方法的原理
     * 实现当一个Session被创建后在Context容器中设置一个变量，每次生成一个Session对象就会让这个变量加1。
     *
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {

        System.out.println(new Date().toString() + " Session : " + se.getSession().getId() + " has been created");

        /***
         * 先通过监听器捕捉到的事件对象获取Session对象
         * 在通过Session对象获取ServletContext对象
         */
        ServletContext servletContext = se.getSession().getServletContext();

        /***
         * 获取Context容器中的变量 onlineUserCount
         * 如果不存在就新添价一个变量 onlineUserCount 并赋值为1
         * 如果存在就取出来然后加1再存回去（修改覆盖）
         */
        Integer onlineUserCount = (Integer) servletContext.getAttribute("onlineUserCount");
        if (null == onlineUserCount) {
            servletContext.setAttribute("onlineUserCount", 1);
        } else {
            onlineUserCount += 1;
            servletContext.setAttribute("onlineUserCount", onlineUserCount);
        }
    }

    /***
     *
     * Session的介绍请参考　ProjectC.Gold.Note.JWeb.J2EEV1.servlet.WithCookieSessionDemo
     * 简单来说当Session对象设置的存活时间或者说Session对象默认的存活时间到了之后Session就会被销毁
     * session的销毁不易观察到，一般说来服务器会设置session存活时间为30分钟。所以需要等待才能观察到销毁
     *
     * 此处利用Session销毁时会执行下面方法的原理
     * 实现当一个Session被销毁后在Context容器中获取一个变量并执行相应操作，每次销毁一个Session对象就会让这个变量减1。
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        System.out.println(new Date().toString() + " Session : " + se.getSession().getId() + " has been destroyed");

        /***
         * 先通过监听器捕捉到的事件对象获取Session对象
         * 在通过Session对象获取ServletContext对象
         */
        ServletContext servletContext = se.getSession().getServletContext();

        /***
         * 获取Context容器中的变量 onlineUserCount
         * 如果不存在就新添价一个变量 onlineUserCount 并赋值为0
         * 如果存在就取出来然后减1再存回去（修改覆盖）
         */
        Integer onlineUserCount = (Integer) servletContext.getAttribute("onlineUserCount");
        if (null == onlineUserCount) {
            servletContext.setAttribute("onlineUserCount", 0);
        } else {
            onlineUserCount -= 1;
            servletContext.setAttribute("onlineUserCount", onlineUserCount);
        }
    }
}

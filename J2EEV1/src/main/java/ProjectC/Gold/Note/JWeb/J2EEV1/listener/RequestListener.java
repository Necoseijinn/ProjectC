package ProjectC.Gold.Note.JWeb.J2EEV1.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/***
 * 此监听器实现了 ServletRequestListener 以及 ServletRequestAttributeListener 接口
 * 可以利用这种形式继承多个监听器只写一个监听类。
 *
 * 代表此监听器用于监听Request的生成与销毁（在一次请求到达服务器的时候生成，当服务器处理完请求时销毁）
 * 对应JSP文件中的request隐式对象。
 * 对应Servlet中的request对象。
 */
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

    /***
     *
     * 当用户发起一次http或者https或者别的协议的请求到服务器时
     * 服务器会按照对应协议去解析这个请求
     * 然后生成一个request对象（当然还有response对象）
     * 这时候监听器就会捕捉到这一动作并执行下面的方法
     *
     * @param sre
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println(new Date().toString() + " An request(" + ((HttpServletRequest) sre.getServletRequest()).getRequestURI() + ") which is from : " + sre.getServletRequest().getRemoteAddr() + " has been created");
    }

    /***
     *
     * 当服务器处理完这个请求时
     * 也就是利用response返回结果信息之后
     * 监听器会捕捉到这一动作并执行下面的方法。
     *
     * @param sre
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println(new Date().toString() + " An request(" + ((HttpServletRequest) sre.getServletRequest()).getRequestURI() + ") which is from : " + sre.getServletRequest().getRemoteAddr() + " has been destroyed");
    }

    /***
     *
     * 当request容器上添加了某个变量时，会被该监听器捕捉到该动作并会执行下面的方法。
     * 例：
     *   JSP页面上：request.setAttribute(key,value);
     *   Servlet上：request.setAttribute(key,value);
     *
     * @param srae
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        srae.getName();
        /** value **/
        srae.getValue();
    }

    /***
     *
     * 当request容器上删除了某个变量时，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：request.removeAttribute(key);
     *    Servlet上：request.removeAttribute()(key);
     *
     * @param srae
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        srae.getName();
        /** value **/
        srae.getValue();
    }

    /***
     *
     * 当request容器上某个变量被覆盖或者说修改的时候，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：request.setAttribute(key,value);
     *    Servlet上：request.setAttribute(key,value);
     *    以上情况必须是覆盖已有的key
     *
     * @param srae
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        srae.getName();
        /** value **/
        srae.getValue();
    }
}

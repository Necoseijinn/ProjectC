package ProjectC.Gold.Note.JWeb.J2EEV1.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/***
 * 此监听器实现了 ServletContextAttributeListener 接口
 * 代表此监听器用于监听Context上Attribute的生成与销毁（基本就是web容器或者说服务器启动时生成，关闭时销毁）
 * Context即ApplicationContext 是web容器级别的作用域（最大的）
 */
public class ContextAttributeListener implements ServletContextAttributeListener {

    /***
     *
     * 当Context容器上添加了某个变量时，会被该监听器捕捉到该动作并会执行下面的方法。
     * 例：
     *    JSP页面上：application.setAttribute(key,value);
     *    Servlet上：request.getServletContext().setAttribute(key,value);
     *
     * @param event
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        event.getName();
        /** value **/
        event.getValue();
    }

    /***
     *
     * 当Context容器上删除了某个变量时，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：application.removeAttribute(key);
     *    Servlet上：request.getServletContext().removeAttribute()(key);
     *
     * @param event
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        event.getName();
        /** value **/
        event.getValue();
    }

    /***
     *
     * 当Context容器上某个变量被覆盖或者说修改的时候，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：application.setAttribute(key,value);
     *    Servlet上：request.getServletContext().setAttribute(key,value);
     *    以上情况必须是覆盖已有的key
     *
     * @param event
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        event.getName();
        /** value **/
        event.getValue();
    }
}

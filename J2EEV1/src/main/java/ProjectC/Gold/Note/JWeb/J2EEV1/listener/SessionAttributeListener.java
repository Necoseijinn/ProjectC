package ProjectC.Gold.Note.JWeb.J2EEV1.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/***
 * 此监听器实现了 HttpSessionAttributeListener 接口
 * 代表此监听器用于监听Session上Attribute的生成与销毁
 * Session的介绍请参考　ProjectC.Gold.Note.JWeb.J2EEV1.servlet.WithCookieSessionDemo
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    /***
     *
     * 当Context容器上添加了某个变量时，会被该监听器捕捉到该动作并会执行下面的方法。
     * 例：
     *   JSP页面上：session.setAttribute(key,value);
     *   Servlet上：request.getSession().setAttribute(key,value);
     *
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
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
     * 当Session容器上删除了某个变量时，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：session.removeAttribute(key);
     *    Servlet上：request.getSession().removeAttribute()(key);
     *
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
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
     * 当Session容器上某个变量被覆盖或者说修改的时候，会被监听器捕捉到该动作并执行下面的方法。
     * 例：
     *    JSP页面上：session.setAttribute(key,value);
     *    Servlet上：request.getSession().setAttribute(key,value);
     *    以上情况必须是覆盖已有的key
     *
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        /***
         * 可通过下面的方法获取添加的键值对(key和value)
         */
        /** key **/
        event.getName();
        /** value **/
        event.getValue();
    }
}

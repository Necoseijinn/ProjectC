package ProjectC.Gold.Note.JWeb.J2EEV1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginFilter implements Filter {

    /***
     *
     * 此方法会在Tomcat启动的时候调用一次,因为服务器启动的时候会去web.xml查看定义的过滤器,有定义就制造一个.
     * 而init(FilterConfig f)方法就会在被创造的时候执行,也只在被创造的时候执行
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(new Date().toString() + " Filter : LoginFilter init method has been proceed");
    }

    /***
     *
     * 在此方法中对每一个servlet请求都进行编码设定的操作
     * 包括针对servlet请求发送过来的数据进行UTF-8编码的设定
     * 以及返回数据时通过设定返回头(response header)的形式告知浏览器要用什么去解析返回的数据
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /***
         * 把传递进来的实例向下强转成HttpServletRequest和HttpServletResponse,便于调用更多方法.
         */
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        /** 获取所有不被过滤的请求uri **/
        List<String> passList = getPassList();

        /***
         * 判断目前请求是否在 passList 中，如果在就放行
         * 如果不在就往下走
         * 注意不可以不写else
         * 因为如果不写else的话在执行完 chain.doFilter(req, resp) 之后还是会回到此处往下执行
         * 我们希望的是执行完 chain.doFilter(req, resp) 就结束了
         * 所以必须要接else
         */
        if (passList.contains(req.getRequestURI().substring(req.getContextPath().length()))) {
            chain.doFilter(req, resp);
        } else {

            /***
             * 过滤对象都要在此处判断一次是否已经登录过了
             * 登录过的依据就是存在Session对象并且Session对象中保存了userName的信息
             */
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("userName") == null) {
                /***
                 * 没有Session或者Session中没有userName的情况
                 * 执行重定向(并在重定向url添加参数告诉login.jsp登录失败)
                 *
                 * 为了不让浏览器本地和代理服务器(DNS)缓存301，造成自动跳转的问题。
                 * 需要在响应头加上 "Cache-Control" 的信息
                 *
                 *    no-cache则表示不可直接用缓存，而是先要到服务器端进行验证。
                 *    no-store，表示本地和代理服务器都不可以用缓存，必须去重新获取。
                 */
                resp.setStatus(301);
                resp.setHeader("cache-control", "no-cache;no-store");
                resp.setHeader("location", req.getContextPath() + "/JSPPages/login.html?t=" + System.currentTimeMillis());
            } else {
                /***
                 * 用户已登录的情况
                 * 往下执行过滤器
                 */
                chain.doFilter(req, resp);
            }
        }
    }

    /***
     *
     * 返回一个包含所有不被过滤的uri的List
     *
     * @return
     */
    private List<String> getPassList() {
        List<String> passList = new ArrayList<String>();
        passList.add("/JSPPages/login.html");
        passList.add("/JSPPages/servletLogin");
        passList.add("/JSPPages/login.jsp");
        passList.add("/servletLogin");
        return passList;
    }

    /***
     * 此方法会在本实例被销毁之前调用,一般在服务器关闭的时候会顺带调用此方法.
     */
    @Override
    public void destroy() {
        System.out.println(new Date().toString() + " Filter : loginFilter has been destroyed");
    }
}

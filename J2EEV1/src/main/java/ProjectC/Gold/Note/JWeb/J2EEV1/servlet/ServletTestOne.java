package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ServletTestOne extends HttpServlet {

    /***
     * 次Servlet的构造函数
     * 添加该函数的目的是为了监控一个Servlet的生命周期.
     * 一个Servlet的创建是在这个Servlet被调用的时候.
     * Servlet一旦被创建除非被销毁,否则无论调用几次此Servlet返回的都是同一个实例(该构造函数只会被调用一次).
     */
    public ServletTestOne() {
        System.out.println(new Date().toString() + " A Servlet named ServletTestOne has been created.");
    }

    /***
     *
     * 此方法会在Servlet构造函数执行后执行,所以很明显该方法也只会被调用一次.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /***
     *
     * 此方法是Servlet实例的入口,当服务器调用了Servlet实例,会从这个入口进,不论前端指定的请求方法是get还是post还是其他的方法.
     * 所以可以利用这个方法做一些请求处理前的统一处理.
     * 这里就单纯地转发给了doGet方法.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /***
         * 调用传来的HttpServletResponse实例resp里的getWriter方法获取输出流,再调用输出流的println方法输出web页面.
         * 其实原理就是利用输出流输出一个html页面.
         */
        resp.getWriter().println("<h1>Date Info:</h1>");
        resp.getWriter().println("<h1>" + new Date().toString() + "</h1>");
        resp.getWriter().println("<h3><a href=\"" + req.getContextPath() + "/gotoindex\">Go to index</a></h3>");
    }

    /***
     * 此方法会在该Servlet实例被销毁的时候调用,有以下几种情况.
     *
     * 1,该Servlet所在的web应用重新启动
     * 在server.xml中配置该web应用的时候用到了
     * <Context path="/" docBase="e:\\project\\j2ee\\web" debug="0" reloadable="false" />
     * 如果把 reloadable="false" 改为reloadable="true" 就表示有任何类发生的更新，web应用会自动重启
     * 当web应用自动重启的时候，destroy()方法就会被调用
     *
     * 2,关闭tomcat的时候 destroy()方法会被调用,但是这个一般都发生的很快,不易被发现.
     */
    @Override
    public void destroy() {

        System.out.println(new Date().toString() + " A Servlet named ServletTestOne has been destroyed.");

    }
}

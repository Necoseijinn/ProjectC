package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServletTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName;
        String passWord;

        /***
         * Tomcat Web容器会根据前端的提交方法(此处为Get方法)去解析传来的字符串,并把键值对设到parameter(Map集合)中.
         * req的getRequestDispatcher方法里传递的路径字符串不用带项目名.
         */
        userName = req.getParameter("userName");
        passWord = req.getParameter("passWord");

        if ("AlienCat".equals(userName) && "nonono".equals(passWord)) {

            HttpSession session = req.getSession();
            session.setAttribute("userName", userName);

            /***
             * 此处演示了服务端跳转(由于是服务器内处理完返回数据给前端,所以不会改变url路径,简单来说就是不会发生重定向.)
             */

            req.getRequestDispatcher("/JSPPages/loginSuccess.jsp").forward(req, resp);
        } else {

            /***
             * 此处演示了客户端跳转
             * 本质是向浏览器发送应答报文,并在返回头设定重定向(具体参考下面)信息,告诉浏览器再发送一次请求.
             *
             * 客户端有两种跳转
             *
             *    302 表示临时跳转
             *       response.sendRedirect("target");
             *    301 表示永久性跳转
             *       response.setStatus(301);
             *       response.setHeader("Location", "target");
             *
             * 301和302的区别主要在搜索引擎对页面排名的时候有影响
             * 能的话不要用302重定向
             *
             * 为了不让浏览器本地和代理服务器(DNS)缓存301，造成自动跳转的问题。
             * 需要在响应头加上 "Cache-Control" 的信息
             *
             *    no-cache则表示不可直接用缓存，而是先要到服务器端进行验证。
             *    no-store，表示本地和代理服务器都不可以用缓存，必须去重新获取。
             */
            resp.setStatus(301);
            resp.setHeader("cache-control", "no-cache;no-store");
            resp.setHeader("Location", req.getContextPath() + "/JSPPages/loginfaile.html");
        }

    }
}

package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /***
         * 获取当前Session对象
         * 删除Session容器中的userName变量
         * 这样就会被过滤器拦截下来转到登录画面
         */
        HttpSession session = req.getSession();
        session.removeAttribute("userName");

        /***
         * 由于登出了一个用户所以在线用户需要减1
         */
        ServletContext servletContext = req.getServletContext();
        Integer onlineUserCount = (Integer) servletContext.getAttribute("onlineUserCount");
        onlineUserCount -= 1;
        servletContext.setAttribute("onlineUserCount", onlineUserCount);

        /***
         * 301重定向到登录画面
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
    }
}

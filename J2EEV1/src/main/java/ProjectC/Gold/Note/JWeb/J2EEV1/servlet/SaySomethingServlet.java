package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SaySomethingServlet extends HttpServlet {

    /***
     * 此方法为了测试Servlet自启动的配置是否生效.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println(new Date().toString() + " A Servlet named SaySomethingServlet has been created during Tomcat's start up.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String word;
        word = req.getParameter("word");
        resp.getWriter().println("<h1>Your Message is " + word + "</h1>");
        resp.getWriter().println("<h3><a href=\"" + req.getContextPath() + "/gotoindex\">Go to index</a></h3>");
    }
}

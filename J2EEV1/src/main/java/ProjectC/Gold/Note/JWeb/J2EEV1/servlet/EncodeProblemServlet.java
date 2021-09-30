package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodeProblemServlet extends HttpServlet {
    /***
     *
     * Tomcat服务器的默认编码是[ISO-8859-1],所以从前端发来的编码要先用ISO-8859-1编码再用前端使用的编码进行解码.
     *
     * 前端浏览器(UTF-8)-------(UTF-8编码完的数据)------->Tomcat服务器(ISO-8859-1)------用ISO-8859-1进行解码------>
     *
     * ISO-8859-1解码完的数据(乱码)------>用ISO-8859-1编码还原回UTF-8编码得到的数据------>用UTF-8进行解码(成功获取中文数据)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String word;

        /***
         * 这样一句就可以解决中文乱码的问题,不过需要写在最前面,推荐写在filter中,参考ProjectC.Gold.Note.JWeb.J2EEV1.filter.EncodeFilter.
         */
        //req.setCharacterEncoding("UTF-8");

        /***
         * 或者用下面的方式(土办法)
         * 先把获得的字符串按服务器编码"ISO-8859-1"编码,再用浏览器的编码"UTF-8"解码.
         */
        //word = new String(req.getParameter("word").getBytes("ISO-8859-1"),"UTF-8");

        /***
         * 此处由于在filter处已经作了编码设定处理,就没必要在这里再写一遍了.
         */
        word = req.getParameter("word");
        resp.getWriter().println("<h1>Your Input are" + word + "</h1>");
        resp.getWriter().println("<h3><a href=\"" + req.getContextPath() + "/gotoindex\">Back To Index</a></h3>");

        /***
         * 最后记住,由于servlet的response是把html文本包括自定义的数据通过网络输出流传输到浏览器,
         * 所以必须设置返回数据的编码让浏览器知道返回的数据采用什么编码.
         * 此处由于在过滤器ProjectC.Gold.Note.JWeb.J2EEV1.filter.EncodeFilter中已经配置好了,就不用重复配置了.
         */
        //resp.setContentType("text/html;UTF-8");

    }
}

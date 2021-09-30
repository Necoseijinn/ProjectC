package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import jdk.internal.util.xml.impl.Input;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TestAllResponseMethod extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /***
         * 设置响应格式
         * "text/html" 是即格式,在request获取头信息中对应的request.getHeader("accept").
         * "text/html" 是存在的,表示浏览器可以识别这种格式,如果换一个其他的格式,比如 "text/lol",浏览器不能识别,那么打开此servlet就会弹出一个下载的对话框.
         * 这样的手段也就常常用于实现下载功能
         */
        //详细请参考ProjectC.Gold.Note.JWeb.J2EEV1.servlet.DownloadTest

        /***
         * 设置响应编码
         * 设置响应编码有两种方式
         * 1. response.setContentType("text/html; charset=UTF-8");
         * 2. response.setCharacterEncoding("UTF-8");
         * 这两种方式都需要在response.getWriter调用之前执行才能生效.
         * 他们的区别在于
         * 1. 不仅发送到浏览器的内容会使用UTF-8编码,而且还通知浏览器使用UTF-8编码方式进行显示,所以总能正常显示中文.
         * 2. 仅仅是发送的浏览器的内容是UTF-8编码的,至于浏览器是用哪种编码方式显示不管,所以当浏览器的显示编码方式不是UTF-8的时候,就会看到乱码,需要手动再进行一次设置.
         *
         * 此处由于已经在ProjectC.Gold.Note.JWeb.J2EEV1.filter.EncodeFilter中设定了响应编码,就不重复设定了.
         *
         */
        //resp.setContentType("text/html;charser=UTF-8");
        //resp.setCharacterEncoding("UTF-8");

        /***
         * 使用缓存可以加快页面的加载,降低服务端的负担.
         * 但是也可能看到过时的信息,可以通过如下手段通知浏览器不要使用缓存.
         * response.setDateHeader("Expires",0 );
         * response.setHeader("Cache-Control","no-cache");
         * response.setHeader("pragma","no-cache");
         */
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("pragma", "no-cache");

        /***
         * 设置响应内容
         * 通过response.getWriter(); 获取一个PrintWriter 对象
         * 可以使用println(),append(),write(),format()等等方法设置返回给浏览器的html内容.
         */
        PrintWriter pw = resp.getWriter();
        pw.println("<h1>Write by PrintWriter.println</h1>");
        pw.append("<h1>Write by PrintWriter.append</h1>");
        pw.write("<h1>Write by PrintWriter.write</h1>");
        pw.format("<h1>Write by PrintWriter.fomat</h1>");
        pw.format("<h3><a herf=\"/JSPPages/index.jsp\"></a></h3>");
        pw.flush();
        pw.close();

        /***
         * 客户端有两种跳转
         * 302 表示临时跳转
         *    response.sendRedirect("fail.html");
         * 301 表示永久性跳转
         *    response.setStatus(301);
         *    response.setHeader("Location", "fail.html");
         *
         * 301跟302的区别:
         * 请参考笔记Http与Https网络协议----301与302的区别以及url劫持的概念
         */
    }
}

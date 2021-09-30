package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class TestAllRequestMethod extends HttpServlet {

    /***
     *
     * 此处重写了service方法,无论前端用什么请求方法都可以接受并处理.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /***
         * HttpServletRequest的常见方法.
         */
        System.out.println("浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + req.getRequestURL());

        System.out.println("浏览器发出请求的资源名部分，去掉了协议和主机名: " + req.getRequestURI());

        System.out.println("请求行中的参数部分，只能显示以get方式发出的参数，post方式的看不到: " + req.getQueryString());

        System.out.println("浏览器所处于的客户机的IP地址: " + req.getRemoteAddr());

        System.out.println("浏览器所处于的客户机的主机名: " + req.getRemoteHost());

        System.out.println("浏览器所处于的客户机使用的网络端口: " + req.getRemotePort());

        System.out.println("服务器的IP地址: " + req.getLocalAddr());

        System.out.println("服务器的主机名: " + req.getLocalPort());

        System.out.println("得到客户机请求方式一般是GET或者POST: " + req.getMethod());

        /***
         * HttpServletRequest获取参数的方法.
         */
        /** 用于获取单值的参数 **/
        String name = req.getParameter("name");
        /** 用于获取具有多值的参数,比如注册时候提交的"hobits",可以是多选的. **/
        String[] hobits = req.getParameterValues("hobits");
        /** 用于遍历所有的参数,并返回Map类型. **/
        Map<String, String[]> parameterMap = req.getParameterMap();

        System.out.println(new Date().toString() + " RequestMethodDemo TestParameterInfo : name = " + name);

        for (int i = 0; i < hobits.length; i++) {
            if (i == 0) {
                System.out.print(new Date().toString() + " RequestMethodDemo TestParameterInfo : hobits = [");
            }
            if ((i + 1) == hobits.length) {
                System.out.println(hobits[i] + "]");
            } else {
                System.out.print(hobits[i] + ",");
            }
        }
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        entries.forEach((e) -> {
            System.out.println(new Date().toString() + " RequestMethodDemo TestParameterInfoWithMap : key = " + e.getKey() + ", value = " + e.getValue().toString());
        });

        /***
         * HttpServletRequest获取头信息
         */
        /**
         * 获取浏览器传递过来的头信息
         * 此处获取的是"user-agent",可以获取浏览器的基本资料,这样就能判断是firefox,IE,chrome,或者是safari浏览器.
         */
        String userAgent = req.getHeader("user-agent");
        /** 获取浏览器所有的头信息名称,根据头信息名称就能遍历出所有的头信息. **/
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            System.out.println(new Date().toString() + " RequestMethodDemo TestHeaderInfo : " + headerName + " : " + headerValue);
        }

        req.getRequestDispatcher("/JSPPages/index.jsp").forward(req, resp);
    }
}

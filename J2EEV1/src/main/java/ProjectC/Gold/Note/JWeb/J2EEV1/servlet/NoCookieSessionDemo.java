package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCookieSessionDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /***
         * 不设置Cookie的情况
         */
        resp.getWriter().println("<h1>No Cookie and No Session</h1>");

        /***
         * 第一次访问的请求信息
         * GET /J2EEV1_war/noCookieSessionTest HTTP/1.1
         * Host: localhost:8080
         * User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:92.0) Gecko/20100101 Firefox/92.0
         * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,* / *;q=0.8
         * Accept-Language: ja,en-US;q=0.7,en;q=0.3
         * Accept-Encoding: gzip, deflate
         * Connection: keep-alive
         * Upgrade-Insecure-Requests: 1
         * Sec-Fetch-Dest: document
         * Sec-Fetch-Mode: navigate
         * Sec-Fetch-Site: none
         * Sec-Fetch-User: ?1
         *
         *第一次访问的响应信息
         * HTTP/1.1 200
         * Content-Type: text/html;charset=UTF-8
         * Content-Length: 35
         * Date: Tue, 28 Sep 2021 00:12:47 GMT
         * Keep-Alive: timeout=20
         * Connection: keep-alive
         *
         * 说明：
         * 由于并没有在Servlet中设置Cookie以及Session
         * 所以第一次返回的响应头中并没有携带Cookie信息。
         * 第一次访问的请求头中也不会有Cookie信息，因为是第一次访问。
         */

        /***
         * 第二次访问的请求信息
         * GET /J2EEV1_war/noCookieSessionTest HTTP/1.1
         * Host: localhost:8080
         * User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:92.0) Gecko/20100101 Firefox/92.0
         * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,* / *;q=0.8
         * Accept-Language: ja,en-US;q=0.7,en;q=0.3
         * Accept-Encoding: gzip, deflate
         * Connection: keep-alive
         * Upgrade-Insecure-Requests: 1
         * Sec-Fetch-Dest: document
         * Sec-Fetch-Mode: navigate
         * Sec-Fetch-Site: none
         * Sec-Fetch-User: ?1
         * Cache-Control: max-age=0
         *
         * 第二次访问的响应信息
         * HTTP/1.1 200
         * Content-Type: text/html;charset=UTF-8
         * Content-Length: 35
         * Date: Tue, 28 Sep 2021 00:16:18 GMT
         * Keep-Alive: timeout=20
         * Connection: keep-alive
         *
         * 说明：
         * 由于并没有在Servlet中设定Cookie以及Session信息
         * 无论访问几次都不会产生Cookie在请求或者响应信息中。
         */
    }
}

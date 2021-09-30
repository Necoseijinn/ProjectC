package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class WithCookieSessionDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /***
         * Cookie
         *     Cookie是一种浏览器和服务器交互数据的方式。
         *     Cookie是由服务器端创建，但是不会保存在服务器。
         *     创建好之后，发送给浏览器。浏览器保存在用户本地。
         *     下一次访问网站的时候，就会把该Cookie发送给服务器。
         *     由于jsp网页是默认创建Cookie和Session所以即便不手动设置，也会自动生成。
         *     Cookie使用例子：
         *         Cookie c = new Cookie("name", "Gareen");
         *             创建了一个cookie,名字是"name" 值是"Gareen"
         *         c.setMaxAge(24 * 60 * 60);
         *             表示这个cookie可以保留一天，如果是0，表示浏览器一关闭就销毁
         *         c.setPath("/");
         *             Path表示访问服务器的所有应用都会提交这个cookie到服务端，如果其值是 /a, 那么就表示仅仅访问 /a 路径的时候才会提交 cookie
         *         response.addCookie(c);
         *             通过response把这个cookie保存在浏览器端
         *     Cookie种类
         *         Cookie分为内存Cookie和硬盘Cookie
         *             内存Cookie
         *                 保存在浏览器内存中，浏览器窗口关闭而消失。
         *                 设置成内存Cooki的方法：
         *                     c.setMaxAge(0);
         *             硬盘Cookie
         *                 保存在硬盘中，不会随着浏览器窗口关闭而消失。
         *                 设置成硬盘Cookie的方法：
         *                     c.setMaxAge(24 * 60 * 60);
         *             不设置的话默认为硬盘Cookie保存时间为30分钟。
         *
         * Session
         *     Session对象其实就是个Map，用于存储一些信息。
         *     想要获取Session对象就必须要有SessionID
         *     用户第一次访问的时候，可以通过getSession()或者getSession(true)获取一个新建的Session。
         *     新建完Session之后服务器会把该Session的SessionID存放到一个新建的Cookie中并返回给浏览器。
         *     只要浏览器允许Cookie那就能使用Session。
         *     下一次访问服务器时，服务器利用getSession()或getSession(true)就能获取到存储在Cookie中的SessionID并获取Session对象。
         *
         *     简单来说服务器在启动时会创造一个Session对象的容器。
         *     当用户没有SessionID但调用了getSession()或者getSession(true)方法时，就会新建一个Session对象放到容器中，而SessionID就是获取该Session对象的钥匙。
         *     当用户Cookie中带有SessionID并调用了getSession()或者getSession(true)方法时，就会返回容器中跟SessionID对应的Session对象。
         *     注意Cookie有多个但存储SessionID的只有一个Cookie。
         *
         *     Session由于是依赖于Cookie存在的，所以一旦Cookie消失Session也跟着消失。
         *     想要设置Session为关闭浏览器窗口Session就消失的话只要把Cookie设置成内存Cookie就行
         *         c.setMaxAge(0);
         */

        /***
         * 由下可见Cookie可以有多个
         * 第一次访问的时候是0个
         * 第二次访问是2个
         *    一个是新建的Cookie
         *    一个是保存SessionID的Cookie
         */
        Cookie[] cookies = req.getCookies();

        /** 输出现有Cookie的数量 **/
        if (cookies == null) {
            resp.getWriter().println("<h1>Total Cookie Count : " + 0 + "</h1>");
        } else {
            resp.getWriter().println("<h1>Total Cookie Count : " + cookies.length + "</h1>");
        }

        /***
         * 利用getSession(false)方法查看是否有对应的Session对象
         * 如果有就输出保存在Session对象中的内容
         * 如果没有就什么都不做
         */
        HttpSession session = req.getSession(false);
        if (session != null) {
            resp.getWriter().println("<h1>UserName : " + session.getAttribute("UserName") + "</h1>");
        }


        /***
         * 创建Cookie
         * 需要传入Cookie的名称以及Cookie的值
         */
        Cookie cookie = new Cookie("Key", "Value");

        /** 可以通过此方法设定Cookie的新值 **/
        cookie.setValue("NewValue");

        /** 表示这个cookie可以保留一天，如果是0，表示浏览器一关闭就销毁 **/
        cookie.setMaxAge(24 * 60 * 60);

        /***
         * Path表示访问服务器的所有应用都会提交这个cookie到服务端。
         * 如果其值是 /a, 那么就表示仅仅访问 /a 路径的时候才会提交 cookie
         * 此处利用 req.getServletContext().getContextPath() 获取到项目名的uri，也就是/J2EEV1_war
         * 当然嫌麻烦直接写 / 也行，但会造成一个问题就是多项目的时候这个Cookie也会被提交到别的项目去。
         */
        cookie.setPath(req.getServletContext().getContextPath());

        /** 添加新建的Cookie到响应头中 **/
        resp.addCookie(cookie);

        /***
         * req.getSession()等价于req.getSession(true)
         *    意思是如果存在就返回该Session对象，不存在就新建一个Session对象再返回。
         * req.getSession(false)
         *    意思是如果存在就返回该Session对象，不存在就返回null。
         */
        session = req.getSession(true);

        /***
         * 添加内容到Session对象中，实际应用为保存用户信息。
         * 重复添加会覆盖。
         */
        session.setAttribute("UserName", "AlienCat");

        /***
         * 第一次访问的请求头信息
         * GET /J2EEV1_war/withCookieSessionTest HTTP/1.1
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
         * 第一次访问的响应头信息
         * HTTP/1.1 200
         * Set-Cookie: Key=NewValue; Max-Age=86400; Expires=Tue, 28-Sep-2021 09:38:49 GMT; Path=/J2EEV1_war
         * Set-Cookie: JSESSIONID=F2AF01ED3F1E4F2520D03C1A8429CA94; Path=/J2EEV1_war; HttpOnly
         * Content-Type: text/html;charset=UTF-8
         * Content-Length: 33
         * Date: Mon, 27 Sep 2021 09:38:49 GMT
         * Keep-Alive: timeout=20
         * Connection: keep-alive
         *
         * 说明：
         *    第一次访问由于没有Cookie更没有保存SessionID的Cookie所以看不到任何Cookie信息
         */

        /***
         * 第二次访问的请求头信息
         * GET /J2EEV1_war/withCookieSessionTest HTTP/1.1
         * Host: localhost:8080
         * User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:92.0) Gecko/20100101 Firefox/92.0
         * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,* / *;q=0.8
         * Accept-Language: ja,en-US;q=0.7,en;q=0.3
         * Accept-Encoding: gzip, deflate
         * Connection: keep-alive
         * Cookie: Key=NewValue; JSESSIONID=F2AF01ED3F1E4F2520D03C1A8429CA94
         * Upgrade-Insecure-Requests: 1
         * Sec-Fetch-Dest: document
         * Sec-Fetch-Mode: navigate
         * Sec-Fetch-Site: none
         * Sec-Fetch-User: ?1
         * Cache-Control: max-age=0
         *
         * 第二次访问的响应头信息
         * HTTP/1.1 200
         * Set-Cookie: Key=NewValue; Max-Age=86400; Expires=Tue, 28-Sep-2021 09:40:23 GMT; Path=/J2EEV1_war
         * Content-Type: text/html;charset=UTF-8
         * Content-Length: 63
         * Date: Mon, 27 Sep 2021 09:40:23 GMT
         * Keep-Alive: timeout=20
         * Connection: keep-alive
         *
         * 说明：
         * 由于第一次访问的时候已经返回了两个Cookie所以这两个Cookie就保存在了浏览器中。
         * 所以在第二次访问的时候浏览器就会带上这两个Cookie的键值对
         * 第二次返回由于该用户已经有对应的Seesion对象没有再新建，所以返回的响应头信息中就不再带有SessionID的Cookie了。
         * 由于这个Servlet的方法中每次访问都回新建一个Cookie添加到response中，所以每次返回响应头都会返回一个Cookie，
         * 并且由于该Cookie的名称跟浏览器已有的Cookie重复了，所以会发生覆盖。
         */

        /***
         * 补充：
         * 由于在JSP翻译后的java文件中，9种隐式对象的其中之一是Session对象。
         * 所以只要用到了JSP，返回的响应头信息中必定存在携带着SessionID的Cookie。
         */
    }
}

package ProjectC.Gold.Note.JWeb.J2EEV1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/***
 * 没有框架的情况下过滤器是通过实现Filter类来创建的
 */
public class EncodeFilter implements Filter {

    /***
     *
     * 此方法会在Tomcat启动的时候调用一次,因为服务器启动的时候会去web.xml查看定义的过滤器,有定义就制造一个.
     * 而init(FilterConfig f)方法就会在被创造的时候执行,也只在被创造的时候执行
     *
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println(new Date().toString() + " Filter : EncodeFilter init method has been proceed");

    }

    /***
     *
     * 在此方法中对每一个servlet请求都进行编码设定的操作
     * 包括针对servlet请求发送过来的数据进行UTF-8编码的设定
     * 以及返回数据时通过设定返回头(response header)的形式告知浏览器要用什么去解析返回的数据
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /***
         * 把传递进来的实例向下强转成HttpServletRequest和HttpServletResponse,便于调用更多方法.
         */
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        /***
         * 设定servlet请求的数据的编码
         */
        req.setCharacterEncoding("UTF-8");
        /***
         * 设定servlet返回的数据的返回头(response header)告知浏览器要用什么编码去解析返回数据
         */
        resp.setContentType("text/html;charset=UTF-8");

        /***
         * 获取浏览器发送请求时的url
         */
        StringBuffer servletName = req.getRequestURL();

        /***
         * 第一层判断
         * 判断url是不是以"/"结尾的
         */
        if (!((servletName.length() - 1) == servletName.lastIndexOf("/"))) {

            /***
             * 将url最后一个"/"之前的字符包括"/"除去
             */
            String urlPath = servletName.substring(servletName.lastIndexOf("/") + 1);

            /***
             * 第二层判断
             * 如果是请求jsp或者html文件则不打印,注意要考虑get请求时候的url模式
             */
            if (!(urlPath.endsWith(".jsp") || urlPath.endsWith(".html") || urlPath.contains("?"))) {
                System.out.println(new Date().toString() + " EncodeFilter catch servlet : " + urlPath);
            }
        }

        filterChain.doFilter(req, resp);
    }

    /***
     * 此方法会在本实例被销毁之前调用,一般在服务器关闭的时候会顺带调用此方法.
     */
    public void destroy() {

        System.out.println(new Date().toString() + " Filter : EncodeFilter has been destroyed");

    }
}

package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileInputStream fis = null;
        ServletOutputStream os = null;
        try {
            /***
             * 通过req.getServletContext().getRealPath("/")来获取项目根目录的系统路径.
             */
            fis = new FileInputStream(new File(req.getServletContext().getRealPath("/") + "/ForDownload/MAVEN_IMPORT.txt"));
            os = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            /** 详细参考index.jsp文件下载测试的注释 **/
            //情景2不稳定
            resp.setContentType("text/xxxx");
            //通过设置Content-Disposition响应头来告诉浏览器采用下载的方式处理响应数据
            resp.setHeader("Content-Disposition", "attachment;filename=MAVEN_IMPORT.txt");
        } finally {
            os.flush();
            os.close();
            fis.close();
        }
        req.getRequestDispatcher("JSPPages/index.jsp").forward(req, resp);
    }
}

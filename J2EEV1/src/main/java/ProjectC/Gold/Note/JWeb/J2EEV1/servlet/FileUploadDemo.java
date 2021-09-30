package ProjectC.Gold.Note.JWeb.J2EEV1.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FileUploadDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /** 新建一个Item工厂 **/
        DiskFileItemFactory itemFactory = new DiskFileItemFactory();
        /** 利用新建的工厂再新建一个ServletFileUpload对象 **/
        ServletFileUpload upload = new ServletFileUpload(itemFactory);
        /** 设置上传文件的大小限制5M **/
        itemFactory.setSizeThreshold(1024 * 1024 * 5);

        List items = null;
        try {
            /** 解析request实例里的请求体内容 **/
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        /** 获取List的迭代器 **/
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            FileItem item = (FileItem) iterator.next();
            /** 如果该item不是普通的字段，就用IO方式作为文件输出到指定目录 **/
            if (!item.isFormField()) {
                String fileName = System.currentTimeMillis() + ".jpg";
                /** 利用req.getServletContext().getRealPath()获取目标目录所对应的系统路径 **/
                String filePath = req.getServletContext().getRealPath("/FileUpload");
                File file = new File(filePath, fileName);
                file.getParentFile().mkdir();
                InputStream inputStream = item.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.close();
            } else {
                /***
                 * 由于form提交的是二进制数据,单纯靠过滤器的编码设置无法解决编码问题。
                 * 所以必须要在此处手动设置编码。
                 */
                String name = new String(item.getFieldName().getBytes("ISO-8859-1"), "UTF-8");
                String nameValue = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");

                System.out.println(new Date().toString() + "Your " + name + " is " + nameValue);
            }
        }
        req.getRequestDispatcher("/JSPPages/index.jsp").forward(req, resp);
    }
}

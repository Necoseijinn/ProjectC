package ProjectC.Gold.Note.JWeb.J2EEV1.jasonAjax;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.dao.ProductDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetJasonObjectFromAjax extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService();

        /** 获取request解析完的以键值对形式传输过来的JSON字符串(key是data,value是JSON字符串) **/
        String data = req.getParameter("data");
        /** 解析JSON字符串转换成一个Category对象 JSONObject——>Bean **/
        Category category = JSON.parseObject(data, Category.class);

        categoryService.add(category);
    }
}

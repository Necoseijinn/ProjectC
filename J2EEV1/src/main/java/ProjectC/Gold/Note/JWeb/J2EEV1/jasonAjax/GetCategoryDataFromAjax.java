package ProjectC.Gold.Note.JWeb.J2EEV1.jasonAjax;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetCategoryDataFromAjax extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService();

        /** 获取request解析完的以键值对形式传输过来的JSON字符串(key是data,value是JSON字符串) **/
        String data = req.getParameter("data");
        /** 解析JSON字符串转换成一个JSONObject **/
        JSONObject jsonObject = JSON.parseObject(data);
        /** 获取JSON对象中type属性对应的值 **/
        String type = jsonObject.getString("type");

        List<Category> categories = new ArrayList<Category>();
        int total = categoryService.getTotal();
        if ("all".equals(type)) {
            categories = categoryService.list();
        } else {
            Category category = new Category();
            category.setId(Integer.parseInt(type));
            category = categoryService.get(category);
            categories.add(category);
        }
        /** JSON.toJSONString(Object) 可以传入一个实例对象也可以传入一个List **/
        String respData = JSON.toJSONString(categories);
        resp.getWriter().print(respData);
    }
}

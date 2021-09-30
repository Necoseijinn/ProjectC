package ProjectC.Gold.Note.JWeb.J2EEV1.jasonAjax;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RequestDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService();

        int total = categoryService.getTotal();
        List<Category> categories = categoryService.list();

        req.setAttribute("categories", categories);
        req.getRequestDispatcher("JSPPages/CategoryProduct/JSONTest/jsonGetCategories.jsp").forward(req, resp);
    }
}

package ProjectC.Gold.Note.JWeb.J2EEV1.controller;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.ProductService;
import ProjectC.Gold.Note.JWeb.J2EEV1.tool.PageHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCategories extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService();
        PageHelper pageHelper = new PageHelper();

        int start = 0;
        int count = 3;
        int showPageCount = 2;
        int total = categoryService.getTotal();

        String param = req.getParameter("start");
        if (param != null) {
            start = Integer.parseInt(param);
        }

        try {
            pageHelper.initPageset(start, count, showPageCount, total);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Category> categories = categoryService.list(start, count);

        req.setAttribute("pageHelper", pageHelper);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("JSPPages/CategoryProduct/listCategories.jsp").forward(req, resp);
    }
}

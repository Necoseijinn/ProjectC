package ProjectC.Gold.Note.JWeb.J2EEV1.controller;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.dao.ProductDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.ProductService;
import ProjectC.Gold.Note.JWeb.J2EEV1.tool.PageHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProduct extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = new ProductService();
        CategoryService categoryService = new CategoryService();
        PageHelper pageHelper = new PageHelper();

        int start = 0;
        int count = 3;
        int total = productService.getTotal();
        int showPageCount = 2;

        String param = req.getParameter("start");
        if (param != null) {
            start = Integer.parseInt(param);
        }

        try {
            pageHelper.initPageset(start, count, showPageCount, total);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Product> products = productService.list(start, count);
        List<Category> categories = categoryService.list();

        req.setAttribute("pageHelper", pageHelper);
        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("JSPPages/CategoryProduct/listProducts.jsp").forward(req, resp);
    }
}

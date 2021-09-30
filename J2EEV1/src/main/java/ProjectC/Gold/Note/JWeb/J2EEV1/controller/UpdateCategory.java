package ProjectC.Gold.Note.JWeb.J2EEV1.controller;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCategory extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryService categoryService = new CategoryService();

        String id = req.getParameter("category.id");
        String name = req.getParameter("category.name");

        Category category = new Category();
        category.setId(Integer.parseInt(id));
        category.setName(name);

        categoryService.update(category);

        /***
         * 为了不让浏览器本地和代理服务器(DNS)缓存301，造成自动跳转的问题。
         * 需要在响应头加上 "Cache-Control" 的信息
         *
         *    no-cache则表示不可直接用缓存，而是先要到服务器端进行验证。
         *    no-store，表示本地和代理服务器都不可以用缓存，必须去重新获取。
         */
        resp.setStatus(301);
        resp.setHeader("cache-control", "no-cache;no-store");
        resp.setHeader("location", "listCategories");
    }
}

package ProjectC.Gold.Note.JWeb.J2EEV1.service;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;

import java.util.List;

public class CategoryService implements BaseService<Category> {

    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public void add(Category category) {
        categoryDAO.add(category);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public Category get(Category category) {
        return categoryDAO.get(category);
    }

    @Override
    public List<Category> list() {
        return categoryDAO.list();
    }

    @Override
    public List<Category> list(int start, int count) {
        return categoryDAO.list(start, count);
    }

    @Override
    public int getTotal() {
        return categoryDAO.getTotal();
    }
}

package ProjectC.Gold.Note.Inergration.S2SHV1.service.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.CategoryDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.CategoryService;

import java.io.Serializable;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> list() {
        return categoryDAO.list();
    }

    public <T> Category get(Class<T> clz, Serializable s) {
        return categoryDAO.get(clz, s);
    }

    public void update(Category c) {
        categoryDAO.update(c);
    }

    public void delete(Category c) {
        categoryDAO.delete(c);
    }

    public void add(Category c) {
        categoryDAO.add(c);
    }
}

package ProjectC.Gold.Note.Inergration.S2SHV1.service;

import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;

import java.io.Serializable;
import java.util.List;

public interface CategoryService {
    public List<Category> list();

    public <T> Category get(Class<T> clz, Serializable s);

    public void update(Category c);

    public void delete(Category c);

    public void add(Category c);
}

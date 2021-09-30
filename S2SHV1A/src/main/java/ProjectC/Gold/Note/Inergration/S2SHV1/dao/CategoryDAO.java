package ProjectC.Gold.Note.Inergration.S2SHV1.dao;

import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


public interface CategoryDAO {

    public List<Category> list();

    public <T> Category get(Class<T> clz, Serializable s);

    public void update(Category c);

    public void delete(Category c);

    public void add(Category c);
}

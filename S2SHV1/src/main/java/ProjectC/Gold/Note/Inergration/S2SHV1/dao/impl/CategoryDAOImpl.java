package ProjectC.Gold.Note.Inergration.S2SHV1.dao.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.CategoryDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

public class CategoryDAOImpl extends HibernateDaoSupport implements CategoryDAO {
    public List<Category> list() {
        return this.getHibernateTemplate().find("From Category c");
    }

    public <T> Category get(Class<T> clz, Serializable s) {
        return (Category) this.getHibernateTemplate().get(clz, s);
    }

    public void update(Category c) {
        this.getHibernateTemplate().update(c);
    }

    public void delete(Category c) {
        this.getHibernateTemplate().delete(c);
    }

    public void add(Category c) {
        this.getHibernateTemplate().save(c);
    }
}

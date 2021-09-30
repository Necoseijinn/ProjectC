package ProjectC.Gold.Note.Inergration.S2SHV1.dao.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.CategoryDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用Repository注释声明制作此类的bean实例
 */
@Repository("categoryDAO")
public class CategoryDAOImpl extends HibernateDaoSupport implements CategoryDAO {

    /**
     * 表示注入sessionFactoryBean实例(该实例目前由applicationContext.xml配置制作)
     * 由于HibernateDaoSupport类的setSessionFactory方法为final方法不可重写
     * 所以此处新建了一个别的set方法名字可以任意重点是利用了Resource注释让spring调用该set方法
     * 然后再间接的调用父类的setSessionFactory方法来完成sessionFactory实例的注入
     */
    @Resource(name = "sessionFactoryBean")
    public void setSupperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

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

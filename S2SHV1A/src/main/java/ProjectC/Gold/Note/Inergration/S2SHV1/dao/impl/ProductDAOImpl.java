package ProjectC.Gold.Note.Inergration.S2SHV1.dao.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.ProductDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;
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
@Repository("productDAO")
public class ProductDAOImpl extends HibernateDaoSupport implements ProductDAO {

    /* 表示注入sessionFactoryBean实例(该实例目前由applicationContext.xml配置制作) */
    @Resource(name = "sessionFactoryBean")
    public void setSupperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public List<Product> list() {
        return this.getHibernateTemplate().find("FROM Product p");
    }

    public <T> Product get(Class<T> clz, Serializable s) {
        return (Product) this.getHibernateTemplate().get(clz, s);
    }

    public void update(Product p) {
        this.getHibernateTemplate().update(p);
    }

    public void delete(Product p) {
        this.getHibernateTemplate().delete(p);
    }

    public void add(Product p) {
        this.getHibernateTemplate().save(p);
    }

    public void addGroup(List<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == 2) {
                throw new RuntimeException();
            }
            this.getHibernateTemplate().save(list.get(i));
        }
    }
}

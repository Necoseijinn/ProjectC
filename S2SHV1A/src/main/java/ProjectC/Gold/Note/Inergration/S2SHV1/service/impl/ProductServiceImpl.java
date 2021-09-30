package ProjectC.Gold.Note.Inergration.S2SHV1.service.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.ProductDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/***
 * 用Service注释声明制作此类的bean实例
 * 用Transactional注释替代原本的<tx:advice></tx:advice>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
public class ProductServiceImpl implements ProductService {

    /**
     * 表示注入sessionFactoryBean实例(该实例目前由applicationContext.xml配置制作)
     * 由于HibernateDaoSupport类的setSessionFactory方法为final方法不可重写
     * 所以此处新建了一个别的set方法名字可以任意重点是利用了Resource注释让spring调用该set方法
     * 然后再间接的调用父类的setSessionFactory方法来完成sessionFactory实例的注入
     */
    @Autowired
    private ProductDAO productDAO;

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> list() {
        return productDAO.list();
    }

    public <T> Product get(Class<T> clz, Serializable s) {
        return productDAO.get(clz, s);
    }

    public void update(Product p) {
        productDAO.update(p);
    }

    public void delete(Product p) {
        productDAO.delete(p);
    }

    public void add(Product p) {
        productDAO.add(p);
    }

    public void addGroup(List<Product> list) {
        productDAO.addGroup(list);
    }
}

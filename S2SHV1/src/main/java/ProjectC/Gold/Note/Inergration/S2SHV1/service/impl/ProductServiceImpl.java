package ProjectC.Gold.Note.Inergration.S2SHV1.service.impl;

import ProjectC.Gold.Note.Inergration.S2SHV1.dao.ProductDAO;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.ProductService;

import java.io.Serializable;
import java.util.List;

public class ProductServiceImpl implements ProductService {
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

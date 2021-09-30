package ProjectC.Gold.Note.JWeb.J2EEV1.service;

import ProjectC.Gold.Note.JWeb.J2EEV1.dao.ProductDAO;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product;

import java.util.List;

public class ProductService implements BaseService<Product> {

    ProductDAO productDAO = new ProductDAO();

    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public Product get(Product product) {
        return productDAO.get(product);
    }

    @Override
    public List<Product> list() {
        return productDAO.list();
    }

    @Override
    public List<Product> list(int start, int count) {
        return productDAO.list(start, count);
    }

    @Override
    public int getTotal() {
        return productDAO.getTotal();
    }
}

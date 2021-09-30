package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.DAO.DAOImpl;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.Bean.Product;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.DAO.BaseDAO.BaseDAO;

public class ProductDAO implements BaseDAO<Product> {
    @Override
    public void add(Product product) {
        System.out.println("add method!");
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }
}

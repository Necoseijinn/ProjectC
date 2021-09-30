package ProjectC.Gold.Note.Inergration.S2SHV1.dao;

import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;

import java.io.Serializable;
import java.util.List;

public interface ProductDAO {
    public List<Product> list();

    public <T> Product get(Class<T> clz, Serializable s);

    public void update(Product p);

    public void delete(Product p);

    public void add(Product p);

    public void addGroup(List<Product> list);
}

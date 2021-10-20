package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Dao;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends BaseDao<Product> {

    @Autowired
    private Product product;

    @Override
    public Product get() {
        return product;
    }
}

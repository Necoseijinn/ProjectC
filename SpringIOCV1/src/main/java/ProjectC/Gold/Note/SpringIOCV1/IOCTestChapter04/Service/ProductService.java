package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Service;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/***
 *
 * 通过指定Service的value属性来指定该组件的ID
 * 其他注解同理
 *
 * 通过指定Scope注解的value属性来指定该组件是单例还是多例
 *
 */
@Service(value = "forTest02ProductService")
@Scope(value = "prototype")
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void get() {
        productDao.get();
    }
}

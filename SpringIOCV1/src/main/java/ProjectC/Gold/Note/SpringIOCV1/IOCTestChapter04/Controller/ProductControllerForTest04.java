package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Dao.ProductDao;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Pojo.Category;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ProductControllerForTest04 {

    private ProductService productService;
    private ProductDao productDao;

    @Autowired
    private Category category;

    /***
     *
     * 在方法上标注AutoWired
     * 1，不论方法名是什么，Spiring都会在创建这个组件的时候调用该方法
     * 2，在调用该方法的时候给该方法所有的参数赋值（跟在属性上添加AutoWired注解同理的赋值）
     *    还可以在参数位置添加AutoWired（该方法的该参数是否要自动装配还能指定是否为必须）或者Qualifier来指定匹配IOC容器中组件时候使用的id。
     *
     * @param productService
     * @param productDao
     */
    @Autowired
    public void setMethod(@Qualifier(value = "forTest02ProductService") ProductService productService,
                          @Autowired(required = false) @Qualifier(value = "nonono") ProductDao productDao) {
        this.productService = productService;
        this.productDao = productDao;
    }

    public void get() {
        productService.get();
        System.out.println(this.productDao);
        System.out.println("===========================================");
        System.out.println("自动装配的products（数组） : " + category.getProducts());
        System.out.println("自动装配的productList（List集合） : " + category.getProductList());
        System.out.println("自动装配的productMap（Map集合） : " + category.getProductMap());
    }
}

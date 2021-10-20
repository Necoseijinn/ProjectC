package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Dao.ProductDao;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Pojo.Category;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductServlet {

    /***
     * 使用Qualifier注解value来表明Autowired匹配的bean的id
     * 如果IOC容器中不存在Qualifier注解value所配置的id对应的bean
     *
     */
    @Autowired
    @Qualifier(value = "forTest02ProductService")
    private ProductService productService;

    /***
     * 如果IOC容器中不存在Qualifier注解value所配置的id对应的bean
     * 并且Autowired注解设置了required=false
     * 就会注入null值
     */
    @Autowired(required = false)
    @Qualifier(value = "nonono")
    private ProductDao productDao;

    public void get() {
        productService.get();
        System.out.println(this.productDao);
    }
}

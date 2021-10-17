package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Controller;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Dao.ProductDao;
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

    @Autowired
    @Qualifier(value = "forTest02ProductService")
    private ProductService productService;

    @Autowired(required = false)
    @Qualifier(value = "nonono")
    private ProductDao productDao;

    public void get() {
        productService.get();
        System.out.println(this.productDao);
    }
}

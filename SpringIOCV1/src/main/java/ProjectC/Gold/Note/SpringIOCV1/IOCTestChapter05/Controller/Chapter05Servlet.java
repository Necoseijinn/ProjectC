package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Controller;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Service.CategoryService;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Chapter05Servlet {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public void get() {
        System.out.println(productService.get());
        System.out.println(categoryService.get());
    }
}

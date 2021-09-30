package ProjectC.Gold.Note.Inergration.S2SHV1.action;

import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.CategoryService;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.ProductService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Namespace
 *    表示访问路径,如果是@Namespace("/test"),那么访问的时候,就需要写成http://127.0.0.1:8080/S2SHV1A_war/test/...
 * ParentPackage
 *    与配置文件中的struts-default相同,表示使用默认的一套拦截器
 */
@Namespace("/")
@ParentPackage("struts-default")
public class BasicAction {
    private Product product;
    private Category category;
    private List<Product> productList;
    private List<Category> categoryList;
    /***
     * 自动注入了productService和categoryService的ServiceBean实例
     */
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 对应原本struts.xml里面的配置
     */
    @Action(value = "transactionTest", results = {
            @Result(name = "success", type = "redirect", location = "listProducts"),
            @Result(name = "failed", type = "redirect", location = "listProducts")
    })
    public String transactionTest() {
        List<Product> list = new ArrayList();
        category = categoryService.get(Category.class, 1);
        for (int i = 0; i < 5; i++) {
            Product p = new Product();
            p.setName("Nokia 100" + i);
            p.setPrice(i * 1000);
            p.setCategory(category);
            list.add(p);
        }
        productService.addGroup(list);
        return "success";
    }

    @Action(value = "listProducts", results = {
            @Result(name = "success", location = "/JSPPages/listProducts.jsp")
    })
    public String listProducts() {
        productList = productService.list();
        categoryList = categoryService.list();
        return "success";
    }

    @Action(value = "addProduct", results = {
            @Result(name = "success", type = "redirect", location = "listProducts")
    })
    public String addProduct() {
        category = categoryService.get(Category.class, category.getId());
        product.setCategory(category);
        productService.add(product);
        return "success";
    }

    @Action(value = "editProduct", results = {
            @Result(name = "success", location = "/JSPPages/editProduct.jsp")
    })
    public String editProduct() {
        product = productService.get(Product.class, product.getId());
        categoryList = categoryService.list();
        return "success";
    }

    @Action(value = "updateProduct", results = {
            @Result(name = "success", type = "redirect", location = "listProducts")
    })
    public String updateProduct() {
        category = categoryService.get(Category.class, category.getId());
        product.setCategory(category);
        productService.update(product);
        return "success";
    }

    @Action(value = "deleteProduct", results = {
            @Result(name = "success", type = "redirect", location = "listProducts")
    })
    public String deleteProduct() {
        product = productService.get(Product.class, product.getId());
        productService.delete(product);
        return "success";
    }

    @Action(value = "listCategories", results = {
            @Result(name = "success", location = "/JSPPages/listCategories.jsp")
    })
    public String listCategories() {
        categoryList = categoryService.list();
        return "success";
    }

    @Action(value = "addCategory", results = {
            @Result(name = "success", type = "redirect", location = "listCategories")
    })
    public String addCategory() {
        categoryService.add(category);
        return "success";
    }

    @Action(value = "editCategory", results = {
            @Result(name = "success", location = "/JSPPages/editCategory.jsp")
    })
    public String editCategory() {
        category = categoryService.get(Category.class, category.getId());
        return "success";
    }

    @Action(value = "updateCategory", results = {
            @Result(name = "success", type = "redirect", location = "listCategories")
    })
    public String updateCategory() {
        categoryService.update(category);
        return "success";
    }

    @Action(value = "deleteCategory", results = {
            @Result(name = "success", type = "redirect", location = "listCategories")
    })
    public String deleteCategory() {
        category = categoryService.get(Category.class, category.getId());
        categoryService.delete(category);
        return "success";
    }

}

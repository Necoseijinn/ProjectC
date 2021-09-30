package ProjectC.Gold.Note.Inergration.S2SHV1.action;

import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Category;
import ProjectC.Gold.Note.Inergration.S2SHV1.pojo.Product;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.CategoryService;
import ProjectC.Gold.Note.Inergration.S2SHV1.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class BasicAction {
    private Product product;
    private Category category;
    private List<Product> productList;
    private List<Category> categoryList;
    private ProductService productService;
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

    public String listProducts() {
        productList = productService.list();
        categoryList = categoryService.list();
        return "success";
    }

    public String addProduct() {
        category = categoryService.get(Category.class, category.getId());
        product.setCategory(category);
        productService.add(product);
        return "success";
    }

    public String editProduct() {
        product = productService.get(Product.class, product.getId());
        categoryList = categoryService.list();
        return "success";
    }

    public String updateProduct() {
        category = categoryService.get(Category.class, category.getId());
        product.setCategory(category);
        productService.update(product);
        return "success";
    }

    public String deleteProduct() {
        product = productService.get(Product.class, product.getId());
        productService.delete(product);
        return "success";
    }

    public String listCategories() {
        categoryList = categoryService.list();
        return "success";
    }

    public String addCategory() {
        categoryService.add(category);
        return "success";
    }

    public String editCategory() {
        category = categoryService.get(Category.class, category.getId());
        return "success";
    }

    public String updateCategory() {
        categoryService.update(category);
        return "success";
    }

    public String deleteCategory() {
        category = categoryService.get(Category.class, category.getId());
        categoryService.delete(category);
        return "success";
    }

}

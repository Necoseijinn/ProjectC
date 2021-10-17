package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.Category;
import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo.Product;

public class PhoneStaticFactory {

    public static Product getProduct(String version) {
        Product product = new Product();
        Category category = new Category();
        category.setId(1);
        category.setName("Nokia 1000 Line");

        product.setId(1);
        product.setName("Nokia" + version);
        product.setCategory(category);
        return product;
    }
}

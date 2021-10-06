package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

public class PhoneInstanceFactory {
    public Product getProduct(String version){
        Product product=new Product();
        Category category = new Category();
        category.setId(3);
        category.setName("Nokia 2000 Line");

        product.setId(3);
        product.setName("Nokia"+version);
        product.setCategory(category);
        return product;
    }
}

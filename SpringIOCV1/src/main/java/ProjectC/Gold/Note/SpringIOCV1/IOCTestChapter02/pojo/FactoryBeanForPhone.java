package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanForPhone implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Product product = new Product();
        Category category = new Category();
        category.setId(5);
        category.setName("Nokia 3000 Line");

        product.setId(5);
        product.setName("Nokia3990");
        product.setCategory(category);
        return product;
    }

    @Override
    public Class<?> getObjectType() {
        return Product.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

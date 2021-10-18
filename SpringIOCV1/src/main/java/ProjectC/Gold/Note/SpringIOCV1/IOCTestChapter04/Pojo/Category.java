package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class Category {
    private int id;
    private String name;
    @Autowired
    private Product[] products;
    @Autowired
    private List<Product> productList;
    @Autowired
    private Map<String,Product> productMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + Arrays.toString(products) +
                ", productList=" + productList +
                ", productMap=" + productMap +
                '}';
    }

    public void showProducts(){
        System.out.println("自动装配的Product数组 : "+products);
        System.out.println("自动装配的ProductList : "+productList);
        System.out.println("自动装配的ProductMap : "+productMap);
    }
}

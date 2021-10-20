package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 *
 * Category标注了Autowired的三个属性
 *      1，products
 *          数组类型
 *          Spring会去IOC容器中寻找符合该数组所属类型的组件并封装成数组赋值给该属性
 *      2，productList
 *          List集合
 *          Spring会去IOC容器中寻找符合该List集合泛型的组件并封装成List赋值给该属性
 *      3，productMap
 *          Map集合
 *          Spring会去IOC容器中寻找符合该Map集合value对应泛型的组件
 *          然后把该组件的bean的id作为key一起封装成Map赋值给该属性
 *
 */
@Component
public class Category {
    private int id;
    private String name;
    @Autowired
    private Product[] products;
    @Autowired
    private List<Product> productList;
    @Autowired
    private Map<String, Product> productMap;

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

}

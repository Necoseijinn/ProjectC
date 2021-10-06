package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo;

import java.util.Date;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Product> productList;

    public Category() {
        super();
        System.out.println(new Date().toString()+" 【Category】的无参构造器被调用，【Category】的实例被创建了。");
    }

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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

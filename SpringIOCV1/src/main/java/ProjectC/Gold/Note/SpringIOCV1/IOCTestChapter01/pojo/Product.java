package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private float price;
    private Category category;

    public Product() {
        super();
        System.out.println(new Date().toString() + " 【Product】的无参构造器被调用，【Product】的实例被创建了。");
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}

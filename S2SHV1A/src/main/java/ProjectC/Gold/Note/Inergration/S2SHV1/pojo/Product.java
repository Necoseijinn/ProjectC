package ProjectC.Gold.Note.Inergration.S2SHV1.pojo;

import javax.persistence.*;

/***
 * 1. 使用@Entity 为 Product 添加注解
 * 2. @Table 指定对应表名
 * 3. @id 表明主键
 * 4. @GeneratedValue(strategy = GenerationType.IDENTITY) 使用自增长策略
 * 5. @Column 指明属性对应的数据库中的字段名
 */
@Entity
@Table(name = "product_")
public class Product {
    private int id;
    private String name;
    private float price;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "cid")
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

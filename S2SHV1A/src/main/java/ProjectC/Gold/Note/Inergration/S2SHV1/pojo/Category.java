package ProjectC.Gold.Note.Inergration.S2SHV1.pojo;

import javax.persistence.*;
import java.util.Set;

/***
 * 1. 使用@Entity 为 Product 添加注解
 * 2. @Table 指定对应表名
 * 3. @id 表明主键
 * 4. @GeneratedValue(strategy = GenerationType.IDENTITY) 使用自增长策略
 * 5. @Column 指明属性对应的数据库中的字段名
 */
@Entity
@Table(name = "category_")
public class Category {

    private int id;
    private String name;
    private Set<Product> products;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

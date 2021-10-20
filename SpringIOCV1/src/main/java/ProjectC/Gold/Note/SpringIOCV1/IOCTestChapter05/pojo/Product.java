package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Product {

    private int id;
    private String name;

    /***
     * 利用标注在方法上的Autowired注解来实现对基本数据类型的设置
     */
    @Autowired
    public void setProduct01() {
        this.id = 1;
        this.name = "Product01";
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

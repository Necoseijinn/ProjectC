package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo;

import java.util.Date;

public class Dog {
    private String kind;
    private String name;
    private int age;

    public Dog() {
        super();
        System.out.println(new Date().toString() + " 【Dog】的无参构造器被调用，【Dog】的实例被创建了。");
    }

    public Dog(String kind, String name, int age) {
        this();
        this.kind = kind;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

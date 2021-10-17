package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo;

import java.util.Date;

public class Cat {
    private String name;
    private String kind;
    private int age;

    public Cat() {
        super();
        System.out.println(new Date().toString() + " 【Cat】的无参构造器被调用，【Cat】的实例被创建了。");
    }

    public String getName() {
        return name;
    }

    /**
     * 不设置Cat类的name的setter方法，Spring就无法赋值。
     * public void setName(String name) {
     * this.name = name;
     * }
     **/

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

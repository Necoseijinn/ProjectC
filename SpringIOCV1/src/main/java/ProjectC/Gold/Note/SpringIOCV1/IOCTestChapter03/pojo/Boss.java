package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo;

import java.util.Date;

public class Boss {

    private String name;
    private int age;

    public Boss() {
        System.out.println(new Date().toString()+" [Boss]的构造函数被调用了");
    }

    public void bossInitMethod(){
        System.out.println(new Date().toString()+" [Boss]的初始化函数被调用了");
    }

    public void bossDestroyMethod(){
        System.out.println(new Date().toString()+" [Boss]的销毁函数被调用了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

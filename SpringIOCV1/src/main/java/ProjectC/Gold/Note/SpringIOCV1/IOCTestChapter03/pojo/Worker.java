package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo;

import java.util.Date;

public class Worker {

    private String name;
    private int age;

    public Worker() {
        System.out.println(new Date().toString() + " [Worker]的构造函数被调用了");
    }

    public void workerInitMethod() {
        System.out.println(new Date().toString() + " [Worker]的初始化函数被调用了");
    }

    public void workerDestroyMethod() {
        System.out.println(new Date().toString() + " [Worker]的销毁函数被调用了");
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
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

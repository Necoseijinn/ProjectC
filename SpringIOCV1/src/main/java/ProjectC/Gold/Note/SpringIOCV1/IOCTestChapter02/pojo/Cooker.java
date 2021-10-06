package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import java.util.Date;

public class Cooker {
    private String name;
    private String specialty;

    public Cooker() {
        super();
        System.out.println(new Date().toString()+" 【Cooker】的无参构造器被调用，【Cooker】的实例被创建了。*优先于Dinner bean 的创建*");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Cooker{" +
                "name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}

package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter01.pojo;

public class Bird {
    private String kind;
    private int age;

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

    @Override
    public String toString() {
        return "Bird{" +
                "kind='" + kind + '\'' +
                ", age=" + age +
                '}';
    }
}

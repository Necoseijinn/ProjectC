package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo;

public class UserName {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserName{" +
                "name='" + name + '\'' +
                '}';
    }
}

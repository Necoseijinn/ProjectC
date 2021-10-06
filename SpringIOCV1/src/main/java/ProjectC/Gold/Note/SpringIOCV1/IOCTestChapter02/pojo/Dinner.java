package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import java.util.Date;

public class Dinner {
    private Cooker cooker;
    private String dinnerName;

    public Dinner() {
        super();
        System.out.println(new Date().toString()+" 【Dinner】的无参构造器被调用，【Dinner】的实例被创建了。*创建顺序比Cooker bean 晚 *");
    }

    public Cooker getCooker() {
        return cooker;
    }

    public void setCooker(Cooker cooker) {
        this.cooker = cooker;
    }

    public String getDinnerName() {
        return dinnerName;
    }

    public void setDinnerName(String dinnerName) {
        this.dinnerName = dinnerName;
    }

    @Override
    public String toString() {
        return "Dinner{" +
                "cooker=" + cooker +
                ", dinnerName='" + dinnerName + '\'' +
                '}';
    }
}

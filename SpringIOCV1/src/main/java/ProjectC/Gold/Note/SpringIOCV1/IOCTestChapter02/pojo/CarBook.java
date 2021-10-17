package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import java.util.Map;

public class CarBook {
    private Map<String, String> book;

    public Map<String, String> getBook() {
        return book;
    }

    public void setBook(Map<String, String> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CarBook{" +
                "book=" + book +
                '}';
    }
}

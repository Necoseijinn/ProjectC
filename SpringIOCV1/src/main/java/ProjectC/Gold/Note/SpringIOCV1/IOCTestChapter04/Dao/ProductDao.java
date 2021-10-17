package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter04.Dao;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    public void get() {
        System.out.println("Get Product");
    }
}

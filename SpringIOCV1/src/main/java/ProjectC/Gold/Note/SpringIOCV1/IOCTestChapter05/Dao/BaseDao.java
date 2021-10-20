package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Dao;

import org.springframework.stereotype.Repository;

public abstract class BaseDao<T> {

    public abstract T get();
}

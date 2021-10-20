package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Service;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public T get() {
        return baseDao.get();
    }

}

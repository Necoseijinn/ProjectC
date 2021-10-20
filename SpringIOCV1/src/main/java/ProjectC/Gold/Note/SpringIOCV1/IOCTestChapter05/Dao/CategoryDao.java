package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.Dao;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter05.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends BaseDao<Category> {

    @Autowired
    private Category category;

    @Override
    public Category get() {
        return category;
    }
}

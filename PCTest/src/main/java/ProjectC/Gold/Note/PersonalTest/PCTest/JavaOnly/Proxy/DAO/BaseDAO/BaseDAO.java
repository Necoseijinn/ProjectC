package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.DAO.BaseDAO;

public interface BaseDAO<T> {

    public void add(T t);
    public void delete(T t);
    public void update(T t);
}

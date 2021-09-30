package ProjectC.Gold.Note.JWeb.J2EEV1.dao;

import java.util.List;

public interface BaseDAO<T> {

    public void add(T t);

    public void delete(T t);

    public void update(T t);

    public T get(T t);

    public List<T> list();

    public List<T> list(int start, int count);

    public int getTotal();

}

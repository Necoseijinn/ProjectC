package ProjectC.Gold.Note.JWeb.J2EEV1.service;

import java.util.List;

public interface BaseService<T> {
    public void add(T t);

    public void delete(T t);

    public void update(T t);

    public T get(T t);

    public List<T> list();

    public List<T> list(int start, int count);

    public int getTotal();
}

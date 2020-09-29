package datos;

import java.util.List;

/**
 *
 * @author asael
 * @param <T>
 */
public interface CRUD<T> {
    
    List<T> getListado();
    void create(T t);
    T getObject(String id);
    void update(T t);
    void delete(String t);
    boolean exists(String id);
}

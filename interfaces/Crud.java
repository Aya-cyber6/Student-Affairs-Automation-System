package termProject.interfaces;

public interface Crud {
    void add(Object obj);
    void update(Object obj);
    void delete(Object obj);
    Object getById(String id);
}
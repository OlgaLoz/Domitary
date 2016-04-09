package Interfaces;

public interface IRepository<T> {
    void create(T item);
    T read(int id);
    void update(T item);
    void delete(int id);
}

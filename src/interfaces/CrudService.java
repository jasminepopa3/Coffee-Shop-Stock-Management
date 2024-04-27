package interfaces;

public interface CrudService<T> {
    void add(T item);

    void update(int id, T item);

    void remove(int id);

    void display();
}


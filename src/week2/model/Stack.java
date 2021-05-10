package week2.model;

public interface Stack<T> extends Iterable<T> {
    void push(T item);
    T pop();
    T top();
    boolean isEmpty();
}

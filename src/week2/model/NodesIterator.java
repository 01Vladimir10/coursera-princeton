package week2.model;

import java.util.Iterator;

public class NodesIterator<T> implements Iterator<T> {
    private Node<T> current;

    public NodesIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

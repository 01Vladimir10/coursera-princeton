package week2;

import week2.model.Node;
import week2.model.NodesIterator;
import week2.model.Queue;

import java.util.Iterator;

public class BasicQueue<T> implements Queue<T> {
    private Node<T> first, last;

    @Override
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if(last == null){
            first = last = node;
            return;
        }
        last.setNext(node);
        last = node;
    }

    @Override
    public T dequeue() {
        T data = first.getData();
        first = first.getNext();

        if(first == null) last = null;
        return data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new NodesIterator<>(first);
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new BasicQueue<>();
        System.out.printf("Is empty? %s\n", queue.isEmpty());
        for (int i = 0; i < 20; i++)
            queue.enqueue(i);

        System.out.println("Items:");
        for (int i: queue)
            System.out.printf("%d ", i);

        System.out.println("\nRemoving items:");
        while (!queue.isEmpty())
            System.out.printf("%d ", queue.dequeue());

        System.out.printf("\nIs empty? %s", queue.isEmpty());
    }
}

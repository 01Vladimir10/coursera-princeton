package week2;

import week2.model.Node;
import week2.model.Queue;
import week2.model.Stack;
import week2.model.NodesIterator;

import java.util.Iterator;

public class BasicStack<T> implements Stack<T> {
    private Node<T> list;

    @Override
    public void push(T item) {
        Node<T> node = new Node<>(item);
        node.setNext(list);
        list = node;
    }

    @Override
    public T pop() {
        T data = list.getData();
        list = list.getNext();
        return data;
    }

    @Override
    public T top() {
        return list.getData();
    }

    @Override
    public boolean isEmpty() {
        return list == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new NodesIterator<>(list);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new BasicStack<>();
        System.out.printf("Is empty? %s\n", stack.isEmpty());
        for (int i = 0; i < 20; i++)
            stack.push(i);

        System.out.println("Items:");
        for (int i: stack)
            System.out.printf("%d ", i);

        System.out.println("\nRemoving items:");
        while (!stack.isEmpty())
            System.out.printf("%d ", stack.pop());

        System.out.printf("\nIs empty? %s", stack.isEmpty());
    }
}
